package edu.esprit.controllers;

import edu.esprit.entities.Reclamation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ModifierReclamationController {

    @FXML
    private TextField TFTitre;
    @FXML
    private TextField TFType;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea TADescription;

    // Add the initData method here
    public void initData(Reclamation selectedReclamation) {
        // Add code here to populate the fields with data from selectedReclamation
        TFType.setText(selectedReclamation.getType());
        TFTitre.setText(selectedReclamation.getTitre());

        java.util.Date utilDate = selectedReclamation.getDate();
        Timestamp timestamp = new Timestamp(utilDate.getTime());
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        LocalDate localDate = dateTime.toLocalDate();

// Now you can use the LocalDate to set the DatePicker value
        datePicker.setValue(localDate);
        TADescription.setText(selectedReclamation.getDescription());




        // Set other fields accordingly
    }

    // Add any other methods or event handlers as needed
}
