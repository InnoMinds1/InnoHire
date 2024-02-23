package edu.esprit.tests;

import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Utilisateur;
import edu.esprit.entities.Wallet;
import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceWallet;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


/*----------------------------------------------------Sayari ---------------------------------------*/



        /*
        DataSource ds = new DataSource();
        DataSource ds1 = new DataSource();
        DataSource ds2 = new DataSource();
        DataSource ds3 = new DataSource();
       --> bchaamlk barcha instance w twli bel zhar donc lezem patron de conception singleton
         */

        ServiceEtablissement se = new ServiceEtablissement();
        System.out.println(se.getOneByCode(524));

        //----------------ajouter-------------------------

        //
        //
        //
        //
        //
        //
        Utilisateur user=new Utilisateur();
        user.setId_utilisateur(19);

          //se.ajouter(new Etablissement("isie","Ghazela",12345,"Faculte",user));

        /*----------------modifier-------------------------*/

        Etablissement etablissementModifie = new Etablissement(35,"ff","Ghazela",12345,"Faculte",user);
         //se.modifier(etablissementModifie);

        /*-------------------Supprimer-------------------*/
        //se.supprimer(1);

        /*-------------------affichage_par_ID-------------------*/
        //System.out.println(se.getOneByID(2));

        /*----------------afficher tout-------------------------*/
      //  System.out.println(se.getAll());

        /*--------------------------------------------------------------------------------------------------------*/
        /*--------------------------------------End_table_etab----------------------------------------------------*/
        /*--------------------------------------------------------------------------------------------------------*/



        ServiceWallet sw = new ServiceWallet();

        /*----------------ajouter-------------------------*/

        sw.ajouter(new Wallet(100,etablissementModifie));

        /*----------------modifier-------------------------*/

        Wallet walletModifie = new Wallet(3,200,etablissementModifie);
       // sw.modifier(walletModifie);

        /*-------------------Supprimer-------------------*/
       // sw.supprimer(4);

        /*-------------------affichage_par_ID-------------------*/
        //System.out.println(sw.getOneByID(3));

        /*----------------afficher tout-------------------------*/
      // System.out.println(sw.getAll());


        /*--------------------------------------End_Sayari----------------------------------------------------*/


    }
}
