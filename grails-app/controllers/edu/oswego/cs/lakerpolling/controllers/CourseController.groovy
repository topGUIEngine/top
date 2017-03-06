package edu.oswego.cs.lakerpolling.controllers

import edu.oswego.cs.lakerpolling.services.PrecheckService

class CourseController {

    static responseFormats = ['json', 'xml']

    PrecheckService precheckService

    def courseGet(String access_token, String user_id, String course_id, boolean list_students) {
        def require = precheckService.require(params, ["access_token", "user_id"])
        if (require.success) {
            render("HI")
        } else {
            render (view : '../failure', model : [errorCode:require.error, message:require.message])
        }
    }

    def putCourse() {

    }

    def postCourse() {

    }

    def deleteCourse() {

    }

}
