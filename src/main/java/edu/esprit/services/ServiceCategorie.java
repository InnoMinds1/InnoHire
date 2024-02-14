package edu.esprit.services;

import edu.esprit.entities.Categorie;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ServiceCategorie implements Iservice<Categorie> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Categorie categorie) {
        String req = "INSERT INTO `categorie`(`nom`, `description`) VALUES (?,?)";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setString(1, categorie.getNom());
            ps.setString(2, categorie.getDescription());
            ps.executeUpdate();
            System.out.println("Category added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Categorie categorie) {
        String req = "UPDATE `categorie` SET `nom` = ?, `description` = ? WHERE `id_categorie` = ?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setString(1, categorie.getNom());
            ps.setString(2, categorie.getDescription());
            ps.setInt(3, categorie.getId_categorie());
            ps.executeUpdate();
            System.out.println("Category modified!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id_categorie) {
        String req = "DELETE FROM `categorie` WHERE `id_categorie` = ?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id_categorie);
            ps.executeUpdate();
            System.out.println("Category deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<Categorie> getAll() {
        Set<Categorie> categories = new HashSet<>();
        String req = "SELECT * FROM `categorie`";
        try (PreparedStatement ps = cnx.prepareStatement(req);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.setId_categorie(rs.getInt("id_categorie"));
                categorie.setNom(rs.getString("nom"));
                categorie.setDescription(rs.getString("description"));
                categories.add(categorie);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    @Override
    public Categorie getOneByID(int id) {
        String req = "SELECT * FROM `categorie` WHERE `id_categorie` = ?";
        try (PreparedStatement ps = cnx.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categorie categorie = new Categorie();
                    categorie.setId_categorie(rs.getInt("id_categorie"));
                    categorie.setNom(rs.getString("nom"));
                    categorie.setDescription(rs.getString("description"));
                    return categorie;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
