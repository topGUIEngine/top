package edu.oswego.cs.lakerpolling.services

import edu.oswego.cs.lakerpolling.domains.Course
import edu.oswego.cs.lakerpolling.domains.User
import edu.oswego.cs.lakerpolling.util.QueryResult
import edu.oswego.cs.lakerpolling.util.RoleType

import grails.transaction.Transactional
import org.springframework.http.HttpStatus

import javax.management.Query

@Transactional
class CourseService {

    class CourseErrors {
        final static String UNAUTHORIZED_ACCESS = 'User is not authorized to perform action'
    }

    UserService userService

    QueryResult<Course> deleteCourse () {

    }

    QueryResult verifyInstructorAccess(User user, Course course) {
        def result = new QueryResult()
        if (user.role.type == RoleType.ADMIN || (user.role.type == RoleType.INSTRUCTOR && course.instructor.equals(user))) {
            result.success = true
            return result
        }
        result.success = false
        result.message = CourseErrors.UNAUTHORIZED_ACCESS
        result.errorCode = HttpStatus.UNAUTHORIZED.value()
        result
    }

    QueryResult verifyInstructorAccess(String accessToken, Course course) {
        def userResult = userService.getUser(accessToken)
        if (!userResult.success) {
            def result = new QueryResult()
            result.success = false
            result.errorCode = userResult.errorCode
            result.message = userResult.message
            return result
        }
        return verifyInstructorAccess(userResult.data, course)
    }
}
