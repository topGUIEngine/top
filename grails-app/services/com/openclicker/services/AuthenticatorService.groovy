package com.openclicker.services

import com.openclicker.models.authentication.OAuthId
import com.openclicker.models.authentication.User
import grails.transaction.Transactional

import javax.servlet.http.HttpSession

@Transactional
class AuthenticatorService {

    private static String SUBJECT = "userId"

    User getOrMakerUser(String email, String first, String last) {
        User temp = User.findByEmail(email)
        if (temp == null) {
            temp = new User(email: email, firstName: first, lastName: last).save(flush: true, failOnError:true)
            println("-------------")
            println(temp)
        }
        temp
    }

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

    void signOutCurrentUser(HttpSession session) {
        String curUid = session.getAttribute(SUBJECT)
        OAuthId id = OAuthId.findBySubject(curUid)

        if(id != null) {
            id.delete(flush: true)
        }
        session.invalidate()
    }

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
