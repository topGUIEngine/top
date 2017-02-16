package com.openclicker.services

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import grails.transaction.Transactional
import grails.web.mapping.LinkGenerator

@Transactional
class GoogleAuthService {

    def grailsApplication
    LinkGenerator grailsLinkGenerator

    String getCallbackUrl() {
        def end = grailsLinkGenerator.link(controller: 'login', action: 'callback')
        return grailsApplication.config.getProperty('auth2.serverUrl').concat(end)
    }

    String buildAuthorizeUrl(String callbackUrl) {

        def p = [
                scope        : grailsApplication.config.getProperty('auth2.scope'),
                redirect_uri : callbackUrl,
                response_type: 'code',
                access_type  : 'online',
                client_id    : grailsApplication.config.getProperty('auth2.clientId'),
                prompt       : 'consent'
        ]

        return 'https://accounts.google.com/o/oauth2/auth?'
                .concat(p.collect { it }.join('&'))
    }

    GoogleTokenResponse exchangeCodeForToken(String code, String callback) {
        GoogleTokenResponse tokenResponse =
                new GoogleAuthorizationCodeTokenRequest(
                        new NetHttpTransport(),
                        JacksonFactory.getDefaultInstance(),
                        "https://www.googleapis.com/oauth2/v4/token",
                        (String)grailsApplication.config.getProperty('auth2.clientId'),
                        (String)grailsApplication.config.getProperty('auth2.clientSecret'),
                        code,
                        callback)
                        .execute()

        return tokenResponse
    }



//    TokenResponse exchangeCodeForToken(String code, String callbackUrl) {
//        RESTClient google = new RESTClient("https://accounts.google.com/o/oauth2/")
//
//        def body = [
//                code         : code,
//                client_id    : grailsApplication.config.getProperty('auth2.clientId'),
//                client_secret: grailsApplication.config.getProperty('auth2.clientSecret'),
//                redirect_uri : callbackUrl,
//                grant_type   : 'authorization_code'
//        ]
//        def response = google.post(path: 'token', body: body, requestContentType: URLENC)
//        def json = response.data
//
//        new TokenResponse(accessToken: json.access_token,
//                tokenType: json.token_type,
//                expiresInSeconds: json.expires_in,
//                refreshToken: json.refresh_token,
//                scope: json.scope)
//    }

}
