package edu.esprit.entities;

import java.util.Objects;

public class Question {
    private int id_question;
    private String question;
    private String choix;
    private int id_quiz;
    public Question ()
    {

    }
    public Question(String question, String choix, int id_quiz) {
        this.question = question;
        this.choix = choix;
        this.id_quiz = id_quiz;
    }

    public Question(int id_question, String question, String choix, int id_quiz) {
        this.id_question = id_question;
        this.question = question;
        this.choix = choix;
        this.id_quiz = id_quiz;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public int getId_question() {
        return id_question;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoix() {
        return choix;
    }

    public int getId_quiz() {
        return id_quiz;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return id_question == question1.id_question && id_quiz == question1.id_quiz && Objects.equals(question, question1.question) && Objects.equals(choix, question1.choix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_question, question, choix, id_quiz);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", choix='" + choix + '\'' +
                ", id_quiz=" + id_quiz +
                '}';
    }
}
