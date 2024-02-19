package edu.esprit.services;

import edu.esprit.entities.Commentaire;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class ServiceCommentaire implements IService<Commentaire>  {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Commentaire commentaire) {
        String req = "INSERT INTO `commentaire`(`id_publication`, `id_utilisateur`, `description_co`, `date_co`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, commentaire.getPublication().getId_publication()); // Assuming getId() retrieves the ID of the publication
            ps.setInt(2, commentaire.getUtilisateur().getId_utilisateur()); // Assuming getId() retrieves the ID of the utilisateur
            ps.setString(3, commentaire.getDescription_co());
            ps.setDate(4, Date.valueOf(commentaire.getDate_co()));
            ps.executeUpdate();
            System.out.println("Comment added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding comment: " + e.getMessage());
        }
    }



    @Override
    public void modifier(Commentaire commentaire) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public Set<Commentaire> getAll() {
        return null;
    }

    @Override
    public Commentaire getOneByID(int id) {
        return null;
    }
}
