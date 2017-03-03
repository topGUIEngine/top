package edu.oswego.cs.lakerpolling.controllers

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.User
import edu.oswego.cs.lakerpolling.services.AuthService
import edu.oswego.cs.lakerpolling.util.Pair
import grails.rest.*
import grails.converters.*

class UserController {

    AuthService authService

	static responseFormats = ['json', 'xml']
	
    def all(String accessToken) {
        render accessToken
    }

    def findUser(String accessToken, String id) {
        render accessToken.concat(id)
    }


    /**
     * Verifies and takes action based on the given idToken query parameter.
     */
    def auth() {

        String idTokenString = params.idToken
        String message = null
        User user = null
        AuthToken authToken = null

        if (idTokenString) {
            Optional<GoogleIdToken> optionalToken = authService.getIdToken(idTokenString)

            // if an id token is present, then google has verified it successfully
            if (optionalToken.isPresent()) {
                GoogleIdToken token = optionalToken.get()

                //verify the information on the id token
                Pair<Boolean, String> tokenVerifyPair = authService.verifyIdToken(token)

                if (tokenVerifyPair.key) {
                    //verify the payload of the id token
                    Pair<Boolean, String> payloadVerifyPair = authService.verifyPayload(token.payload)

                    if (payloadVerifyPair.key) {
                        Optional<Pair<User, AuthToken>> optUserAuthPair = authService.getMakeOrUpdate(token.payload)

                        if (optUserAuthPair.isPresent()) {
                            user = optUserAuthPair.get().key
                            authToken = optUserAuthPair.get().value
                        }

                    } else {
                        message = payloadVerifyPair.value
                    }
                } else {
                    message = tokenVerifyPair.value
                }
            } else {
                message = AuthService.AuthErrors.TOKEN_VERIFICATION
            }

        } else {
            message = AuthService.AuthErrors.TOKEN_VERIFICATION
        }

        if (message != null) {
            render(view: 'authError', model: [message: message])
        } else {

            if (user == null || authToken == null)
                render(status:401 ,view: 'authError', model: [message: AuthService.AuthErrors.INTERNAL_ERROR])
            else
                render(view: 'authUser', model: [user: user, token: authToken])

        }

    }
}
