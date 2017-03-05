package edu.oswego.cs.lakerpolling.services

import edu.oswego.cs.lakerpolling.domains.Course
import edu.oswego.cs.lakerpolling.domains.User
import grails.transaction.Transactional

@Transactional
class CourseService {

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
