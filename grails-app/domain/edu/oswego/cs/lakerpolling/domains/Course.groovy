package edu.oswego.cs.lakerpolling.domains

/**
 * Created by Josh on 3/2/17.
 */
class Course {
    String name
    String CRN



    static mapping = {
        id column: 'CRN', type: 'String'
    }

    static constraints = {
        name nullable: true
    }
}
