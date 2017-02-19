package edu.oswego.edu.cs.lakerpolling.controllers

import edu.oswego.edu.cs.lakerpolling.models.authentication.User

class DashboardController {

    // authenticator service. Set by dependency injection
    def authenticatorService

    /**
     * Index controller for dashboard. Checks if user is authenticated, if not, redirects to login.
     */
    def index() {
        Optional<User> optUser = authenticatorService.getAuthenticatedUser(session)
        if (optUser.isPresent()) {
            render(view: 'index')
        } else {
            redirect(controller: 'login', action: 'index')
        }
    }

}
