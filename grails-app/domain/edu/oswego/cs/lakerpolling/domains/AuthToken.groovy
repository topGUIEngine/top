package edu.oswego.cs.lakerpolling.domains

class AuthToken {

    String subject
    String accessToken

    static belongsTo = [user: User]

    static mapping = {
        version false
    }

    static constraints = {
        subject unique: true, blank: false
        accessToken blank: false
    }
}
