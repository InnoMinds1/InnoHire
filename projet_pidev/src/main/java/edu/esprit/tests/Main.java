package edu.esprit.tests;

import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Quiz;
import edu.esprit.entities.Utilisateur;
import edu.esprit.entities.Wallet;
import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceWallet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
       // System.out.println(se.getOneByCode(524));

        //----------------ajouter-------------------------

        //
        //
        //
        //
        //
        //
        Utilisateur user=new Utilisateur();
        user.setId_utilisateur(2215);





        //  se.ajouter(new Etablissement("isie","Ghazela",123456,"Faculte","gg",user));

        /*----------------modifier-------------------------*/
        Etablissement etablissementModifie = new Etablissement(46,"ff","Ghazela",12345,"Faculte","gg",null,user);
       // System.out.println(etablissementModifie);

        Quiz quiz =new Quiz();
        Quiz quiz2 =new Quiz();
        quiz.setPrix_quiz(1);
        quiz2.setPrix_quiz(2);
        etablissementModifie.addQuiz(quiz);
        etablissementModifie.addQuiz(quiz2);
        //System.out.println(etablissementModifie);
         //se.modifier(etablissementModifie);

        /*-------------------Supprimer-------------------*/
        //se.supprimer(42);

        /*-------------------affichage_par_ID-------------------*/
      //  System.out.println(se.getOneByID(46));

        /*----------------afficher tout-------------------------*/
      //  System.out.println(se.getAll());


        System.out.println(se.getQuizzesPourEtablissement(etablissementModifie));

        /*--------------------------------------------------------------------------------------------------------*/
        /*--------------------------------------End_table_etab----------------------------------------------------*/
        /*--------------------------------------------------------------------------------------------------------*/



        ServiceWallet sw = new ServiceWallet();
        Wallet wallet = new Wallet(1,100, LocalDate.of(2021,02,4),0,etablissementModifie);
        Wallet wallet1 = new Wallet(2,100, LocalDate.of(2021,02,4),0,etablissementModifie);
        Wallet wallet2 = new Wallet(3,100, LocalDate.of(2021,02,4),0,etablissementModifie);


        /*----------------ajouter-------------------------*/

      //sw.ajouter(wallet);

        /*----------------modifier-------------------------*/

       Wallet walletModifie = new Wallet(17,400, LocalDate.of(2021,02,4),0,etablissementModifie);
      //  sw.modifier(walletModifie);

        /*-------------------Supprimer-------------------*/
        //sw.supprimer(17);

        /*-------------------affichage_par_ID-------------------*/
      //  System.out.println(sw.getOneByID(18));

        /*----------------afficher tout-------------------------*/
      // System.out.println(sw.getAll());


        /*--------------------------------------End_Sayari----------------------------------------------------*/


    }
}
