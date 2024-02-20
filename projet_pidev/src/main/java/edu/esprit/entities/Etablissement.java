package edu.esprit.entities;

import java.util.Objects;

public class Etablissement {
    private int id_etablissement;
    private String nom;
    private String lieu;
    private int code_etablissement;
    private String type_etablissement;
    private Integer id_utilisateur;

    public Etablissement(int id_etablissement, String nom, String lieu, int code_etablissement, String type_etablissement, Integer id_utilisateur) {
        this.id_etablissement = id_etablissement;
        this.nom = nom;
        this.lieu = lieu;
        this.code_etablissement = code_etablissement;
        this.type_etablissement = type_etablissement;
        this.id_utilisateur = id_utilisateur;
    }

    public Etablissement(String nom, String lieu, int code_etablissement, String type_etablissement, Integer id_utilisateur) {
        this.nom = nom;
        this.lieu = lieu;
        this.code_etablissement = code_etablissement;
        this.type_etablissement = type_etablissement;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
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

    public int getCode_etablissement() {
        return code_etablissement;
    }

    public void setCode_etablissement(int code_etablissement) {
        this.code_etablissement = code_etablissement;
    }

    public String getType_etablissement() {
        return type_etablissement;
    }

    public void setType_etablissement(String type_etablissement) {
        this.type_etablissement = type_etablissement;
    }

    public Integer getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public boolean isIdUtilisateurNull() {
        return id_utilisateur == null;
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
                ", nom='" + nom + '\'' +
                ", lieu='" + lieu + '\'' +
                ", code_etablissement=" + code_etablissement +
                ", type_etablissement='" + type_etablissement + '\'' +
                ", id_utilisateur=" + id_utilisateur +
                "}\n";
    }
}
