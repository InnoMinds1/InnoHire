package edu.esprit.services;

import edu.esprit.entities.Question;
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
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Quiz quiz) {

        try {
            String query = "INSERT INTO quiz (code_quiz, nom_quiz, description, prix_quiz, image_quiz) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, quiz.getCode_quiz());
                preparedStatement.setString(2, quiz.getNom_quiz());
                preparedStatement.setString(3, quiz.getDescription());
                preparedStatement.setInt(4, quiz.getPrix_quiz());
                preparedStatement.setString(5, quiz.getImage_quiz());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Quiz quiz) {

        try {
            String query = "UPDATE quiz SET code_quiz = ?, nom_quiz = ?, description = ?, prix_quiz = ?, image_quiz = ? WHERE id_quiz = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, quiz.getCode_quiz());
                preparedStatement.setString(2, quiz.getNom_quiz());
                preparedStatement.setString(3, quiz.getDescription());
                preparedStatement.setInt(4, quiz.getPrix_quiz());
                preparedStatement.setString(5, quiz.getImage_quiz());
                preparedStatement.setInt(6, quiz.getId_quiz());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {

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
                        quiz.setImage_quiz(resultSet.getString("image_quiz"));
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
                        quiz.setImage_quiz(resultSet.getString("image_quiz"));
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
    private int getCodeQuizbyID(int idQuiz) throws SQLException {


        try {
            String sql = "SELECT code_quiz FROM quiz WHERE id_quiz = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, idQuiz);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("code_quiz");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return -1;
    }
    public Quiz getOneByCode(int code) {

        try {
            String query = "SELECT * FROM quiz WHERE code_quiz = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, code);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Quiz quiz = new Quiz();
                        quiz.setId_quiz(resultSet.getInt("id_quiz"));
                        quiz.setCode_quiz(resultSet.getInt("code_quiz"));
                        quiz.setNom_quiz(resultSet.getString("nom_quiz"));
                        quiz.setDescription(resultSet.getString("description"));
                        quiz.setPrix_quiz(resultSet.getInt("prix_quiz"));
                        quiz.setImage_quiz(resultSet.getString("image_quiz"));
                        return quiz;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getIdQuizByCode(Integer codeQuiz) throws SQLException {



        try {
            String sql = "SELECT id_quiz FROM quiz WHERE code_quiz = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, codeQuiz);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id_quiz");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return -1;
    }


}
