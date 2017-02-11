package com.openclicker

import com.example.Instructor
import com.example.Semester
import com.example.TypeOfSemester
import org.h2.tools.Server

class BootStrap {

    final String[] args = [
            "-tcpPort", "8092",
            "-tcpAllowOthers"]

    Server server

    def init = { servletContext ->
        server = Server.createTcpServer(args).start()

        def sem = new Semester(year: '2017', type: TypeOfSemester.Spring).save(flush:true)

        def instruc = new Instructor(firstName: 'Bastion', lastName: 'Temberg',
                email: 'whatisan@email.com', authenticationToken: 'TokenOfAuthen').save(flush:true)

//        def course = new Course(name: 'CSC 480').save(flush:true)
//
//        course.addToCrns(crn).save(flush:true).save(flush:true)




    }

    def destroy = {
        server.stop()
    }
}
