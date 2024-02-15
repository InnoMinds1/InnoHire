package edu.esprit.entities;

import java.util.Objects;

public class Cours {

    private int id_cours,prix;
    private String nom,description,image;

    Categorie cat;
    Etablissement etab;

    public Cours() {
    }

    public Cours(String nom, int prix, String description, String image) {
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public Cours(Categorie cat,Etablissement etab,String nom,int prix,  String description, String image) {
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.cat = cat;
        this.etab=etab;
    }

    public Cours(int id_cours, Categorie cat, Etablissement etab,String nom,int prix, String description, String image) {
        this.id_cours = id_cours;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.cat = cat;
        this.etab=etab;
    }

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public Etablissement getEtab() {
        return etab;
    }

    public void setEtab(Etablissement etab) {
        this.etab = etab;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return id_cours == cours.id_cours && Objects.equals(cat, cours.cat) && Objects.equals(etab, cours.etab);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cours, cat, etab);
    }

    @Override
    public String toString() {
        return "Cours{" +
                "prix=" + prix +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", cat=" + cat +
                ", etab=" + etab +
                '}';
    }
}
