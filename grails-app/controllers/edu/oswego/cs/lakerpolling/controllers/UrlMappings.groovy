package edu.oswego.cs.lakerpolling.controllers

class UrlMappings {

    static mappings = {

        group "/api/user", {
            "/auth"(controller: 'user', action: 'auth')
        }

        group "/api/course", {
            "/"(controller: 'course', action: 'courseGet', method : 'get')
        }

        "/**"(controller: 'application', action: 'index')
    }
}
