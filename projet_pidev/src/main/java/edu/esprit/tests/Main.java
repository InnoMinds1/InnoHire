package edu.esprit.tests;

import edu.esprit.entities.Admin;
import edu.esprit.entities.Candidat;
import edu.esprit.entities.Representant;
import edu.esprit.services.ServiceUtilisateur;
import edu.esprit.utils.DataSource;

public class Main {
    public static void main(String[] args)
    {
        DataSource.getInstance();
        ServiceUtilisateur sr= new ServiceUtilisateur();
        Admin a = new Admin(1,234,"AMENALLAH123","KTHIRI","amenallah@esprit.tn","ELKING");
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




    }
}
