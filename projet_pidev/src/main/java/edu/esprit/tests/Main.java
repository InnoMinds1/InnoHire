package edu.esprit.tests;

import edu.esprit.entities.Evenement;

import edu.esprit.entities.Reservation;
import edu.esprit.services.ServiceEvenement;

import edu.esprit.services.ServiceReservation;
import edu.esprit.utils.DataSource;

public class Main {
    public static void main(String[] args) {

        /*
        DataSource ds = new DataSource();
        DataSource ds1 = new DataSource();
        DataSource ds2 = new DataSource();
        DataSource ds3 = new DataSource();
       --> bchaamlk barcha instance w twli bel zhar donc lezem patron de conception singleton
         */


        //ServiceEvenement se = new ServiceEvenement();
        ServiceReservation sr = new ServiceReservation();

        /*----------------ajouter-------------------------*/


        //se.ajouter(new Evenement(23,"ouma","kantawi","24-02-2023","hhh",12));
      //sr.ajouter(new Reservation(2,3));

        /*----------------modifier-------------------------*/

      //Evenement evenementModifiee = new Evenement(1,55,"oumayma","kantawiiiii","24-02-2023","hhh",12);
        //se.modifier(evenementModifiee);
        Reservation reservationModifiee=new Reservation(9,2,3);
       sr.modifier(reservationModifiee);

        /*-------------------Supprimer-------------------*/
      //sr.supprimer(8);

        /*-------------------affichage_par_ID-------------------*/
        //System.out.println(sr.getOneByID(8));

        /*----------------afficher tout-------------------------*/
       System.out.println(sr.getAll());
    }
}
