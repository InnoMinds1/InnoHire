package edu.esprit.tests;

import edu.esprit.entities.*;
import edu.esprit.services.ServicePublication;
import edu.esprit.services.ServiceUtilisateur;
import edu.esprit.utils.DataSource;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args)
    {

        DataSource.getInstance();

        //AJOUT
        ServicePublication sb = new ServicePublication();
       ServiceUtilisateur sr= new ServiceUtilisateur();
        Utilisateur u=new Utilisateur(10,22,"nom","prenom","hh","ppp");
        //Admin a = new Admin(1,234,"AMENALLAH123","KTHIRI","amenallah@esprit.tn","ELKING");
        //sr.ajouter(u);
        Publication p = new Publication(2,u,"bbbbb","hbbbbh","hh","hh", LocalDate.of(2021,02,4),4);
       sb.ajouter(p);
       //sb.supprimer(2);
       //System.out.println(sb.getOneByID(4));
        //System.out.println(sb.getAll());
        //Representant r = new Representant(2,33,"AMENALLAH","KTHIRI","amenallah@esprit.tn","ELKING");
       //sr.ajouter(r);
        //Candidat c= new Candidat(3,322,"YO222","ee","SLFJ@","TRAHHH");
        //sr.ajouter(c);

        //System.out.println(sr.getAll());
        //System.out.println(sr.getAll_admin());
        //sr.modifier(c);
        //sr.modifier_admin(a);
        //sr.supprimer(44);

        //sr.supprimer_admin(44);

        //System.out.println(sr.getOneByID(1));
        //System.out.println(sr.getOneAdminByID(12));





    }
}
