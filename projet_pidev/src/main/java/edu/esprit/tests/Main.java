package edu.esprit.tests;

import edu.esprit.entities.Question;
import edu.esprit.entities.Quiz;
import edu.esprit.services.questionService;
import edu.esprit.utils.DataSource;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        questionService qs= new questionService();
        System.out.println(qs.getAll());



    }
}
