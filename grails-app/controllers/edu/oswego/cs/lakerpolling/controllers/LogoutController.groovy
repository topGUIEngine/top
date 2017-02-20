package edu.oswego.cs.lakerpolling.controllers

import grails.converters.JSON

class LogoutController {

    def authenticatorService

    /**
     * Endpoint to invalidate the server's tracking of this session.
     */
    def invalidateSignin() {
        authenticatorService.signOutCurrentUser(session)
        render ([
                "message" : "ok"
        ] as JSON)
    }

}
