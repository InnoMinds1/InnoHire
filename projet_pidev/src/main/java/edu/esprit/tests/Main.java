package edu.esprit.tests;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.questionService;
import edu.esprit.utils.DataSource;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Quiz Q=new Quiz();
        Q.setId_quiz(5);
        Question q=new Question("aleh","wakteh",Q,3);
        questionService qs=new questionService();
        qs.ajouter(q);
        qs.modifier(q);






    }
}
