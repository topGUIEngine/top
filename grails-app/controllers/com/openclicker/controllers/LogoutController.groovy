package com.openclicker.controllers

import grails.converters.JSON

class LogoutController {

    def authenticatorService

    def invalidateSignin() {
        authenticatorService.signOutCurrentUser(session)
        render ([
                "success" : "true",
                "message" : "ok"
        ] as JSON)
    }

}
