package edu.esprit.tests;


import edu.esprit.entities.Publication;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceReclamation;
import edu.esprit.utils.DataSource;

import java.util.Date;

public class Main {
    public static void main(String[] args)
    {
        DataSource.getInstance();
        //(int id_utilisateur, int cin, String nom , String prenom, String adresse, String mdp)
        Utilisateur user=new Utilisateur(1,11417264,"dhawadi","hachem","bizerte","123456789");
        Publication pub=new Publication(1,1,5,"mhaf","#mhaf","seen","mhaf.png","19/02/2024");
        ServiceReclamation sr=new ServiceReclamation();
        //int status, String type, String titre, String description, String image, Date date, Publication pub, Utilisateur user
        sr.ajouter(new Reclamation(0,"spam","bleda mhafa","description","mhaf.png",new Date(),pub,user));
        //ServiceCategorie sc = new ServiceCategorie();
        //sc.ajouter(new Categorie("Web","all belogns to Web"));
        //sc.modifier(new Categorie(1,"new Mobile",sc.getOneByID(1).getDescription()));
        //sc.supprimer(1);
        //System.out.println(sc.getOneByID(2));*/

    }
}
