package com.openclicker

import com.openclicker.models.authentication.User

class BootStrap {

    def init = { servletContext ->
        new User(firstName: 'Someone', lastName: 'withlast', email: 'someEmail').save(flush: true)
        new User(firstName: 'SomeoneElse', lastName: 'withAdifferentLast', email: 'someEmailOther').save(flush: true)

    }


    def destroy = {

    }

}
