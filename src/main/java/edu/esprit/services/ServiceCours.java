package edu.esprit.services;

import edu.esprit.entities.Categorie;
import edu.esprit.entities.Cours;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
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
        String req = "UPDATE `cours` SET `id_categorie` = ?, `id_etablissement` = ?, `nom` = ?, `prix` = ?, `description` = ?, `image` = ? WHERE `id_cours` = ?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, cours.getCat().getId_categorie());
            ps.setInt(2, cours.getEtab().getId_etablissement());
            ps.setString(3, cours.getNom());
            ps.setFloat(4, cours.getPrix());
            ps.setString(5, cours.getDescription());
            ps.setString(6, cours.getImage());
            ps.setInt(7, cours.getId_cours());
            ps.executeUpdate();
            System.out.println("Cours modified!");
        } catch (SQLException e) {
            System.err.println("Error modifying cours: " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public Set<Cours> getAll() {
        Set<Cours> coursList = new HashSet<>();
        String req = "SELECT * FROM `cours`";
        try (PreparedStatement ps = cnx.prepareStatement(req);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cours cours = new Cours();
                cours.setId_cours(rs.getInt("id_cours"));
                cours.setNom(rs.getString("nom"));
                cours.setPrix(rs.getInt("prix"));
                cours.setDescription(rs.getString("description"));
                cours.setImage(rs.getString("image"));

                Categorie categorie = new ServiceCategorie().getOneByID(rs.getInt("id_categorie"));
                cours.setCat(categorie);

                /*Etablissement etablissement = new ServiceEtablissement().getOneByID(rs.getInt("id_etablissement"));
                cours.setEtab(etablissement);*/

                coursList.add(cours);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving cours: " + e.getMessage());
        }
        return coursList;
    }

    @Override
    public Cours getOneByID(int id) {
        String req = "SELECT * FROM `cours` WHERE `id_cours` = ?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cours cours = new Cours();
                    cours.setId_cours(rs.getInt("id_cours"));
                    cours.setNom(rs.getString("nom"));
                    cours.setPrix(rs.getInt("prix"));
                    cours.setDescription(rs.getString("description"));
                    cours.setImage(rs.getString("image"));

                    Categorie categorie = new ServiceCategorie().getOneByID(rs.getInt("id_categorie"));
                    cours.setCat(categorie);

                    /*Etablissement etablissement = new ServiceEtablissement().getOneByID(rs.getInt("id_etablissement"));
                    cours.setEtab(etablissement);*/

                    return cours;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving cours: " + e.getMessage());
        }
        return null;
    }
}
