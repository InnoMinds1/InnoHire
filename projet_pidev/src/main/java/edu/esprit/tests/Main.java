package edu.esprit.tests;


import edu.esprit.entities.Messagerie;
import edu.esprit.entities.Publication;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceMessagerie;
import edu.esprit.services.ServiceReclamation;
import edu.esprit.utils.DataSource;

import java.util.Date;

public class Main {
    public static void main(String[] args)
    {
        DataSource.getInstance();
        //(int id_utilisateur, int cin, String nom , String prenom, String adresse, String mdp)
        Utilisateur user=new Utilisateur(1,11417264,"dhawadi","hachem","bizerte","123456789");
        Utilisateur user2=new Utilisateur(9,11417264,"dhawadi","hachem","bizerte","123456789");
        Publication pub=new Publication(1,1,5,"mhaf","#mhaf","seen","mhaf.png","19/02/2024");
        ServiceReclamation sr=new ServiceReclamation();
        ServiceMessagerie sm=new ServiceMessagerie();
        //sm.ajouter(new Messagerie("text","chna7welek yal gafsi",new Date(),user,user2));
        //int status, String type, String titre, String description, String image, Date date, Publication pub, Utilisateur user
        //sr.ajouter(new Reclamation(0,"son image","son image","son image",new Date(),pub,user));
        //sr.modifier(new Reclamation(1,1,"spam","modification mhafa","modification","mhaf.png",new Date(),pub,user));
        //ServiceCategorie sc = new ServiceCategorie();
        //sc.ajouter(new Categorie("Web","all belogns to Web"));
        //sc.modifier(new Categorie(1,"new Mobile",sc.getOneByID(1).getDescription()));
        System.out.println(sm.getAll());;
        //System.out.println(sr.getOneByID(1));
        //System.out.println(sc.getOneByID(2));*/

    }
}
