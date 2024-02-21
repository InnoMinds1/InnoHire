package edu.esprit.services;

import edu.esprit.entities.Publication;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Utilisateur;
import edu.esprit.utils.DataSource;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceReclamation implements Iservice<Reclamation>{
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation rec) throws SQLException {
        //try (PreparedStatement ps = cnx.prepareStatement(req)) {
        String req = "INSERT INTO `reclamation`(`type`, `titre`, `description`, `date`, `status`, `id_publication`, `id_utilisateur`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, rec.getType());
            ps.setString(2, rec.getTitre());
            ps.setString(3, rec.getDescription());
            ps.setTimestamp(4, new java.sql.Timestamp(rec.getDate().getTime()));
            ps.setInt(5,rec.getStatus());
            ps.setInt(6, rec.getPub().getId_publication());
            ps.setInt(7, rec.getUser().getId_utilisateur());
            ps.executeUpdate();
            System.out.println("Reclamation added!");
       /* } catch (SQLException e) {
            System.err.println("Error adding Reclamation: " + e.getMessage());
        }*/
    }

    @Override
    public void modifier(Reclamation reclamation) {
        String req = "UPDATE `reclamation` SET `type`=?, `titre`=?, `description`=?, `date`=?, `status`=?, `id_publication`=?, `id_utilisateur`=? WHERE `id_reclamation`=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setString(1, reclamation.getType());
            ps.setString(2, reclamation.getTitre());
            ps.setString(3, reclamation.getDescription());
            ps.setTimestamp(4, new java.sql.Timestamp(reclamation.getDate().getTime()));
            ps.setInt(5, reclamation.getStatus());
            ps.setInt(6, reclamation.getPub().getId_publication());
            ps.setInt(7, reclamation.getUser().getId_utilisateur());
            ps.setInt(8, reclamation.getId_reclamation());
            ps.executeUpdate();
            System.out.println("Reclamation updated!");
        } catch (SQLException e) {
            System.err.println("Error updating Reclamation: " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM `reclamation` WHERE `id_reclamation`=?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Reclamation deleted!");
        } catch (SQLException e) {
            System.err.println("Error deleting Reclamation: " + e.getMessage());
        }
    }

    @Override
    public Set<Reclamation> getAll() {
        Set<Reclamation> reclamations = new HashSet<>();
        String req = "SELECT * FROM `reclamation`";
        try (PreparedStatement ps = cnx.prepareStatement(req);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setId_reclamation(rs.getInt("id_reclamation"));
                rec.setStatus(rs.getInt("status"));
                rec.setType(rs.getString("type"));
                rec.setTitre(rs.getString("titre"));
                rec.setDescription(rs.getString("description"));
                rec.setDate(rs.getTimestamp("date"));

                // Use ServicePublication to get Publication by ID
                /*Publication publication = new ServicePublication().getOneByID(rs.getInt("id_publication"));
                rec.setPub(publication);*/

                // Use ServiceUtilisateur to get Utilisateur by ID
                Utilisateur utilisateur = new ServiceUtilisateur().getOneByID(rs.getInt("id_utilisateur"));
                rec.setUser(utilisateur);

                reclamations.add(rec);
            }
        } catch (SQLException e) {
            System.err.println("Error getting Reclamations: " + e.getMessage());
        }
        return reclamations;
    }



    @Override
    public Reclamation getOneByID(int id) {
        String req = "SELECT * FROM `reclamation` WHERE `id_reclamation` = ?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Reclamation rec = new Reclamation();
                    rec.setId_reclamation(rs.getInt("id_reclamation"));
                    rec.setStatus(rs.getInt("status"));
                    rec.setType(rs.getString("type"));
                    rec.setTitre(rs.getString("titre"));
                    rec.setDescription(rs.getString("description"));
                    rec.setDate(rs.getTimestamp("date"));

                    // Use ServicePublication to get Publication by ID
                   /* Publication publication = new ServicePublication().getOneByID(rs.getInt("id_publication"));
                    rec.setPub(publication);*/

                    // Use ServiceUtilisateur to get Utilisateur by ID
                    Utilisateur utilisateur = new ServiceUtilisateur().getOneByID(rs.getInt("id_utilisateur"));
                    rec.setUser(utilisateur);

                    return rec;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving reclamation: " + e.getMessage());
        }
        return null;
    }

}
