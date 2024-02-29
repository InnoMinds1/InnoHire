package edu.esprit.entities;

public class CurrentUser {
    private static int id_utilisateur = 2216 ;
    private static  int cin= 12345;
    private static String nom="admin";
    private static String prenom;
    private static String adresse;
    private static String mdp;
    private static int role = 0;

    public static int getRole() {
        return role;
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
