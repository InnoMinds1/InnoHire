package edu.esprit.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Publication {
    private int id_publication ;
    private  Utilisateur utilisateur ;
    private String description ;
    private String hashtag ;
    private String visibilite ;
    private String image ;
    private LocalDate date ;
    private int nb_report ;


    public Publication() {
    }

    public Publication(int id_publication, Utilisateur utilisateur, String description, String hashtag, String visibilite, String image, LocalDate date, int nb_report) {
        this.id_publication = id_publication;
        this.utilisateur = utilisateur;
        this.description = description;
        this.hashtag = hashtag;
        this.visibilite = visibilite;
        this.image = image;
        this.date = date;
        this.nb_report = nb_report;
    }

    public Publication(int id_publication, String description, String hashtag, String visibilite, String image, LocalDate date, int nb_report) {
        this.id_publication = id_publication;
        this.description = description;
        this.hashtag = hashtag;
        this.visibilite = visibilite;
        this.image = image;
        this.date = date;
        this.nb_report = nb_report;
    }

    public Publication(Utilisateur utilisateur, String description, String hashtag, String visibilite, String image, LocalDate date, int nb_report) {
        this.utilisateur = utilisateur;
        this.description = description;
        this.hashtag = hashtag;
        this.visibilite = visibilite;
        this.image = image;
        this.date = date;
        this.nb_report = nb_report;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(String visibilite) {
        this.visibilite = visibilite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNb_report() {
        return nb_report;
    }

    public void setNb_report(int nb_report) {
        this.nb_report = nb_report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return id_publication == that.id_publication;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_publication);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id_publication=" + id_publication +
                ", description='" + description + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", visibilite='" + visibilite + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", nb_report=" + nb_report +
                '}';
    }
}
