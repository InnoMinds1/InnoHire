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
                "id_utilisateur=" + this.getId_utilisateur() +
                ", cin=" + this.getCin() +
                ", nom='" + this.getName() + '\'' +
                ", prenom='" + this.getPrenom() + '\'' +
                ", adresse='" + this.getAdresse() + '\'' +
                ", mdp='" + this.getMdp() + '\'' +

                '}';
    }

    public Admin(int id_utilisateur,int cin,String name ,String prenom,String adresse, String mdp)
    {
        super(id_utilisateur, cin, name, prenom, adresse, mdp);

    }

}
