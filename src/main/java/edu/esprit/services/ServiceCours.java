package edu.esprit.services;

import edu.esprit.entities.Cours;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class ServiceCours implements Iservice<Cours>{
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Cours cours) {
        String req = "INSERT INTO `cours`(`id_categorie`,`id_etablissement`, `nom`, `prix`, `description`, `image`) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, cours.getCat().getId_categorie());
            ps.setInt(2, cours.getEtab().getId_etablissement());
            ps.setString(3, cours.getNom());
            ps.setFloat(4, cours.getPrix());
            ps.setString(5, cours.getDescription());
            ps.setString(6, cours.getImage());
            ps.executeUpdate();
            System.out.println("Cours added!");
        } catch (SQLException e) {
            System.err.println("Error adding cours: " + e.getMessage());
        }
    }

    @Override
    public void modifier(Cours cours) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public Set<Cours> getAll() {
        return null;
    }

    @Override
    public Cours getOneByID(int id) {
        return null;
    }
}
