package edu.oswego.cs.lakerpolling.domains

class Question {
    String question
    boolean on
    List<Boolean> answers
    List<Integer> studentAnswers

    static belongsTo = [course: Course]
    static hasMany = [answers: Answer]
    static constraints = {
    }
}
