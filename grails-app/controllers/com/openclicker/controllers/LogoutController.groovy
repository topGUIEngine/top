package com.openclicker.controllers

import grails.converters.JSON

class LogoutController {

    def authenticatorService

    /**
     * Endpoint to invalidate the server's tracking of this session.
     */
    def invalidateSignin() {
        authenticatorService.signOutCurrentUser(session)
        render ([
                "success" : "true",
                "message" : "ok"
        ] as JSON)
    }

}
