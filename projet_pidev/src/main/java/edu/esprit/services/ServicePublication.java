package edu.esprit.services;

import edu.esprit.entities.Publication;
import edu.esprit.entities.Utilisateur;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

public class ServicePublication implements Iservice<Publication>{
    @Override
    public void ajouter(Publication publication) throws SQLException {

    }

    @Override
    public void modifier(Publication publication) throws SQLException {

    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public Set<Publication> getAll() throws SQLException {
        return null;
    }

    @Override
    public Publication getOneByID(int id_publication) {
        Connection cnx = DataSource.getInstance().getCnx();
        String req = "SELECT * FROM publication WHERE id_publication = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_publication);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String code_pub = rs.getString("code_pub");
                String description = rs.getString("description");
                String hashtag = rs.getString("hashtag");
                String visibilite = rs.getString("visibilite");
                String image = rs.getString("image");
                LocalDate date = rs.getDate("date").toLocalDate();
                int nb_report = rs.getInt("nb_report");
                int id_utilisateur = rs.getInt("id_utilisateur");

                // Use ServiceUtilisateur to get Utilisateur by ID
                Utilisateur utilisateur = new ServiceUtilisateur().getOneByID(id_utilisateur);

                // Create a new Publication object and set the Utilisateur
                Publication publication = new Publication(code_pub, utilisateur, description, hashtag, visibilite, image, date, nb_report);
                publication.setId_publication(id_publication);

                return publication;
            } else {
                System.out.print("Echec! Etablissement with ID " + id_publication + " est" + " ");
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



}
