package edu.esprit.services;

import edu.esprit.entities.Reclamation;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class ServiceReclamation implements Iservice<Reclamation>{
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation rec) {
        String req = "INSERT INTO `reclamation`(`type`, `titre`, `description`, `date`, `status`, `image`, `id_publication`, `id_utilisateur`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setString(1, rec.getType());
            ps.setString(2, rec.getTitre());
            ps.setString(3, rec.getDescription());
            ps.setTimestamp(4, new java.sql.Timestamp(rec.getDate().getTime()));
            ps.setInt(5,rec.getStatus());
            ps.setString(6, rec.getImage());
            ps.setInt(7, rec.getPub().getId_publication());
            ps.setInt(8, rec.getUser().getId_utilisateur());
            ps.executeUpdate();
            System.out.println("Reclamation added!");
        } catch (SQLException e) {
            System.err.println("Error adding Reclamation: " + e.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation reclamation) {
        String req = "UPDATE `reclamation` SET `type`=?, `titre`=?, `description`=?, `date`=?, `status`=?, `image`=?, `id_publication`=?, `id_utilisateur`=? WHERE `id_reclamation`=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setString(1, reclamation.getType());
            ps.setString(2, reclamation.getTitre());
            ps.setString(3, reclamation.getDescription());
            ps.setTimestamp(4, new java.sql.Timestamp(reclamation.getDate().getTime()));
            ps.setInt(5, reclamation.getStatus());
            ps.setString(6, reclamation.getImage());
            ps.setInt(7, reclamation.getPub().getId_publication());
            ps.setInt(8, reclamation.getUser().getId_utilisateur());
            ps.setInt(9, reclamation.getId_reclamation());
            ps.executeUpdate();
            System.out.println("Reclamation updated!");
        } catch (SQLException e) {
            System.err.println("Error updating Reclamation: " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public Set<Reclamation> getAll() {
        return null;
    }

    @Override
    public Reclamation getOneByID(int id) {
        return null;
    }
}
