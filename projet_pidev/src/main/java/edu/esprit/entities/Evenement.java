package edu.esprit.entities;

import java.util.Objects;

public class Evenement {
    private  int id_evenement ;
    private  int code_evenement;
    private String nom ;
    private String lieu ;
    private String date ;
    private String description ;
    private int nb_participants;

    public Evenement() {
    }

    public Evenement(int id_evenement, int code_evenement, String nom, String lieu, String date, String description, int nb_participants) {
        this.id_evenement = id_evenement;
        this.code_evenement = code_evenement;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.nb_participants = nb_participants;
    }

    public Evenement(int code_evenement, String nom, String lieu, String date, String description, int nb_participants) {
        this.code_evenement = code_evenement;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.nb_participants = nb_participants;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getCode_evenement() {
        return code_evenement;
    }

    public void setCode_evenement(int code_evenement) {
        this.code_evenement = code_evenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evenement evenement = (Evenement) o;
        return id_evenement == evenement.id_evenement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_evenement);
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id_evenement=" + id_evenement +
                ", code_evenement=" + code_evenement +
                ", nom='" + nom + '\'' +
                ", lieu='" + lieu + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", nb_participants=" + nb_participants +
                '}';
    }
}
