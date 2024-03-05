package edu.esprit.entities;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Set;
import edu.esprit.entities.Utilisateur;

public class Etablissement {
    private int id_etablissement;
    private String nom;
    private String lieu;
    private int code_etablissement;
    private String type_etablissement;
    //image
    private Utilisateur user;

    public Etablissement() {

    }

    public Etablissement(int id_etablissement, String nom, String lieu, int code_etablissement, String type_etablissement, Utilisateur user) {
        this.id_etablissement = id_etablissement;
        this.nom = nom;
        this.lieu = lieu;
        this.code_etablissement = code_etablissement;
        this.type_etablissement = type_etablissement;
        this.user = user;
    }

    public Etablissement(String nom, String lieu, int code_etablissement, String type_etablissement, Utilisateur user) {
        this.nom = nom;
        this.lieu = lieu;
        this.code_etablissement = code_etablissement;
        this.type_etablissement = type_etablissement;
        this.user = user;
    }

    public Utilisateur getUser() {
        return user;
    }



    public void setUser(Utilisateur user) {
        this.user = user;
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

                "nom='" + nom + '\'' +
                ", lieu='" + lieu + '\'' +
                ", code_etablissement=" + code_etablissement +  '\''+
                ", type_etablissement='" + type_etablissement + '\'' +
                ", Cin_utilisateur=" + user.getCin() +
                '}'+'\n';
    }



}
