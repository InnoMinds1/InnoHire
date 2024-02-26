














package edu.esprit.services;

import edu.esprit.entities.Question;


import edu.esprit.entities.Quiz;
import edu.esprit.utils.DataSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class questionService implements IService<Question> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override


    public void ajouter(Question question) throws SQLException {
        try{
            String query = "INSERT INTO question (question, choix, id_quiz,reponse_correcte) VALUES (?, ?, ?,?)";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setString(1, question.getQuestion());
                preparedStatement.setString(2, question.getChoix());
                preparedStatement.setInt(3, question.getQuiz().getId_quiz());
                preparedStatement.setInt(3, question.getReponse_correcte());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void modifier(Question question) {

        try {
            String query = "UPDATE question SET question = ?, choix = ?, id_quiz = ?,reponse_correcte=? WHERE id_question = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setString(1, question.getQuestion());
                preparedStatement.setString(2, question.getChoix());
                preparedStatement.setInt(3, question.getQuiz().getId_quiz());
                preparedStatement.setInt(4, question.getId_question());
                preparedStatement.setInt(5, question.getReponse_correcte());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override

    public void supprimer(int id_question) throws SQLException{
        String query = "DELETE FROM `question` WHERE `id_question`= ?";

        try (
             PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, id_question);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Set<Question> getAll() throws SQLException{
        Set<Question> questionSet = new HashSet<>();

        try {
            String query = "SELECT * FROM question";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Question question = new Question();
                        Quiz quiz = new Quiz();
                        question.setId_question(resultSet.getInt("id_question"));
                        question.setQuestion(resultSet.getString("question"));
                        question.setChoix(resultSet.getString("choix"));
                        question.setReponse_correcte(resultSet.getInt("reponse_correcte"));

                        int id_quiz = resultSet.getInt("id_quiz");
                        quizService qs=new quizService();
                      Quiz quiz2 = qs.getOneByID(id_quiz);
                        question.setQuiz(quiz2);


                        questionSet.add(question);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionSet;
    }

    @Override
    public Question getOneByID(int id) {

        try {
            String query = "SELECT * FROM question WHERE id_question = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Question question = new Question();
                        question.setId_question(resultSet.getInt("id_question"));
                        question.setQuestion(resultSet.getString("question"));
                        question.setChoix(resultSet.getString("choix"));
                        question.getQuiz().setId_quiz(resultSet.getInt("id_quiz"));
                        question.setReponse_correcte(resultSet.getInt("reponse_correcte"));

                        return question;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
