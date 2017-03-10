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

    def postCourse(String access_token, String course_id, String name, String user_id) {
        def require = preconditionService.notNull(params, ["access_token", "course_id"])
        render "name:$name"
    }

    /**
     * Endpoint to perform delete operation on courses.
     * @param access_token - The access token of the requesting user.
     * @param course_id - The id of the course.
     */
    def deleteCourse(String access_token, String course_id) {
        QueryResult<AuthToken> require = new QueryResult<>()

        preconditionService.notNull(params, ["access_token", "course_id"], require)
        preconditionService.acessToken(access_token, require)
        if (require.success) {

            QueryResult<Course> result = courseService.deleteCourse(require.data, course_id)

            if (result.success) {
                render(view: 'deleteResult', model: [token: require.data])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }

        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }

    def getterCourseStudent(String access_token, int course_id) {

    }

    def postCourseStudent(String access_token, int course_id, String email) {

    }

    def deleteCourseStudent(String access_token, int course_id) {

    }

}
