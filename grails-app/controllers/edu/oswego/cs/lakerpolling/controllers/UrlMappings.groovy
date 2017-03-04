package edu.oswego.cs.lakerpolling.controllers

class UrlMappings {

    static mappings = {

        group "/api/user", {
            "/auth"(controller: 'user', action: 'auth')
        }

        group "/api/instructor/$authToken", {
            "/course" (controller: 'instructor', action: 'index', method: 'GET')
            "/course" (controller: 'instructor', action: 'getStudent', method: 'GET')
            "/course" (controller: 'instructor', action: 'getCourseParticipants', method: 'GET')
            "/course" (controller: 'instructor', action: 'addStudentToCourse', method: 'POST')
        }

        "/**"(controller: 'application', action: 'index')
    }
}
