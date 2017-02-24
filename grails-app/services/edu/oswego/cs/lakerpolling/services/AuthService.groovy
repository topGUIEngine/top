package edu.oswego.cs.lakerpolling.services

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.User
import edu.oswego.cs.lakerpolling.util.Pair
import grails.core.GrailsApplication
import grails.transaction.Transactional

@Transactional
class AuthService {

    class AuthErrors {
        final static String TOKEN_VERIFICATION = 'Token verification error'
        final static String TOKEN_INTEGRITY = 'Token integrity error'
        final static String UNVERIFIED_EMAIL = 'Unverified user email'
        final static String NON_OSWEGO_EMAIL = 'Non @oswego.edu email'
        final static String INTERNAL_ERROR = 'Internal server error'
    }

    GrailsApplication grailsApplication

    Optional<GoogleIdToken> getIdToken(String idTokenString) {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), new JacksonFactory())
                .setAudience([grailsApplication.config.getProperty("googleauth.clientId")])
                .build()

        GoogleIdToken temp = verifier.verify(idTokenString)

        temp != null ?
                Optional.of(temp)
                : Optional.empty()

    }

    Pair<Boolean, String> verifyIdToken(GoogleIdToken token) {
        Boolean success = false
        String message = AuthErrors.TOKEN_INTEGRITY

        if (token.verifyAudience([grailsApplication.config.getProperty("googleauth.clientId")])) {
            if (token.verifyIssuer(grailsApplication.config.getProperty("googleauth.issuer"))) {
                success = true
                message = ""
            }
        }

        new Pair<Boolean, String>(success, message)
    }

    Pair<Boolean, String> verifyPayload(Payload payload) {
        Boolean success = false
        String message

        if (payload.getEmailVerified()) {
            if (payload.getEmail().indexOf('oswego.edu') != -1) {
                message = ""
                success = true
            } else {
                message = AuthErrors.NON_OSWEGO_EMAIL
            }
        } else {
            message = AuthErrors.UNVERIFIED_EMAIL
        }

        new Pair<Boolean, String>(success, message)
    }

    Optional<Pair<User, AuthToken>> getMakeOrUpdate(Payload payload) {

        User user
        AuthToken token

        String subj = payload.getSubject()
        String first = payload.get("given_name").toString()
        String last = payload.get("family_name").toString()
        String imageUrl = payload.get("picture").toString()
        String accessTokenHash = payload.getAccessTokenHash()
        String email = payload.getEmail()

        token = AuthToken.findBySubject(subj)

        if (token != null) {

            user = token.user
            if (user.email != email) {
                user.email = email
            }

            if (token.accessToken != accessTokenHash) {
                token.accessToken = accessTokenHash
            }

            user.save(flush: true, failOnError: true)
            token.save(flush: true, failOnError: true)

        } else {// this may be a preloaded account or we don't have this user.
            user = User.findByEmail(email)

            if (user == null) {// new user
                user = new User(firstName: first, lastName: last, imageUrl: imageUrl, email: email).save(flush: true, failOnError: true)
            } else {//found the user by email
                if (user.imageUrl == null) {
                    user.imageUrl = imageUrl
                }

                if (user.firstName == null) {
                    user.firstName = first
                }

                if (user.lastName == null) {
                    user.lastName = last
                }

                user.save(flush:true, failOnError:true)
            }

            token = new AuthToken(user: user, subject: subj, accessToken: accessTokenHash).save(flush: true, failOnError: true)
        }

        user != null ? Optional.of(new Pair<User, AuthToken>(user, token))
                : Optional.empty()

    }

}
