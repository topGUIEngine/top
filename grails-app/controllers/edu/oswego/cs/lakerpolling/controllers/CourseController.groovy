package edu.oswego.cs.lakerpolling.controllers

import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.Course
import edu.oswego.cs.lakerpolling.domains.Role
import edu.oswego.cs.lakerpolling.domains.User
import edu.oswego.cs.lakerpolling.services.PrecheckService

class CourseController {

    static responseFormats = ['json', 'xml']

    PrecheckService precheckService

    def courseGet(String access_token, String user_id, String course_id, boolean list_students) {
        def require = precheckService.require(params, ["access_token", "user_id"])
        if (require.success) {
            AuthToken token = AuthToken.findByAccessToken(access_token)
            User reqUser = token?.user
            if (token != null && reqUser != null) {

            } else {
                render(view: '../unauthorized')
            }
        } else {
            render(view: '../failure', model: [errorCode: require.error, message: require.message])
        }
    }

    def putCourse(String access_token, String name, String crn, String user_id) {
        def require = precheckService.require(params, ["access_token", "name", "crn"])
        render("crn:$crn")
    }

    def postCourse(String access_token, String course_id, String name, String user_id, File file) {
        def require = precheckService.require(params, ["access_token", "course_id"])
        render "name:$name"
    }

    def deleteCourse(String access_token, String course_id, String user_id) {
        def require = precheckService.require(params, ["access_token", "course_id"])
        if (require.success) {


//            AuthToken token = AuthToken.findByAccessToken(access_token)
//            User reqUser = token?.user
//            Role role = reqUser?.role
//            Course course = null
//
//            if (reqUser != null && role != null) {
//                course = course_id != null && course_id.isLong() ?
//                        Course.findById(course_id.toLong())
//                        : null
//            }
//
//            if (course != null) {
//
//            } else {
//                render(view: '../unauthorized')
//            }

        } else {
            render(view: '../failure', model: [errorCode: require.error, message: require.message])
        }
    }

}
