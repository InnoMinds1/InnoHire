package edu.esprit.entities;

import edu.esprit.services.ServiceUtilisateur;

public class CurrentUser {
    private static int id_utilisateur = 2 ;
    private static  int cin = 11423569;
    private static String nom = "amen";
    private static String prenom ="Kthiri";
    private static String adresse="AmenKthiri@esprit.tn";
    private static String mdp="fgffdgdfg";
    private static int role=2;
    private static String ProfileImagePath;
    static ServiceUtilisateur su = new ServiceUtilisateur();

    public static int getRole() {
        return role;
    }

    public static String getProfileImagePath() {
        return su.getImagefromCin(cin);
    }

    public static void setProfileImagePath(String profileImagePath) {
        ProfileImagePath = profileImagePath;
    }

    public static void setRole(int role) {
        CurrentUser.role = role;
    }

    public CurrentUser() {
    }

    public static int getId_utilisateur() {
        return id_utilisateur;
    }

    public static void setId_utilisateur(int id_utilisateur) {
        CurrentUser.id_utilisateur = id_utilisateur;
    }

    public static int getCin() {
        return cin;
    }

    public static void setCin(int cin) {
        CurrentUser.cin = cin;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        CurrentUser.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        CurrentUser.prenom = prenom;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        CurrentUser.adresse = adresse;
    }

    public static String getMdp() {
        return mdp;
    }

    public static void setMdp(String mdp) {
        CurrentUser.mdp = mdp;
    }
}
