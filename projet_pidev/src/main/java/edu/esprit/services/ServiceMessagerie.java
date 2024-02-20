package edu.esprit.services;

import edu.esprit.entities.Messagerie;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class ServiceMessagerie implements Iservice<Messagerie> {
    private Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Messagerie mess) {
        String req = "INSERT INTO `messagerie`(`date`, `type`, `contenu`, `sender_id`, `reciver_id`) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setTimestamp(1, new java.sql.Timestamp(mess.getDate().getTime()));
            ps.setString(2, mess.getType());
            ps.setString(3, mess.getContenu());
            ps.setInt(4, mess.getSender_id().getId_utilisateur());
            ps.setInt(5, mess.getReciver_id().getId_utilisateur());
            ps.executeUpdate();
            System.out.println("Messaagerie added!");
        } catch (SQLException e) {
            System.err.println("Error adding Messaagerie: " + e.getMessage());
        }
    }

    @Override
    public void modifier(Messagerie messagerie) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public Set<Messagerie> getAll() {
        return null;
    }

    @Override
    public Messagerie getOneByID(int id) {
        return null;
    }
}
