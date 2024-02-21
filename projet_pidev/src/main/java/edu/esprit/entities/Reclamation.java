package edu.esprit.entities;

import java.util.Date;
import java.util.Objects;

public class Reclamation {
    public int id_reclamation,status;
    public String type,titre,description;
    public Date date;

    Publication pub;

    Utilisateur user;

    public Reclamation() {
    }

    public Reclamation(int status, String type, String titre, String description,Date date) {
        this.status = status;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.date = date;
    }

    public Reclamation(int status, String type, String titre, String description, Date date, Publication pub, Utilisateur user) {
        this.status = status;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.pub = pub;
        this.user = user;
    }

    public Reclamation(int id_reclamation, int status, String type, String titre, String description, Date date, Publication pub, Utilisateur user) {
        this.id_reclamation = id_reclamation;
        this.status = status;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.pub = pub;
        this.user = user;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Publication getPub() {
        return pub;
    }

    public void setPub(Publication pub) {
        this.pub = pub;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reclamation that = (Reclamation) o;
        return id_reclamation == that.id_reclamation && Objects.equals(pub, that.pub) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_reclamation, pub, user);
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id_reclamation=" + id_reclamation +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", pub=" + pub +
                ", user Info= " + user.getNom()+" " + user.getPrenom() +" Cin :"+ user.getCin() +
                '}'+'\n';
    }
}
