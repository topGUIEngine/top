package com.openclicker.controllers

import grails.converters.JSON

class LogoutController {

    def index(String idToken) {
        render(["id" : idToken] as JSON)
    }
}
