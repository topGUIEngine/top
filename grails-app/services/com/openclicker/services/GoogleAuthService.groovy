package com.openclicker.services

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson.JacksonFactory
import grails.converters.JSON
import grails.transaction.Transactional
import javafx.util.Pair

@Transactional
class GoogleAuthService {

    def grailsApplication

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
