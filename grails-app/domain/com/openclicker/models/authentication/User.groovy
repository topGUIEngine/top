package com.openclicker.models.authentication

class User {

    String firstName
    String lastName
    String email

    static mapping = {
        version false
    }

    static constraints = {
        email email: true, unique: true
        firstName blank: false
        lastName blank:false
    }

}