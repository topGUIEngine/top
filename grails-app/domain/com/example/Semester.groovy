package com.example

/**
 * Created by brandonlanthrip on 2/9/17.
 */
class Semester {
    String year
    TypeOfSemester type

    static hasMany = [courses: Course]
}
