package edu.esprit.services;

import edu.esprit.entities.Publication;
import edu.esprit.entities.Utilisateur;
import edu.esprit.utils.DataSource;


import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ServicePublication implements IService<Publication> {
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Publication publication) {
        String req = "INSERT INTO `publication`(`id_utilisateur`, `description`, `hashtag`, `visibilite`, `image`, `date`, `nb_report`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, publication.getUtilisateur().getId_utilisateur()); // id_utilisateur is placed in the first position
            ps.setString(2, publication.getDescription());
            ps.setString(3, publication.getHashtag());
            ps.setString(4, publication.getVisibilite());
            ps.setString(5, publication.getImage());
            ps.setDate(6, Date.valueOf(publication.getDate())); // Assuming date is a java.util.Date
            ps.setInt(7, publication.getNb_report()); // Assuming this is the correct method to get nb_report
            ps.executeUpdate();
            System.out.println("Publication added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding publication: " + e.getMessage());
        }
    }


    @Override
    public void modifier(Publication publication) {
        String req = "UPDATE publication SET id_utilisateur =?, description = ?, hashtag = ?, visibilite = ?, image = ?, date = ?, nb_report = ?  WHERE id_publication = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, publication.getUtilisateur().getId_utilisateur());
            ps.setString(2, publication.getDescription());
            ps.setString(3, publication.getHashtag());
            ps.setString(4, publication.getVisibilite());
            ps.setString(5, publication.getImage());
            ps.setDate(6, Date.valueOf(publication.getDate()));
            ps.setInt(7, publication.getNb_report());
            ps.setInt(8, publication.getId_publication());

            ps.executeUpdate();
            System.out.println("publication modifié!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id_publication) {
        Publication publication= getOneByID(id_publication);
        if (publication != null) {
            String req = "DELETE FROM `publication` WHERE `id_publication`=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, id_publication);
                ps.executeUpdate();
                System.out.println("Pub deleted !");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("introuvable : Échec de suppression ");
        }
    }

    @Override
    public Set<Publication> getAll() {
        Set<Publication> publications = new HashSet<>();

        String req = "Select * from publication";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Publication publication =new Publication();
                int id_publication=rs.getInt("id_publication");
                String description = rs.getString("description");
                String hashtag = rs.getString("hashtag");
                String visibilite = rs.getString("visibilite");
                String image = rs.getString("image");
                LocalDate date = rs.getDate("date").toLocalDate();
                int nb_report = rs.getInt("nb_report");
                Publication p= new Publication(id_publication,description,hashtag,visibilite,image,date,nb_report);
                publications.add(publication);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return publications;
    }

    @Override
    public Publication getOneByID(int id_publication) {
        String req = "SELECT * FROM publication WHERE id_publication = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_publication);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String description = rs.getString("description");
                String hashtag = rs.getString("hashtag");
                String visibilite = rs.getString("visibilite");
                String image = rs.getString("image");
                LocalDate date = rs.getDate("date").toLocalDate();
                int nb_report = rs.getInt("nb_report");

                return new Publication(id_publication,description,hashtag,visibilite,image,date,nb_report);
            } else {
                System.out.print("Echec! Etablissement with ID " + id_publication + " est" + " " );
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
