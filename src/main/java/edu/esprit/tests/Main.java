package edu.esprit.tests;

import edu.esprit.entities.Categorie;
import edu.esprit.entities.Cours;
import edu.esprit.entities.Etablissement;
import edu.esprit.services.ServiceCategorie;
import edu.esprit.services.ServiceCours;
import edu.esprit.utils.DataSource;

public class Main {
    public static void main(String[] args) {
        /*DataSource.getInstance(); Test connexion base
        Categorie c = new Categorie(1,"java","java cours");
        System.out.println(c);*/

        /*ServiceCategorie sc = new ServiceCategorie();
        //sc.ajouter(new Categorie("Web","all belogns to Web"));
        //sc.modifier(new Categorie(1,"new Mobile",sc.getOneByID(1).getDescription()));
        //sc.supprimer(1);
        System.out.println(sc.getOneByID(2));*/

        //Categorie c = new Categorie(2,"Web","Web cours");
        //Etablissement e = new Etablissement(3,2024,1,"la5dher","soussa","edu");
        //int id_etablissement, int code_etablissement, int id_utilisateur, String nom, String lieu, String type_etablissement
        //Cours cs = new Cours(c,e,"PHP",250,"php cours","php.png");
        //System.out.println(cs);
        //sc.ajouter(new Cours(c,e,"React",1500,"Cours React","React.png"));
        /*ServiceCategorie scat = new ServiceCategorie();
        Categorie catMobile = scat.getOneByID(2);
        Categorie catWeb = scat.getOneByID(3);*/
        //ServiceCours sc = new ServiceCours();
        //sc.modifier(new Cours(8,catWeb,e,"Symfony",9500,"Symfony cours","Symfony.png"));
        //System.out.println(sc.getAll());
        //System.out.println(sc.getOneByID(8));
        //sc.supprimer(8);
    }
}
