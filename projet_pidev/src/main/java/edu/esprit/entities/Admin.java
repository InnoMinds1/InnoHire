package edu.esprit.entities;

import java.util.Objects;

public class Admin extends Utilisateur{
    private static int ROLE=0;

    public static int getROLE() {
        return ROLE;
    }

    public static void setROLE(int ROLE) {
        Admin.ROLE = ROLE;
    }

    public Admin() {
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public String toString() {
        return "Admin{" +

                "cin=" + this.getCin() +
                ", nom='" + this.getNom() + '\'' +
                ", prenom='" + this.getPrenom() + '\'' +
                ", adresse='" + this.getAdresse() + '\'' +
                ", Role='" + "0" + '\'' +


                '}'+'\n';
    }
    public Admin(int cin,String nom ,String prenom,String adresse, String mdp)
    {
        super( cin, nom, prenom, adresse, mdp);


    }

    public Admin(int cin,String nom ,String prenom,String adresse, String mdp,String image)
    {
        super( cin, nom, prenom, adresse, mdp,image);

    }

}
