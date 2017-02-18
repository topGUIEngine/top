package com.openclicker.models.authentication

class User {

    String firstName
    String lastName
    String email
    String imageUrl
    String subject

    static mapping = {
        version false
    }

    static constraints = {
        email email: true, unique: true
        firstName blank: false
        lastName blank:false
    }

}