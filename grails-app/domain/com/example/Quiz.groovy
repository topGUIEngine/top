package com.example


class Quiz {
    String name

    static hasOne = [course: Course, instructor: Instructor]
    static belongsTo = [Course, Instructor]
    static hasMany = [questions: Question]
}
