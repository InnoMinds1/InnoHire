package edu.esprit.entities;

import java.util.Objects;

public class Utilisateur {

    private int id_utilisateur ;
    private int cin;
    private String name;
    private String prenom;
    private String adresse;
    private String mdp;
    private int role;

    private String profileImg;
    private int verified ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        verified = verified;
    }



    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }



    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id_utilisateur=" + id_utilisateur +
                ", cin=" + cin +
                ", name='" + name + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id_utilisateur == that.id_utilisateur && cin == that.cin && Objects.equals(name, that.name) && Objects.equals(prenom, that.prenom) && Objects.equals(adresse, that.adresse) && Objects.equals(mdp, that.mdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_utilisateur, cin, name, prenom, adresse, mdp);
    }
    public Utilisateur(){};
    public Utilisateur(int id_utilisateur,int cin,String name ,String prenom,String adresse, String mdp)
    {
        this.id_utilisateur=id_utilisateur;
        this.cin=cin;
        this.name=name;
        this.prenom=prenom;
        this.adresse=adresse;
        this.mdp=mdp;

    }

    public Utilisateur(int id_utilisateur, int cin, String name, String prenom, String adresse, String mdp, int role, String profileImg, int verified) {
        this.id_utilisateur = id_utilisateur;
        this.cin = cin;
        this.name = name;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
        this.role = role;
        this.profileImg = profileImg;
        this.verified = verified;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
