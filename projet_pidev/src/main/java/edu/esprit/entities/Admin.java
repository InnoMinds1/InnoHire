package edu.esprit.entities;

import java.util.Objects;

public class Admin extends Utilisateur{
<<<<<<< HEAD
    private  int ROLE=0;

    public int getROLE() {
        return ROLE;
    }

=======
>>>>>>> gestion-etablissement

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
<<<<<<< HEAD
                ", Role='" + "0" + '\'' +


                '}'+'\n';
    }
    public Admin(int cin,String nom ,String prenom,String adresse, String mdp)
    {
        super( cin, nom, prenom, adresse, mdp);
        this.ROLE=0;


    }

    public Admin(int cin,String nom ,String prenom,String adresse, String mdp,String image)
    {
        super( cin, nom, prenom, adresse, mdp,image);
        this.ROLE=0;
=======
                ", mdp='" + this.getMdp() + '\'' +

                '}'+'\n';
    }

    public Admin(int cin,String nom ,String prenom,String adresse, String mdp)
    {
        super( cin, nom, prenom, adresse, mdp);
>>>>>>> gestion-etablissement

    }

}
