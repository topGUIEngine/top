package com.openclicker.services

import com.openclicker.models.authentication.OAuthId
import com.openclicker.models.authentication.User
import grails.transaction.Transactional

import javax.servlet.http.HttpSession

@Transactional
class AuthenticatorService {

    private static String SUBJECT = "userId"

    /**
     * Method to retrieve a user from the database by their email. It creates a user with
     * the given information if one was not found.
     * @param email - The email of the user.
     * @param first The first name of the user.
     * @param last The last name of the user.
     * @return A user domain object.
     */
    User getOrMakerUser(String email, String first, String last, String imageUrl, String subj) {
        User temp = User.findByEmail(email)
        if (temp == null) {
            temp = new User(email: email, firstName: first, lastName: last).save(flush: true, failOnError:true)
        }
        temp
    }

    /**
     * Method to set property onto the session object.
     * @param user - The user.
     * @param subj - The subject id from google sign in.
     * @param session - The session object.
     */
    void setSession(User user, String subj, HttpSession session) {
        OAuthId id = OAuthId.findBySubject(subj)
        if (id == null) {
            new OAuthId(subject: subj, user: user).save(flush: true, failOnError:true)
        } else {
            id.subject = subj
            id.save(flush: true)
        }

        session.setAttribute(SUBJECT, subj)
    }

    /**
     * Method to invalidate the session and delete the associated saved subject id.
     * @param session - The session object to invalidate.
     */
    void signOutCurrentUser(HttpSession session) {
        String curUid = session.getAttribute(SUBJECT)
        OAuthId id = OAuthId.findBySubject(curUid)

        if(id != null) {
            id.delete(flush: true)
        }
        session.invalidate()
    }

    /**
     * Fetches the current authenticated user if there is one.
     * @param session - The session object to use.
     * @return An optional user.
     */
    Optional<User> getAuthenticatedUser(HttpSession session) {
        String subj = session.getAttribute(SUBJECT)
        Optional<User> result = Optional.empty()

        if (subj != null) {
            OAuthId id = OAuthId.findBySubject(subj)
            if (id != null) {
                User temp = id.user
                if (temp != null) {
                    result = Optional.of(temp)
                } else {
                    signOutCurrentUser(session);
                }
            } else {
                signOutCurrentUser(session)
            }
        }

        result
    }

    Boolean verifiedAllRoles() {
        true
    }

}
