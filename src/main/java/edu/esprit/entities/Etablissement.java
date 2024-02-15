package edu.esprit.entities;

import java.util.Objects;

public class Etablissement {
    public int id_etablissement,code_etablissement,id_utilisateur;
    public String nom,lieu,type_etablissement;

    public Etablissement() {
    }

    public Etablissement(int id_etablissement, int code_etablissement, int id_utilisateur, String nom, String lieu, String type_etablissement) {
        this.id_etablissement = id_etablissement;
        this.code_etablissement = code_etablissement;
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.lieu = lieu;
        this.type_etablissement = type_etablissement;
    }

    public int getCode_etablissement() {
        return code_etablissement;
    }

    public void setCode_etablissement(int code_etablissement) {
        this.code_etablissement = code_etablissement;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
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

    public String getType_etablissement() {
        return type_etablissement;
    }

    public void setType_etablissement(String type_etablissement) {
        this.type_etablissement = type_etablissement;
    }

    public int getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etablissement that = (Etablissement) o;
        return id_etablissement == that.id_etablissement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_etablissement);
    }

    @Override
    public String toString() {
        return "Etablissement{" +
                "id_etablissement=" + id_etablissement +
                ", code_etablissement=" + code_etablissement +
                ", id_utilisateur=" + id_utilisateur +
                ", nom='" + nom + '\'' +
                ", lieu='" + lieu + '\'' +
                ", type_etablissement='" + type_etablissement + '\'' +
                '}';
    }
}
