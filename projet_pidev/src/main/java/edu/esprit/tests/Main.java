package edu.esprit.tests;

<<<<<<< HEAD
import com.emailsender.sendemail.SendemailApplication;
import edu.esprit.entities.*;
import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceUtilisateur;
import edu.esprit.utils.DataSource;
import org.springframework.boot.SpringApplication;

import java.sql.SQLException;
import java.util.*;

=======
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import edu.esprit.entities.*;
import edu.esprit.services.ServiceEtablissement;
import edu.esprit.services.ServiceUtilisateur;
import edu.esprit.services.ServiceWallet;
>>>>>>> gestion-etablissement

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
<<<<<<< HEAD
    public static void main(String[] args)
    {
        DataSource.getInstance();

     /* ServiceUtilisateur sr= new ServiceUtilisateur();
        try {
            sr.supprimer_par_cin(33);
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }
        try {
            sr.supprimer_par_cin(12);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }*/
        Utilisateur u = new Utilisateur();
        u.setCin(123);
        Etablissement e = new Etablissement("esprit","lie",133,"er",u);
        ServiceEtablissement se= new ServiceEtablissement();
        try {
            se.ajouter(e);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
      /*  Admin a = new Admin(1,234,"AMENALLAH123","KTHIRI","amenallah@esprit.tn","ELKING");
        //sr.ajouter(a);
        Representant r = new Representant(2,33,"AMENALLAH","KTHIRI","amenallah@esprit.tn","ELKING");
       //sr.ajouter(r);
        Candidat c= new Candidat(3,322,"YO222","ee","SLFJ@","TRAHHH");
        //sr.ajouter(c);

        System.out.println(sr.getAll());
        System.out.println(sr.getAll_admin());
        sr.modifier(c);
        sr.modifier_admin(a);
        sr.supprimer(44);
        sr.supprimer(3);
        sr.supprimer_admin(44);
        sr.supprimer_admin(12);
        System.out.println(sr.getOneByID(1));
        System.out.println(sr.getOneAdminByID(12));
        System.out.println(sr.getOneAdminByID(1));*/


=======
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
        Utilisateur user = new Utilisateur();
        ServiceUtilisateur su = new ServiceUtilisateur();
        user.setId_utilisateur(2215);
//System.out.println(su.getAll());


        //  se.ajouter(new Etablissement("isie","Ghazela",123456,"Faculte","gg",user));

        /*----------------modifier-------------------------*/
        Etablissement etablissementModifie = new Etablissement(618, "ff", "Ghazela", 12345, "Faculte", "gg", null, user);
        Etablissement etablissementModifie2 = new Etablissement(621, "ff", "Ghazela", 12345, "Faculte", "gg", null, user);

        // System.out.println(etablissementModifie);

        Quiz quiz = new Quiz();
        Quiz quiz2 = new Quiz();
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
        Wallet wallet = new Wallet(100, currentDate, 1, etablissementModifie);
        Wallet wallet1 = new Wallet(100, currentDate, 1, etablissementModifie2);
        Wallet wallet2 = new Wallet(100, currentDate, 1, etablissementModifie2);
        Wallet wallet3 = new Wallet(100, 1, etablissementModifie2);
        // System.out.println(wallet1);



        /*----------------ajouter-------------------------*/

        // sw.ajouter(wallet3);

        /*----------------modifier-------------------------*/

        Wallet walletModifie = new Wallet(30, 500, 2, etablissementModifie);

        //  sw.modifier(walletModifie);

        /*-------------------Supprimer-------------------*/
        //sw.supprimer(30);

        /*-------------------affichage_par_ID-------------------*/
        //System.out.println(sw.getOneByID(28));

        /*----------------afficher tout-------------------------*/
        //System.out.println(sw.getAll());

        /*--------------------------------------End_Sayari----------------------------------------------------*/
>>>>>>> gestion-etablissement

//System.out.println (se.getWalletByEtablissement(etablissementModifie));
        // System.out.println( su.getAll());


         //  System.out.println(CurrencyConverter.convertToEUR(95));


    }
}
