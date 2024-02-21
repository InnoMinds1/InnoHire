package edu.esprit.services;

import edu.esprit.entities.Quiz;
import edu.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class quizService implements IService<Quiz> {

    @Override
    public void ajouter(Quiz quiz) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String query = "INSERT INTO quiz (code_quiz, nom_quiz, description, prix_quiz, id_etablissement) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, quiz.getCode_quiz());
                preparedStatement.setString(2, quiz.getNom_quiz());
                preparedStatement.setString(3, quiz.getDescription());
                preparedStatement.setInt(4, quiz.getPrix_quiz());
                preparedStatement.setInt(5, quiz.getId_etablissement());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Quiz quiz) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String query = "UPDATE quiz SET code_quiz = ?, nom_quiz = ?, description = ?, prix_quiz = ?, id_etablissement = ? WHERE id_quiz = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, quiz.getCode_quiz());
                preparedStatement.setString(2, quiz.getNom_quiz());
                preparedStatement.setString(3, quiz.getDescription());
                preparedStatement.setInt(4, quiz.getPrix_quiz());
                preparedStatement.setInt(5, quiz.getId_etablissement());
                preparedStatement.setInt(6, quiz.getId_quiz());
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
            String query = "DELETE FROM quiz WHERE id_quiz = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Quiz> getAll() {
        Set<Quiz> quizSet = new HashSet<>();
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String query = "SELECT * FROM quiz";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Quiz quiz = new Quiz();
                        quiz.setId_quiz(resultSet.getInt("id_quiz"));
                        quiz.setCode_quiz(resultSet.getInt("code_quiz"));
                        quiz.setNom_quiz(resultSet.getString("nom_quiz"));
                        quiz.setDescription(resultSet.getString("description"));
                        quiz.setPrix_quiz(resultSet.getInt("prix_quiz"));
                        quiz.setId_etablissement(resultSet.getInt("id_etablissement"));
                        quizSet.add(quiz);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizSet;
    }

    @Override
    public Quiz getOneByID(int id) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String query = "SELECT * FROM quiz WHERE id_quiz = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Quiz quiz = new Quiz();
                        quiz.setId_quiz(resultSet.getInt("id_quiz"));
                        quiz.setCode_quiz(resultSet.getInt("code_quiz"));
                        quiz.setNom_quiz(resultSet.getString("nom_quiz"));
                        quiz.setDescription(resultSet.getString("description"));
                        quiz.setPrix_quiz(resultSet.getInt("prix_quiz"));
                        quiz.setId_etablissement(resultSet.getInt("id_etablissement"));
                        return quiz;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Integer> getQuizcodes() throws SQLException {
        List<Integer> quizcodes = new ArrayList<>();
        Connection cnx = DataSource.getInstance().getCnx();

        try {
            String sql = "SELECT code_quiz FROM quiz";

            try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int codeQuiz = resultSet.getInt("code_quiz");
                        quizcodes.add(codeQuiz);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quizcodes;
    }
}
