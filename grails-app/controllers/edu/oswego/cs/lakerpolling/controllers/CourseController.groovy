package edu.oswego.cs.lakerpolling.controllers

import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.Course
import edu.oswego.cs.lakerpolling.domains.User
import edu.oswego.cs.lakerpolling.services.CourseListParserService
import edu.oswego.cs.lakerpolling.services.CourseService
import edu.oswego.cs.lakerpolling.services.PreconditionService
import edu.oswego.cs.lakerpolling.util.QueryResult
import org.springframework.web.multipart.MultipartFile

class CourseController {

    static responseFormats = ['json', 'xml']

    PreconditionService preconditionService
    CourseService courseService
    CourseListParserService courseListParserService

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

    def getCourseStudent(String access_token, String course_id) {

    }

    def postCourseStudent(String access_token, String course_id, String email) {
        QueryResult<AuthToken> require = new QueryResult<>()

        preconditionService.notNull(params, ["access_token", "course_id"], require)
        preconditionService.accessToken(access_token, require)

        if (require.success) {
            AuthToken token = require.data
            List<String> emails = new ArrayList<>()

            MultipartFile file = request.getFile("file")
            if (file != null) {
                QueryResult parseResult = courseListParserService.parse(file)
                if (parseResult.success) {
                    emails = parseResult.data
                }
                else {
                    render(view: '../failure', model: [errorCode: parseResult.errorCode, message: parseResult.message])
                    return
                }
            }

            if (email != null) {
                emails.add(email)
            }

            def result = courseService.postStudentsToCourse(token, course_id, emails)
            if (result.success) {
                render(view: 'postStudentsResult', model: [token: token, course_id: course_id, students: result.data])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }
        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }

    def deleteCourseStudent(String access_token, String course_id) {
        println(course_id)
        QueryResult<AuthToken> checks = new QueryResult<>()

        preconditionService.notNull(params, ["access_token", "course_id", "user_id"], checks)
        preconditionService.accessToken(access_token, checks)

        if (checks.success) {
            List userIds = params.list("user_id")
            QueryResult result = courseService.deleteStudentCourse(checks.data, course_id, userIds)
            if (result.success) {
                render(view: 'deleteResult', model: [token: checks.data])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }
        } else {
            render(view: '../failure', model: [errorCode: checks.errorCode, message: checks.message])
        }

    }

}
