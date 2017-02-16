package com.openclicker.controllers

import grails.converters.JSON

class LoginController {

    def authenticatorService

    def index() {}

    def authenticate(String firstName, String lastName, String imageUrl, String email, String idToken) {
        println(firstName)
        println(lastName)
        println(email)
        println(idToken)

        render([
                "first" : firstName,
                "email" : email
        ] as JSON)

    }

}
