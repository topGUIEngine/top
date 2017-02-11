package com.example
/**
 * Created by brandonlanthrip on 2/9/17.
 */
class Question {
    String value
    int totalPoints

    static hasOne = [quiz:Quiz]
    static belongsTo = Quiz
    static hasMany = [submissions: Submission]
}
