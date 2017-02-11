package com.example

/**
 * Created by brandonlanthrip on 2/9/17.
 */
class Submission {
    boolean correct
    String value
    int pointValue

    static belongsTo = Question
    static hasOne = [question: Question]
}
