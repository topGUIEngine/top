package edu.oswego.cs.lakerpolling.controllers

import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.Course
import edu.oswego.cs.lakerpolling.services.CourseService
import edu.oswego.cs.lakerpolling.services.PreconditionService
import edu.oswego.cs.lakerpolling.util.QueryResult

class CourseController {

    static responseFormats = ['json', 'xml']

    PreconditionService preconditionService
    CourseService courseService

    def courseGet(String access_token, String user_id, String course_id, boolean list_students) {
        def require = preconditionService.notNull(params, ["access_token", "user_id"])
        preconditionService.acessToken(access_token, require)

        if (require.success) {

        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }

    def putCourse(String access_token, String name, String crn, String user_id) {
        def require = preconditionService.notNull(params, ["access_token", "name", "crn"])
        render("crn:$crn")
    }

    def postCourse(String access_token, String course_id, String name, String user_id, File file) {
        def require = preconditionService.notNull(params, ["access_token", "course_id"])
        render "name:$name"
    }

    /**
     * Endpoint to perform delete operation on courses.
     * @param access_token - The access token of the requesting user.
     * @param course_id - The id of the course.
     * @param user_id - (Optional) The id of the user to delete.
     */
    def deleteCourse(String access_token, String course_id, String user_id) {
        QueryResult<AuthToken> require = new QueryResult<>()

        preconditionService.notNull(params, ["access_token", "course_id"], require)
        preconditionService.acessToken(access_token, require)
        if (require.success) {

            QueryResult<Course> result = user_id == null ?
                    courseService.deleteCourse(require.data, course_id)
                    : courseService.deleteCourse(require.data, course_id, user_id)

            if (result.success) {
                render(view: 'result', model: [token: require.data, course: result.data, listStudents: false])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }

        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }

}
