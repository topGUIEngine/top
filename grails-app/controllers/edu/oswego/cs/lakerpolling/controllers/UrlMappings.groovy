package edu.oswego.cs.lakerpolling.controllers

class UrlMappings {

    static mappings = {

        group "/api/user", {
            "/auth"(controller: 'user', action: 'auth')
        }

        group "/api/courseInstructor/$authToken", {
            "/course" (controller: 'courseInstructor', action: 'index', method: 'GET')
            "/course" (controller: 'courseInstructor', action: 'getStudent', method: 'GET')
            "/course" (controller: 'courseInstructor', action: 'getCourseParticipants', method: 'GET')
            "/course" (controller: 'courseInstructor', action: 'addStudentToCourse', method: 'POST')
        }

        "/**"(controller: 'application', action: 'index')
    }
}
