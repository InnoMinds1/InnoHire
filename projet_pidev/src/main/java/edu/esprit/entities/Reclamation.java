package edu.esprit.entities;

import java.util.Objects;

public class Reclamation {
    public int id_reclamation;
    public String titre,description;

    Categorie cat;
    Commentaire com;
    Publication pub;

    public Reclamation() {
    }

    public Reclamation(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Reclamation(String titre, String description, Categorie cat, Commentaire com, Publication pub) {
        this.titre = titre;
        this.description = description;
        this.cat = cat;
        this.com = com;
        this.pub = pub;
    }

    public Reclamation(int id_reclamation, String titre, String description, Categorie cat, Commentaire com, Publication pub) {
        this.id_reclamation = id_reclamation;
        this.titre = titre;
        this.description = description;
        this.cat = cat;
        this.com = com;
        this.pub = pub;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
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

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public Commentaire getCom() {
        return com;
    }

    public void setCom(Commentaire com) {
        this.com = com;
    }

    public Publication getPub() {
        return pub;
    }

    public void setPub(Publication pub) {
        this.pub = pub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reclamation that = (Reclamation) o;
        return id_reclamation == that.id_reclamation && Objects.equals(cat, that.cat) && Objects.equals(com, that.com) && Objects.equals(pub, that.pub);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_reclamation, cat, com, pub);
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id_reclamation=" + id_reclamation +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", cat=" + cat +
                ", com=" + com +
                ", pub=" + pub +
                '}';
    }
}
