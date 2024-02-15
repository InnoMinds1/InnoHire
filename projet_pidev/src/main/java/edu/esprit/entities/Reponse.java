package edu.esprit.entities;

import java.util.Objects;

public class Reponse {
    private int id_reponse;
    private int choix_correcte;
    private int id_question;

    public Reponse(int id_reponse, int choix_correcte, int id_question) {
        this.id_reponse = id_reponse;
        this.choix_correcte = choix_correcte;
        this.id_question = id_question;
    }
    public Reponse ()
    {

    }

    public int getId_reponse() {
        return id_reponse;
    }

    public int getChoix_correcte() {
        return choix_correcte;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public void setChoix_correcte(int choix_correcte) {
        this.choix_correcte = choix_correcte;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reponse reponse = (Reponse) o;
        return id_reponse == reponse.id_reponse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_reponse);
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id_reponse=" + id_reponse +
                ", choix_correcte=" + choix_correcte +
                ", id_question=" + id_question +
                '}';
    }

}
