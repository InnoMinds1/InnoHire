package edu.esprit.tests;

import edu.esprit.entities.Etablissement;
import edu.esprit.entities.Quiz;
import edu.esprit.entities.Utilisateur;
import edu.esprit.entities.Wallet;
import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceUtilisateur;
import edu.esprit.services.ServiceWallet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        ServiceUtilisateur su=new ServiceUtilisateur();
        user.setId_utilisateur(2215);
//System.out.println(su.getAll());




        //  se.ajouter(new Etablissement("isie","Ghazela",123456,"Faculte","gg",user));

        /*----------------modifier-------------------------*/
        Etablissement etablissementModifie = new Etablissement(618,"ff","Ghazela",12345,"Faculte","gg",null,user);
        Etablissement etablissementModifie2 = new Etablissement(621,"ff","Ghazela",12345,"Faculte","gg",null,user);

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


      //  System.out.println(se.getQuizzesPourEtablissement(etablissementModifie));

        /*--------------------------------------------------------------------------------------------------------*/
        /*--------------------------------------End_table_etab----------------------------------------------------*/
        /*--------------------------------------------------------------------------------------------------------*/


        LocalDateTime currentDate = LocalDateTime.now();
        ServiceWallet sw = new ServiceWallet();
        Wallet wallet = new Wallet(100, currentDate,1,etablissementModifie);
        Wallet wallet1 = new Wallet(100, currentDate,1,etablissementModifie2);
        Wallet wallet2 = new Wallet(100, currentDate,1,etablissementModifie2);
        Wallet wallet3= new Wallet(100,1,etablissementModifie2);
       // System.out.println(wallet1);



        /*----------------ajouter-------------------------*/

     // sw.ajouter(wallet3);

        /*----------------modifier-------------------------*/

        Wallet walletModifie = new Wallet(30, 500,2, etablissementModifie);

     //  sw.modifier(walletModifie);

        /*-------------------Supprimer-------------------*/
        //sw.supprimer(30);

        /*-------------------affichage_par_ID-------------------*/
        //System.out.println(sw.getOneByID(28));

        /*----------------afficher tout-------------------------*/
       //System.out.println(sw.getAll());

        /*--------------------------------------End_Sayari----------------------------------------------------*/

//System.out.println (se.getWalletByEtablissement(etablissementModifie));
        System.out.println( su.getAll());

    }
}
