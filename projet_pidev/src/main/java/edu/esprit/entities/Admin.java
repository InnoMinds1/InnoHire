package edu.esprit.entities;

import java.util.Objects;

public class Admin extends Utilisateur{

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
                ", mdp='" + this.getMdp() + '\'' +

                '}'+'\n';
    }

    public Admin(int cin,String nom ,String prenom,String adresse, String mdp)
    {
        super( cin, nom, prenom, adresse, mdp);

    }

}
