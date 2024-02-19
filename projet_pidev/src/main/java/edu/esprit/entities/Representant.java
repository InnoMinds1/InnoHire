package edu.esprit.entities;

public class Representant extends Utilisateur{
    private static int ROLE=0;

    public static int getROLE() {
        return ROLE;
    }

    public static void setROLE(int ROLE) {
        Representant.ROLE = ROLE;
    }

    public Representant() {
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
        return "Representant{" +
                "id_utilisateur=" + this.getId_utilisateur() +
                ", cin=" + this.getCin() +
                ", nom='" + this.getNom() + '\'' +
                ", prenom='" + this.getPrenom() + '\'' +
                ", adresse='" + this.getAdresse() + '\'' +
                ", mdp='" + this.getMdp() + '\'' +
                ", role='" + Representant.getROLE() + '\'' +
                '}';
    }

   /* public Representant(int id_utilisateur, int cin, String nom , String prenom, String adresse, String mdp)
    {
        super(id_utilisateur, cin, nom, prenom, adresse, mdp);
        this.ROLE=0;
    }*/
    public Representant(int cin, String nom , String prenom, String adresse, String mdp)
    {
        super(cin,nom,prenom, adresse, mdp);
        this.ROLE=0;
    }



}