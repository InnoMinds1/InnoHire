package edu.esprit.services;

import edu.esprit.entities.Reponse;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class reponseService implements IService<Reponse> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reponse reponse) {

        try {
            String query = "INSERT INTO reponse (choix_correcte, id_question) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, reponse.getChoix_correcte());
                preparedStatement.setInt(2, reponse.getId_question());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Reponse reponse) {

        try {
            String query = "UPDATE reponse SET choix_correcte = ?, id_question = ? WHERE id_reponse = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, reponse.getChoix_correcte());
                preparedStatement.setInt(2, reponse.getId_question());
                preparedStatement.setInt(3, reponse.getId_reponse());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String query = "DELETE FROM reponse WHERE id_reponse = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Reponse> getAll() {
        Set<Reponse> reponseSet = new HashSet<>();
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String query = "SELECT * FROM reponse";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Reponse reponse = new Reponse();
                        reponse.setId_reponse(resultSet.getInt("id_reponse"));
                        reponse.setChoix_correcte(resultSet.getInt("choix_correcte"));
                        reponse.setId_question(resultSet.getInt("id_question"));
                        reponseSet.add(reponse);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reponseSet;
    }

    @Override

    public Reponse getOneByID(int id) {

        try {
            String query = "SELECT * FROM reponse WHERE id_reponse = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Reponse reponse = new Reponse();
                        reponse.setId_reponse(resultSet.getInt("id_reponse"));
                        reponse.setChoix_correcte(resultSet.getInt("choix_correcte"));
                        reponse.setId_question(resultSet.getInt("id_question"));
                        return reponse;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

