package edu.oswego.cs.lakerpolling.domains


/**
 * Created by Josh on 3/2/17.
 */
class Semester {
    String year
    Season season

    static hasMany = [course: Course]

    static mapping = {
        version false
    }

    static constraints = {
        year nullable: false
        season nullable: false
    }
}

