package edu.esprit.services;

import edu.esprit.entities.Evenement;

import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceEvenement implements IService<Evenement> {
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Evenement evenement) {
        String req = "INSERT INTO `evenement`(`code_evenement`, `nom`,`lieu`,`date`, `description`, `nb_participants`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, evenement.getCode_evenement());
            ps.setString(2, evenement.getNom());
            ps.setString(3, evenement.getLieu());
            ps.setString(4, evenement.getDate());
            ps.setString(5, evenement.getDescription());
            ps.setInt(6, evenement.getNb_participants());
            ps.executeUpdate();
            System.out.println("Evenement added !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Evenement evenement) {
        int id = evenement.getId_evenement();
        Evenement existingEvenement = getOneByID(id);
        if (existingEvenement != null) {
            String req = "UPDATE `evenement` SET `code_evenement`=?, `nom`=?,`lieu`=?,`date`=?,`description`=?,`nb_participants`=? WHERE `id_evenement`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, evenement.getCode_evenement());
                ps.setString(2, evenement.getNom());
                ps.setString(3, evenement.getLieu());
                ps.setString(4, evenement.getDate());
                ps.setString(5, evenement.getDescription());
                ps.setInt(6, evenement.getNb_participants());
                ps.setInt(7, id);
                ps.executeUpdate();
                System.out.println("Evenement updated !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("introuvable : Échec de mise à jour ");
        }
    }

    @Override
    public void supprimer(int id) {
        Evenement evenement = getOneByID(id);
        if (evenement != null) {
            String req = "DELETE FROM `evenement` WHERE `id_evenement`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Evenement deleted !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("introuvable : Échec de suppression ");
        }

    }

    @Override
    public Set<Evenement> getAll() {
        Set<Evenement> evenements = new HashSet<>();

        String req = "Select * from evenement";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id_evenement = rs.getInt("id_evenement"); //wala t7ot num colomn kima eli ta7etha
                int code_evenement = rs.getInt("code_evenement");
                String nom = rs.getString("nom"); //wala t7ot esm colomn kima eli fou9ha
                String date = rs.getString("lieu");
                String lieu = rs.getString("date");
                String description = rs.getString("description");
                int nb_participants = rs.getInt("nb_participants");
                Evenement e = new Evenement(id_evenement,code_evenement,nom,lieu,date,description,nb_participants);
                evenements.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return evenements;
    }

    @Override
    public Evenement getOneByID(int id) {
        String req = "SELECT * FROM evenement WHERE id_evenement = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int code_evenement = rs.getInt("code_evenement");
                String nom = rs.getString("date");
                String lieu = rs.getString("lieu");
                String date = rs.getString("date");
                String description = rs.getString("description");
                int nb_participants = rs.getInt("nb_participants");
                return new Evenement(code_evenement,nom,date,lieu,description,nb_participants);
            } else {
                System.out.print("Echec! Evenement with ID " + id + " est" + " " );
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;


        }
} }
