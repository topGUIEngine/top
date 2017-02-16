package com.openclicker.models.authentication

class OAuthId {

    String accessToken
    String refreshToken
    String idToken

    static hasOne = [user:User]
    static belongsTo = User

    static mapping = {
        autoTimestamp false
        version false
    }

    static constraints = {
        idToken unique: true, blank: false
        refreshToken unique: true, blank: false
        accessToken unique: true, blank: false
    }
}
