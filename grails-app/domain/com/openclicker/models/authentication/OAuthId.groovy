package com.openclicker.models.authentication

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.auth.oauth2.CredentialRefreshListener
import com.google.api.client.auth.oauth2.TokenErrorResponse
import com.google.api.client.auth.oauth2.TokenResponse
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson.JacksonFactory
import grails.util.Holders

class OAuthId implements CredentialRefreshListener {

    private final static HttpTransport HTTP_TRANSPORT = new NetHttpTransport()
    private final static JsonFactory JSON_FACTORY = new JacksonFactory()

    String accessToken
    String refreshToken
    Long expirationTimeInMillis

    Credential credential = new GoogleCredential.Builder()
            .setTransport(HTTP_TRANSPORT)
            .setJsonFactory(JSON_FACTORY)
            .setClientSecrets(
            Holders.grailsApplication.config.getProperty('auth2.clientId').toString(),
            Holders.grailsApplication.config.getProperty('auth2.clientSecret').toString())
            .addRefreshListener(this)
            .build();

    static hasOne = [user: User]
    static belongsTo = User

    static transients = ["credential"]

    static mapping = {
        autoTimestamp false
        version false
    }

    def beforeValidate() {
        accessToken = credential.getAccessToken()
        refreshToken = credential.getRefreshToken()
        expirationTimeInMillis = credential.getExpirationTimeMilliseconds()
    }

    def afterLoad() {
        credential.setAccessToken(accessToken)
        credential.setRefreshToken(refreshToken)
        credential.setExpirationTimeMilliseconds(expirationTimeInMillis)
    }


    @Override
    void onTokenResponse(Credential credential, TokenResponse tokenResponse) throws IOException {
        def store = findById(id)
        store.credential = credential
        store.save(failOnError: true)
    }

    @Override
    void onTokenErrorResponse(Credential credential, TokenErrorResponse tokenErrorResponse) throws IOException {
        log.error "On token error response credential=${credential} tokenErrorResponse=${tokenErrorResponse}"
    }
}
