package edu.esprit.tests;

import edu.esprit.entities.*;
import edu.esprit.services.ServiceCommentaire;
import edu.esprit.services.ServicePublication;
import edu.esprit.services.ServiceUtilisateur;
import edu.esprit.utils.DataSource;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args)
    {

        DataSource.getInstance();

        //AJOUT
        //ServicePublication sb = new ServicePublication();
        Utilisateur u=new Utilisateur(10,22,"nom","prenom","hh","ppp");



        ServicePublication sb = new ServicePublication();
        ServiceUtilisateur sr= new ServiceUtilisateur();
        ServiceCommentaire sc=new ServiceCommentaire();

        //---------------------Publication---------------------------------------//

        Publication p = new Publication("22",u,"22","11","444","111",LocalDate.of(2021,02,4),125);
        //sb.ajouter(p);
        //sb.modifier(p);
        //sb.supprimer(10);
        //System.out.println(sb.getOneByID(11));
        //System.out.println(sb.getAll());

        //----------------------Commentaire--------------------------------------//
        Publication pc = new Publication(11,"22",u,"22","11","444","111",LocalDate.of(2021,02,4),125);
        Commentaire c=new Commentaire(pc,u,"ccccc", LocalDate.of(2023,02,4));

        //sc.ajouter(c);
        //sc.modifier(c);
        //sc.supprimer(10);
        //System.out.println(sc.getOneByID(10));
        //System.out.println(sc.getAll());







        //Admin a = new Admin(1,234,"AMENALLAH123","KTHIRI","amenallah@esprit.tn","ELKING");
        //sr.ajouter(u);
        //sb.ajouter(p);
        //Publication p = new Publication(4,u,"bbbbb","hbbbbh","hh","hh", LocalDate.of(2021,02,4),4);
        //sb.ajouter(p)
       //sb.supprimer(2);
       //System.out.println(sb.getOneByID(4));
        //System.out.println(sb.getAll());
        //Representant r = new Representant(2,33,"AMENALLAH","KTHIRI","amenallah@esprit.tn","ELKING");
       //sr.ajouter(r);
        //Candidat c= new Candidat(3,322,"YO222","ee","SLFJ@","TRAHHH");
        //sr.ajouter(c);
        //sc.modifier(c);
        //System.out.println(sc.getAll());
        //System.out.println(sr.getAll_admin());
        //sr.modifier(c);
        //sr.modifier_admin(a);
        //sr.supprimer(44);

        //sr.supprimer_admin(44);

        //System.out.println(sc.getAll());
        //System.out.println(sr.getOneAdminByID(12));





    }
}
