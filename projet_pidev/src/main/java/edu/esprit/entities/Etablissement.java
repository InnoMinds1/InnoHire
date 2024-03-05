package edu.esprit.entities;

import java.sql.SQLException;
<<<<<<< HEAD
import java.util.Objects;
import java.util.Set;
import edu.esprit.entities.Utilisateur;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

>>>>>>> gestion-etablissement

public class Etablissement {


    private int idEtablissement;
    private String nom;
    private String lieu;
<<<<<<< HEAD
    private int code_etablissement;
    private String type_etablissement;
    //image
    private Utilisateur user;

=======
    private int codeEtablissement;
    private String typeEtablissement;

    private String image;



    private List<Quiz> listeQuizzAchetes;

    private Utilisateur user;







>>>>>>> gestion-etablissement
    public Etablissement() {

    }

<<<<<<< HEAD
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
=======
    public Etablissement(int idEtablissement, String nom, String lieu, int codeEtablissement, String typeEtablissement,String image, List<Quiz> listeQuizzAchetes, Utilisateur user) {
        this.idEtablissement = idEtablissement;
        this.nom = nom;
        this.lieu = lieu;
        this.codeEtablissement = codeEtablissement;
        this.typeEtablissement = typeEtablissement;
        this.image=image;
        this.listeQuizzAchetes = (listeQuizzAchetes != null) ? new ArrayList<>(listeQuizzAchetes) : new ArrayList<>();
        this.user = user;


    }

    public Etablissement(String nom, String lieu, int code_etablissement, String type_etablissement,String image, Utilisateur user ) {
        this.nom = nom;
        this.lieu = lieu;
        this.codeEtablissement = code_etablissement;
        this.typeEtablissement = type_etablissement;
        this.image = image;
        this.listeQuizzAchetes = (listeQuizzAchetes != null) ? new ArrayList<>(listeQuizzAchetes) : new ArrayList<>();

        this.user = user;

>>>>>>> gestion-etablissement
    }

    public Utilisateur getUser() {
        return user;
    }



    public void setUser(Utilisateur user) {
        this.user = user;
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

    public int getCodeEtablissement() {
        return codeEtablissement;
    }

    public void setCodeEtablissement(int codeEtablissement) {
        this.codeEtablissement = codeEtablissement;
    }

    public String getTypeEtablissement() {
        return typeEtablissement;
    }

    public void setTypeEtablissement(String typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }

<<<<<<< HEAD





=======

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getIdEtablissement() {
        return idEtablissement;
    }
>>>>>>> gestion-etablissement

    public void setIdEtablissement(int idEtablissement) {
        this.idEtablissement = idEtablissement;
    }


    public List<Quiz> getListeQuizzAchetes() {
        return listeQuizzAchetes;
    }

    public void setListeQuizzAchetes(List<Quiz> listeQuizzAchetes) {
        this.listeQuizzAchetes = listeQuizzAchetes;
    }

    public void addQuiz(Quiz quiz) {
        listeQuizzAchetes.add(quiz);
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etablissement that = (Etablissement) o;
        return idEtablissement == that.idEtablissement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEtablissement);
    }

    @Override
    public String toString() {
        return "Etablissement{" +

                "nom='" + nom + '\'' +
                ", lieu='" + lieu + '\'' +
<<<<<<< HEAD
                ", code_etablissement=" + code_etablissement +  '\''+
                ", type_etablissement='" + type_etablissement + '\'' +
                ", Cin_utilisateur=" + user.getCin() +
=======
                ", codeEtablissement=" + codeEtablissement +  '\''+
                ", typeEtablissement='" + typeEtablissement + '\'' +
                ", image=" + image +
                ", listeQuizzAchetes=" + listeQuizzAchetes +

                ", Cin_utilisateur=" + user.getCin() +

>>>>>>> gestion-etablissement
                '}'+'\n';
    }



}
