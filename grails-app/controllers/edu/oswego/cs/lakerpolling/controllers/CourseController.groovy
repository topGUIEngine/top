package edu.oswego.cs.lakerpolling.controllers

import edu.oswego.cs.lakerpolling.services.PreconditionService

class CourseController {

    static responseFormats = ['json', 'xml']

    PreconditionService precheckService

    def courseGet(String access_token, String user_id, String course_id, boolean list_students) {
        def require = precheckService.require(params, ["access_token", "user_id"])
        precheckService.acessToken(access_token, require)

        if (require.success) {

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
        precheckService.acessToken(access_token, require)
        if (require.success) {

        } else {
            render(view: '../failure', model: [errorCode: require.error, message: require.message])
        }
    }

}
