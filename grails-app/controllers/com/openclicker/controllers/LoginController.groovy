package com.openclicker.controllers

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import grails.converters.JSON

class LoginController {

    def googleAuthService

    def authenticatorService


    def index() {}

    def authenticate() {

        String callBackUrl = googleAuthService.callbackUrl
        redirect(url: googleAuthService.buildAuthorizeUrl(callBackUrl))
    }

    def callback(String code) {
        try {

            GoogleTokenResponse tokenResponse = googleAuthService.exchangeCodeForToken(code, googleAuthService.callbackUrl)

            GoogleIdToken googleIdToken = tokenResponse.parseIdToken()
            def payload = googleIdToken.getPayload()

            render([
                    "first": (String) payload.get("given_name"),
                    "last" : (String) payload.get("family_name"),
                    "email": payload.getEmail()
            ] as JSON)

            GoogleCredential credential = new GoogleCredential().setFromTokenResponse(tokenResponse)


        } catch (e) {
            render e
        }
    }

//    private  def oAuthCallBackUrl () {
//        return grailsApplication.config.auth2.serverUrl.concat(g.createLink([controller: 'login', action: 'callback']))
//    }

}
