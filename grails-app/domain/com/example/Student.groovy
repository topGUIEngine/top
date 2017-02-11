package com.example

/**
 * Created by brandonlanthrip on 2/9/17.
 */
class Student extends User{

    static hasMany = [courses: Course, submissions: Submission]
}
