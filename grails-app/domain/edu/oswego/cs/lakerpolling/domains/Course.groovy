package edu.oswego.cs.lakerpolling.domains


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
