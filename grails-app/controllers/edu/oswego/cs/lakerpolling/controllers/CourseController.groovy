package edu.oswego.cs.lakerpolling.controllers

import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.Course
import edu.oswego.cs.lakerpolling.domains.User
import edu.oswego.cs.lakerpolling.services.CourseService
import edu.oswego.cs.lakerpolling.services.PreconditionService
import edu.oswego.cs.lakerpolling.util.QueryResult
import org.springframework.http.HttpStatus

class CourseController {

    static responseFormats = ['json', 'xml']

    PreconditionService preconditionService
    CourseService courseService

    def courseGet(String access_token, String user_id, String course_id, boolean list_students) {
        def require = preconditionService.notNull(params, ["access_token", "user_id"])
        preconditionService.accessToken(access_token, require)

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
        preconditionService.accessToken(access_token, require)
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

    /**
     * Endpoint to get a list of students in a specified course.
     * @param access_token - The access token of the requesting user.
     * @param course_id - The id of the course
     */

    def getCourseStudent(String access_token, String course_id) {
        QueryResult<AuthToken> require = new QueryResult<>()
        preconditionService.notNull(params, ["access_token", "course_id"], require)
        preconditionService.accessToken(access_token, require)

        if (require.success) {
//            println("All good here! This statement runs!")
            def results = courseService.getAllStudents(require.data, course_id)
            if (results.success) {
                render(view: 'getStudentList', model: [token: require.data])
                return
            }
            else {
                render(view: '../failure', model: [errorCode: results.errorCode, message: results.message])
                return
            }
        }
        else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
            return
        }
    }

    def postCourseStudent(String access_token, String course_id, String email) {

    }

    def deleteCourseStudent(String access_token, String course_id) {
        println(course_id)
        QueryResult<AuthToken> checks = new QueryResult<>()

        preconditionService.notNull(params, ["access_token", "course_id", "user_id"], checks)
        preconditionService.accessToken(access_token, checks)

        if (checks.success) {
            if (course_id.isLong()) {
                List userIds = params.list("user_id")
                QueryResult result = courseService.deleteStudentCourse(checks.data, course_id.toLong(), userIds)
                if (result.success) {
                    render(view: 'deleteResult', model: [token: checks.data])
                } else {
                    render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
                }
            } else {
                def bad = QueryResult.fromHttpStatus(HttpStatus.BAD_REQUEST)
                render(view: '../failure', model: [errorCode: bad.errorCode, message: bad.message])
            }
        } else {
            render(view: '../failure', model: [errorCode: checks.errorCode, message: checks.message])
        }

    }

}
