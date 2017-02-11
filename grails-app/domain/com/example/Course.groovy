package com.example

/**
 * Created by brandonlanthrip on 2/9/17.
 */
class Course {
    String name
    static belongsTo = [Semester]
    static hasMany = [quizes: Quiz]
}
