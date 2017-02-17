package com.openclicker.controllers

import com.openclicker.models.authentication.User

class DashboardController {

    def authenticatorService

    def index() {
        Optional<User> optUser = authenticatorService.getAuthenticatedUser(session)
        if (optUser.isPresent()) {
            render(view: 'index')
        } else {
            redirect(controller: 'login', action: 'index')
        }
    }

}
