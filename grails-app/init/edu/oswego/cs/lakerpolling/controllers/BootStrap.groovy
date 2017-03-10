package edu.oswego.cs.lakerpolling.controllers

import edu.oswego.cs.lakerpolling.domains.AuthToken
import edu.oswego.cs.lakerpolling.domains.Course
import edu.oswego.cs.lakerpolling.domains.Role
import edu.oswego.cs.lakerpolling.domains.User
import edu.oswego.cs.lakerpolling.util.RoleType

class BootStrap {

    def init = { servletContext ->
//
//        User a = new User(firstName: "Jason", lastName: "Parker", email: "jpark@gmail.com", imageUrl: "Some image")
//                .save(flush: true, failOnError: true)
//
//        User b = new User(firstName: "Peter", lastName: "Swanson", email: "pswan@coolpeople.com", imageUrl: "coolest")
//                .save(flush: true, failOnError: true)
//
//        User stu = new User(firstName: "Zack", lastName: "Brown", email: "zb@gmail.com", imageUrl: "The greatest image.jpg")
//                .save(flush: true, failOnError: true)
//
//        User inst = new User(firstName: "Bastian", lastName: "Tembergen", email: "ws@gmail.com", imageUrl: "cartoon image")
//                .save(flush: true, failOnError: true)
//
//        User inst2 = new User(firstName: "Chris", lastName: "Haris", email: "chs@gmail.com", imageUrl: "imageofchris.png")
//                .save(flush: true, failOnError: true)
//
//
//        User admin = new User(firstName: "admin", lastName: "admin", email: "admin@gmail.com", imageUrl: "serious.jpg")
//                .save(flush: true, failOnError: true)
//
//        Course course = new Course(name: "CSC 480", crn: 11133, instructor: inst).save(flush: true, failOnError: true)
//        Course course1 = new Course(name: "HCI:521", crn: 3333, instructor: inst2).save(flush: true, failOnError: true)
//
//        course.addToStudents(a)
//        course.addToStudents(stu)
//        course.save(flush: true, failOnError: true)
//
//        course1.addToStudents(b)
//        course1.save(flush: true, failOnError: true)
//
//        new AuthToken(subject: "aa", accessToken: "bb", user: stu).save(flush: true, failOnError: true)
//        new AuthToken(subject: "cc", accessToken: "dd", user: inst).save(flush: true, failOnError: true)
//        new AuthToken(subject: "ee", accessToken: "ff", user: inst2).save(flush: true, failOnError: true)
//        new AuthToken(subject: "gg", accessToken: "hh", user: admin).save(flush: true, failOnError: true)
//
//        inst.setRole(new Role(type: RoleType.INSTRUCTOR, user: inst).save(flush: true, failOnError: true))
//        inst2.setRole(new Role(type: RoleType.INSTRUCTOR, user: inst2).save(flush: true, failOnError: true))
//        admin.setRole(new Role(type: RoleType.ADMIN, user: admin).save(flush: true, failOnError: true))
//
//        inst.save(flush: true, failOnError: true)
//        inst2.save(flush: true, failOnError: true)
//        admin.save(flush: true, failOnError: true)

    }

    def destroy = {
    }
}
