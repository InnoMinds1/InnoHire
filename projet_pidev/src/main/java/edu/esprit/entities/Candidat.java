package edu.esprit.entities;

public class Candidat extends Utilisateur{
    private static int ROLE=1;

    public static int getROLE() {
        return ROLE;
    }

    public static void setROLE(int ROLE) {
        Candidat.ROLE = ROLE;
    }
    public Candidat() {
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
        return "Candidat{" +
                "id_utilisateur=" + this.getId_utilisateur() +
                ", cin=" + this.getCin() +
                ", nom='" + this.getNom() + '\'' +
                ", prenom='" + this.getPrenom() + '\'' +
                ", adresse='" + this.getAdresse() + '\'' +
                ", mdp='" + this.getMdp() + '\'' +
                ", role='" + Candidat.getROLE() + '\'' +
                '}';
    }

    public Candidat(int id_utilisateur,int cin,String nom ,String prenom,String adresse, String mdp)
    {
        super(id_utilisateur, cin, nom, prenom, adresse, mdp);
        this.ROLE=1;
    }


}
