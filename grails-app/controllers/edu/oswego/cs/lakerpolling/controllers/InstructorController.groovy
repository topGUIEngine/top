package edu.oswego.cs.lakerpolling.controllers

import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.Course
import edu.oswego.cs.lakerpolling.domains.User
import edu.oswego.cs.lakerpolling.util.RoleType
import grails.rest.*
import grails.converters.*

class InstructorController {
	static responseFormats = ['json', 'xml']
	
    def index() {
        String token = params.authToken
        User user = grabUser(token)

        if(user && checkIfInstructor(user)) {
            // TODO add relationship to user!!!!
            //List<Course> courses = User.findAllBy..
        }
    }

    def getStudent(String studentId) {
        String token = params.authToken
        User user = grabUser(token)
        User student = grabUser(studentId)

        if(user && checkIfInstructor(user)) {
            if(student && !checkIfInstructor(student)) {
                //TODO add relationships between users!!
                //List<User> students = User.findAllBy...
            }
        }
    }

    def getCourseParticipants() {

    }

    def addStudentToCourse() {

    }

    private def checkIfInstructor(User user) { user.role.type.equals(RoleType.INSTRUCTOR) }

    private def grabUser(String token) {
        AuthToken authToken = AuthToken.findByAccessToken(token)
        User user = User.findByAuthToken(authToken)
        return user
    }
}
