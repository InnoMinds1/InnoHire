package edu.esprit.tests;

import edu.esprit.entities.Question;
import edu.esprit.services.questionService;
import edu.esprit.utils.DataSource;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args)
    {
        questionService sr= new questionService();
        Question q  = new Question(1,"c'est quoi ?","1. toi / 2. moi /3.lui",1);
        try {
            sr.ajouter(q);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
