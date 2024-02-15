package edu.esprit.tests;

import edu.esprit.entities.Etablissement;
import edu.esprit.services.ServiceEtablissement;

public class Main {
    public static void main(String[] args)
    {


/*----------------------------------------------------Sayari---------------------------------------*/

        /*
        DataSource ds = new DataSource();
        DataSource ds1 = new DataSource();
        DataSource ds2 = new DataSource();
        DataSource ds3 = new DataSource();
       --> bchaamlk barcha instance w twli bel zhar donc lezem patron de conception singleton
         */

        ServiceEtablissement se = new ServiceEtablissement();

        /*----------------ajouter-------------------------*/

        //se.ajouter(new Etablissement("isie","Ghazela",123,"Faculte",2));

        /*----------------modifier-------------------------*/

        Etablissement etablissementModifie = new Etablissement(1,"test","Ghazela",123,"Faculte",1);
         //se.modifier(etablissementModifie);

        /*-------------------Supprimer-------------------*/
        //se.supprimer(1);

        /*-------------------affichage_par_ID-------------------*/
        //System.out.println(se.getOneByID(2));

        /*----------------afficher tout-------------------------*/
        System.out.println(se.getAll());

        /*--------------------------------------End_Sayari----------------------------------------------------*/


    }
}
