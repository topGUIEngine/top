package com.example

/**
 * Created by brandonlanthrip on 2/9/17.
 */
class Instructor extends User{

    static hasMany = [quizes: Quiz]
}
