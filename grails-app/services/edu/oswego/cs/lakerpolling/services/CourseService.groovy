package edu.oswego.cs.lakerpolling.services

import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.Course
import edu.oswego.cs.lakerpolling.domains.Role
import edu.oswego.cs.lakerpolling.domains.User
import edu.oswego.cs.lakerpolling.util.QueryResult
import edu.oswego.cs.lakerpolling.util.RoleType
import grails.transaction.Transactional
import org.springframework.http.HttpStatus

/**
 * Service to perform transactional operations relating to {@link Course} model.
 */
@Transactional
class CourseService {

    /**
     * Deletes a specified course. The role of the requesting user is taken into consideration. Only admin and
     * instructors can delete courses and instructors can only delete their own courses.
     * @param token - The token to use to retrieve the requesting user.
     * @param courseId - The id of the course to delete.
     * @return The results of the operations.
     */
    QueryResult<Course> deleteCourse(AuthToken token, String courseId) {
        QueryResult<Course> res = new QueryResult<>()
        User requestingUser = token?.user
        long cid = courseId.isLong() ? courseId.toLong() : -1

        if (requestingUser != null && isInstructorOrAdmin(requestingUser.role) && cid != -1) {
            Course course = Course.findById(cid)
            if (course != null) {

                // if this is an admin performing the action
                if (requestingUser.role.type == RoleType.ADMIN) {
                    doDelete(course, res)
                } else {
                    //make sure the requesting user is the instructor
                    if (isInstructorOf(requestingUser, course)) {
                        doDelete(course, res)
                    } else {
                        QueryResult.fromHttpStatus(HttpStatus.UNAUTHORIZED, res)
                    }
                }

            } else {
                QueryResult.fromHttpStatus(HttpStatus.BAD_REQUEST)
            }
        } else {
            QueryResult.fromHttpStatus(HttpStatus.UNAUTHORIZED, res)
        }

        res
    }

    /**
     * Deletes a user from the specified course. The role of the user must be admin or instructor. Instructors can
     * only delete from their own courses.
     * @param token - The token to use to retrieve the requesting user.
     * @param courseId - The id of the course to delete.
     * @param userId - The user to remove from the course.
     * @return The results of the operations.
     */
    QueryResult<Course> deleteCourse(AuthToken token, String courseId, String userId) {
        QueryResult<Course> res = new QueryResult<>()
        User requestingUser = token?.user
        long cid = courseId.isLong() ? courseId.toLong() : -1
        long uid = userId.isLong() ? userId.toLong() : -1

        if (requestingUser != null && isInstructorOrAdmin(requestingUser.role)) {
            Course course = cid != -1 ? Course.findById(cid) : null
            User toRemove = uid != -1 ? User.findById(uid) : null

            if (course != null && toRemove != null) {
                if (requestingUser.role.type == RoleType.ADMIN) {
                    removeFromStudents(course, toRemove, res)
                    res.data = course
                } else {
                    if (isInstructorOf(requestingUser, course)) {
                        removeFromStudents(course, toRemove, res)
                        res.data = course
                    } else {
                        QueryResult.fromHttpStatus(HttpStatus.UNAUTHORIZED, res)
                    }
                }
            } else {
                QueryResult.fromHttpStatus(HttpStatus.BAD_REQUEST, res)
            }

        } else {
            QueryResult.fromHttpStatus(HttpStatus.UNAUTHORIZED, res)
        }

        res
    }

    /**
     * Deletes a course.
     * @param course - The course to delete.
     * @param result - Optional result to store data in.
     * @return query results.
     */
    private QueryResult<Course> doDelete(Course course, QueryResult<Course> result = new QueryResult<>(success: true)) {
        try {
            course.delete(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            QueryResult.fromHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR, result)
        }
        result
    }

    /**
     * Identified if given role is an instructor or admin role.
     * @param role - The role to check.
     * @return True if conditions are met.
     */
    private boolean isInstructorOrAdmin(Role role) {
        boolean result = false
        if (role != null) {
            result = role.type == RoleType.ADMIN || role.type == RoleType.INSTRUCTOR
        }
        result
    }

    /**
     * Removes a student from a course. Catching errors and returning results.
     * @param course - The course to delete from.
     * @param user - The user to remove.
     * @param result - The optional result to store data in.
     * @return A result object.
     */
    private QueryResult<Course> removeFromStudents(Course course, User user, QueryResult<Course> result = new QueryResult<>(success: true)) {
        try {
            course.removeFromStudents(user)
            course.save(flush: true, failOnError: true)
        } catch (Exception e) {
            e.printStackTrace()
            QueryResult.fromHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR, result)
        }
        result
    }

    Course make(User instructor, String courseName, String crn) {
        Course course = new Course(name: courseName, CRN: crn)
        //TODO: connect course with instructor
        course.save(flush: true, failOnError: true)
    }

    void delete(int courseId) {
        Course course = Course.get(courseId)
        if (course != null) {
            course.delete()
        }
    }

    boolean isInstructorOf(User user, int courseId) {
        Course course = Course.get(courseId)
        if (course != null) {
            //TODO: check if user is instructor of course
        }

        return false
    }

    boolean isInstructorOf(User user, Course course) {
        course.instructorId == user.id
    }

    void addStudent(int courseId, User student) {
        Course course = Course.get(courseId)
        if (course != null) {
            //TODO: add student to course
        }
    }

    boolean containsStudent(int courseId, User student) {
        Course course = Course.get(courseId)
        if (course != null) {
            //TODO: check if course contains student
        }
        return false
    }

}
