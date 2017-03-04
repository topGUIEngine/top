package edu.oswego.cs.lakerpolling.controllers

import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.User

class BootStrap {

    def init = { servletContext ->
        new User(firstName: "brandon", lastName: "lanthrip", email: "blanthri@oswego.edu", authToken: new AuthToken(subject: "idk", accessToken: "123").save()).save()
    }
    def destroy = {
    }
}
