package edu.esprit.services;

import edu.esprit.entities.Admin;
import edu.esprit.entities.Candidat;
import edu.esprit.entities.Representant;
import edu.esprit.entities.Utilisateur;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceUtilisateur implements IService<Utilisateur> {
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Utilisateur utilisateur) {
        if (utilisateur instanceof Admin)
        { Admin a= (Admin) utilisateur;
            String req ="INSERT INTO `admin`( `cin`, `nom`, `prenom`, `email`, `mdp`) VALUES (?,?,?,?,?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1,a.getCin());
                ps.setString(2, a.getNom());
                ps.setString(3, a.getPrenom());
                ps.setString(4, a.getAdresse());
                ps.setString(5, a.getMdp());
                ps.executeUpdate();
                System.out.println("Admin added");


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
         String req = "INSERT INTO `utilisateur`( `cin`, `nom`, `prenom`, `adresse`, `mdp`, `role`) VALUES (?,?,?,?,?,?)";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1,utilisateur.getCin());
                ps.setString(2, utilisateur.getNom());
                ps.setString(3, utilisateur.getPrenom());
                ps.setString(4, utilisateur.getAdresse());
                ps.setString(5, utilisateur.getMdp());
                if(utilisateur instanceof Representant)
                {ps.setInt(6, Representant.getROLE());
                    ps.executeUpdate();
                    System.out.println("Representant added");}
                else {ps.setInt(6, Candidat.getROLE());
                    ps.executeUpdate();
                    System.out.println("Candidat added");}



            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    @Override
    public void modifier(Utilisateur utilisateur) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public Set<Utilisateur> getAll() {
        Set<Utilisateur> utilisateurs=new HashSet<>();
        String req ="Select * from utilisateur";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next())
            {
                int id = rs.getInt(1);
                int cin= rs.getInt(2);
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse =rs.getString("adresse");
                String mdp = rs.getString("mdp");
                int role=rs.getInt(7);
                if (role==0)
                {Utilisateur p = new Representant(id,cin,nom,prenom,adresse,mdp);
                    utilisateurs.add(p);
                }
                else {Utilisateur p = new Candidat(id,cin,nom,prenom,adresse,mdp);
                    utilisateurs.add(p);
                }


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateurs;
    }
    public Set<Admin> getAll_admin() {
        Set<Admin> admins=new HashSet<>();
        String req ="Select * from admin";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next())
            {
                int id = rs.getInt(1);
                int cin= rs.getInt(2);
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse =rs.getString("email");
                String mdp = rs.getString("mdp");
                Admin p = new Admin(id,cin,nom,prenom,adresse,mdp);
                admins.add(p);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }


    @Override
    public Utilisateur getOneByID(int id) {
        return null;
    }
}
