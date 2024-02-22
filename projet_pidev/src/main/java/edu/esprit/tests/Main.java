package edu.esprit.tests;

import edu.esprit.entities.Question;
import edu.esprit.services.questionService;
import edu.esprit.utils.DataSource;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args)
    {
        questionService qs= new questionService();
        try {
            qs.supprimer(22);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
