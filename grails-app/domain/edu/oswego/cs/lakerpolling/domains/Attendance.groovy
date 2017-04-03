package edu.oswego.cs.lakerpolling.domains

/**
 * Created by Josh on 3/27/17.
 */
class Attendance {
    Date date

    static belongsTo = [course: Course]
    static hasMany = [attendees: Attendee]

    static mapping = {
        version false
        id composite: ['date', 'course']
    }

    static constraints = {
        date blank: false
    }
}
