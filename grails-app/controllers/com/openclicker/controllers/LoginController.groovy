package com.openclicker.controllers

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload
import com.openclicker.models.authentication.User
import grails.converters.JSON

class LoginController {

    def googleAuthService

    def authenticatorService


    def index() {}

    def authenticate(String idToken) {

        def payloadOption = googleAuthService.getPayloadForIdToken(idToken)
        def result
        if (payloadOption.isPresent()) {
            Payload payload = payloadOption.get()
            def verifyAcc = googleAuthService.verifyAccountInfo(payload)
            result = verifyAcc.value
            if (verifyAcc.key) {
                User user = authenticatorService.getOrMakerUser(payload.getEmail(),
                        (String) payload.get("given_name"),
                        (String) payload.get("family_name"))

                authenticatorService.setSession(user, payload.getSubject(), session)
            }
        } else {
            result = [
                    "success": false,
                    "message": 'invalid id token'
            ] as JSON
        }

        render(result)
    }

}
