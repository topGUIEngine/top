package edu.oswego.cs.lakerpolling.services

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson.JacksonFactory
import grails.converters.JSON
import grails.transaction.Transactional
import edu.oswego.cs.lakerpolling.util.Pair

@Transactional
class GoogleAuthService {

    def grailsApplication

    /**
     * Verifies the given id token with google.
     * @param idTokenString - The encrypted id token string.
     * @return An optional payload if the token was valid or empty if it was not.
     */
    Optional<Payload> getPayloadForIdToken(String idTokenString) {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(
                (String) grailsApplication.config.getProperty("auth2.clientId")))
                .build()

        GoogleIdToken idToken = verifier.verify(idTokenString)
        return idToken != null ?
                Optional.of(idToken.getPayload())
                : Optional.empty()

    }

    /**
     * Verifies the account information extracted from the payload.
     * @param payload - The payload to get information from.
     * @return A pair containing a boolean stating if validation passed and a message.
     */
    Pair<Boolean, JSON> verifyAccountInfo(Payload payload) {
        JSON json
        boolean result

        if (Boolean.valueOf(payload.getEmailVerified())) {
            if (payload.getEmail().indexOf("oswego.edu") != -1) {
                result = true
                json = [
                        success: "true",
                        message: "Verified"
                ]
            } else {
                result = false
                json = [
                        "success": false,
                        "message": "Email required to be an @oswego.edu email."
                ]
            }
        } else {
            result = false
            json = [
                    "success": false,
                    "message": "Unverified user email"
            ] as JSON
        }

        new Pair<Boolean, JSON>(result, json)
    }

}
