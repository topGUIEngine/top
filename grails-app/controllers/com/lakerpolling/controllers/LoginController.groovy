package com.lakerpolling.controllers

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload
import com.lakerpolling.models.authentication.User
import grails.converters.JSON

class LoginController {

    def googleAuthService

    def authenticatorService

    /**
     * Shows the login page.
     */
    def index() {}

    /**
     * Function to call to authenticate on the server. We create a user if one does not exist
     * and we verify current existing user as well as check the session.
     * @param idToken The encrypted id token received from google signin.
     */
    def authenticate(String idToken) {

        def payloadOption = googleAuthService.getPayloadForIdToken(idToken)//verify token
        def result
        if (payloadOption.isPresent()) {
            Payload payload = payloadOption.get()
            //verify account information.
            def verifyAcc = googleAuthService.verifyAccountInfo(payload)
            result = verifyAcc.value

            //if all verifications went through
            if (verifyAcc.key) {

                String email = payload.getEmail()
                String imageUrl = (String) payload.get("picture")
                String first = (String) payload.get("given_name")
                String last = (String) payload.get("family_name")
                String subj = payload.getSubject()

                User user = authenticatorService.getOrMakerUser(email, first, last, imageUrl, subj)



//                User user = authenticatorService.getOrMakerUser(payload.getEmail(),
//                        (String) payload.get("given_name"),
//                        (String) payload.get("family_name"))

                //set the session
                authenticatorService.setSession(user, payload.getSubject(), session)
            }
        } else {//google replied saying token is not valid
            result = [
                    "success": false,
                    "message": 'invalid id token'
            ] as JSON
        }

        render(result)
    }

}
