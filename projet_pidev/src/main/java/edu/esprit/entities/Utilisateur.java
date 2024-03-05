package edu.esprit.entities;

<<<<<<< HEAD
import edu.esprit.services.ServiceUtilisateur;

=======
>>>>>>> gestion-etablissement
import java.util.Objects;

public class Utilisateur {

    private int id_utilisateur ;
    private int cin;
    private String nom;
    private String prenom;
    private String adresse;
    private String mdp;
<<<<<<< HEAD
    private String image;
   //this is for modifier usage
    private  String ProfileImagePath;
    static ServiceUtilisateur su = new ServiceUtilisateur();
    public  String getProfileImagePath() {
        return su.getImagefromCin(this.getCin());
    }


=======



    private String image;
>>>>>>> gestion-etablissement
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
<<<<<<< HEAD

=======
>>>>>>> gestion-etablissement
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
<<<<<<< HEAD
    public int getRole() {
        // Assuming Admin, Representant, and Candidat are subclasses of Utilisateur
        if (this instanceof Admin) {
            return 0; // Admin role
        } else if (this instanceof Representant) {
            return 1; // Representant role
        } else if (this instanceof Candidat) {
            return 2; // Candidat role
        } else {
            return -1; // Default role (modify according to your logic)
        }
    }
=======
>>>>>>> gestion-etablissement

    @Override
    public String toString() {
        return "Utilisateur{" +

                ", cin=" + cin +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", mdp='" + mdp + '\'' +
<<<<<<< HEAD
                '}'+'\n';
    }


=======
                ", image='" + image + '\'' +
                '}'+'\n';
    }

>>>>>>> gestion-etablissement
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id_utilisateur == that.id_utilisateur && cin == that.cin && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(adresse, that.adresse) && Objects.equals(mdp, that.mdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_utilisateur, cin, nom, prenom, adresse, mdp);
    }
<<<<<<< HEAD
    public Utilisateur(){}
    public Utilisateur(int id_utilisateur )
    {
        this.id_utilisateur=id_utilisateur;
    }

=======
    public Utilisateur(){};

    public Utilisateur(int cin, String nom, String prenom, String adresse, String mdp) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
    }
>>>>>>> gestion-etablissement
    public Utilisateur(int cin, String nom, String prenom, String adresse, String mdp,String image) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
        this.image=image;
    }
<<<<<<< HEAD

    public Utilisateur(int id_utilisateur, int cin, String nom , String prenom, String adresse, String mdp,String image)
    {
        this.id_utilisateur=id_utilisateur;
        this.cin=cin;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.mdp=mdp;
        this.image=image;

    }
    public Utilisateur(int cin, String nom, String prenom, String adresse, String mdp) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;

    }

=======
>>>>>>> gestion-etablissement
    public Utilisateur(int id_utilisateur, int cin, String nom , String prenom, String adresse, String mdp)
    {
        this.id_utilisateur=id_utilisateur;
        this.cin=cin;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.mdp=mdp;

    }

}
