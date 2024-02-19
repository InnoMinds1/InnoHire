package edu.esprit.tests;

import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Wallet;
import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceWallet;

public class Main {
    public static void main(String[] args)
    {


/*----------------------------------------------------Sayari ---------------------------------------*/



        /*
        DataSource ds = new DataSource();
        DataSource ds1 = new DataSource();
        DataSource ds2 = new DataSource();
        DataSource ds3 = new DataSource();
       --> bchaamlk barcha instance w twli bel zhar donc lezem patron de conception singleton
         */

        ServiceEtablissement se = new ServiceEtablissement();

        /*----------------ajouter-------------------------*/

        se.ajouter(new Etablissement("isie","Ghazela",123,"Faculte",null));

        /*----------------modifier-------------------------*/

        Etablissement etablissementModifie = new Etablissement(1,"test","Ghazela",123,"Faculte",1);
         //se.modifier(etablissementModifie);

        /*-------------------Supprimer-------------------*/
        //se.supprimer(1);

        /*-------------------affichage_par_ID-------------------*/
        //System.out.println(se.getOneByID(2));

        /*----------------afficher tout-------------------------*/
        System.out.println(se.getAll());

        /*--------------------------------------------------------------------------------------------------------*/
        /*--------------------------------------End_table_etab----------------------------------------------------*/
        /*--------------------------------------------------------------------------------------------------------*/



        ServiceWallet sw = new ServiceWallet();

        /*----------------ajouter-------------------------*/

        //sw.ajouter(new Wallet(100,1));

        /*----------------modifier-------------------------*/

        Wallet walletModifie = new Wallet(3,200,7);
       // sw.modifier(walletModifie);

        /*-------------------Supprimer-------------------*/
       // sw.supprimer(4);

        /*-------------------affichage_par_ID-------------------*/
        //System.out.println(sw.getOneByID(3));

        /*----------------afficher tout-------------------------*/
      //  System.out.println(sw.getAll());

        /*--------------------------------------End_Sayari----------------------------------------------------*/


    }
}
