package edu.esprit.entities;

public class CurrentUser {
    private static int id_utilisateur =2215;
    private static  int cin=99;
    private static String name="User";
    private static String prenom;
    private static String adresse;
    private static String mdp;
    private static int role = 1;


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

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        CurrentUser.name = name;
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
