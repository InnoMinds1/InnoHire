package edu.esprit.controllers;

import edu.esprit.entities.Messagerie;
import edu.esprit.entities.Reclamation;
import edu.esprit.services.ServiceMessagerie;
import edu.esprit.services.ServiceReclamation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class AjouterAfficherMessageController implements Initializable{
    @FXML
    private VBox chatVbox;

    private final ServiceMessagerie serviceMessagerie = new ServiceMessagerie();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateChatMessages();
    }
    public void updateChatMessages() {
        // Get the reference to the chatVbox
        // VBox chatVbox ;// reference to your chatVbox, you can use FXMLLoader to get it.

        // Get the messages from the database
        Set<Messagerie> messages = serviceMessagerie.getAll();

        // Clear the existing content
        chatVbox.getChildren().clear();

        // Iterate through the messages and add them to the chatVbox with margins
        for (Messagerie message : messages) {
            HBox messageBox = createMessageBox(message);
            VBox.setMargin(messageBox, new Insets(0.0, 0.0, 10.0, 0.0)); // Set top and bottom margins
            chatVbox.getChildren().add(messageBox);
        }
    }

    private HBox createMessageBox(Messagerie message) {
        // Create an HBox for each message
        HBox messageHbox = new HBox();
        messageHbox.setSpacing(10.0); // Set spacing between children

        // Add UI components for displaying the message, e.g., Label, ImageView, etc.
        // Example:
        ImageView profileImage = new ImageView(new Image("/images/profile.png"));
        profileImage.setFitWidth(30.0); // Set the desired width
        profileImage.setFitHeight(30.0); // Set the desired height

        AnchorPane messagePane = new AnchorPane();
        messagePane.setPrefHeight(100.0);
        messagePane.setPrefWidth(410.0);
        messagePane.setStyle("-fx-background-color: #f2f5ec; -fx-background-radius: 0 30 30 30;");

        Label contentLabel = new Label(message.getContenu());
        contentLabel.setLayoutX(17.0);
        contentLabel.setLayoutY(10.0);
        //contentLabel.setPrefHeight(21.0);
        //contentLabel.setPrefWidth(308.0);
        contentLabel.setStyle("-fx-text-fill: #5955b3; -fx-font-weight: bold; -fx-font-size: 10;");

        messagePane.getChildren().add(contentLabel);

        // Add components to the HBox
        messageHbox.getChildren().addAll(profileImage, messagePane);

        return messageHbox;
    }




//    @FXML
//    private GridPane chatGridPane;
//
//    private ServiceMessagerie serviceMessagerie = new ServiceMessagerie();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println("Controller initialized."); // Check if this line is printed
//        displayMessages();
//    }
//
//    private void displayMessages() {
//        Set<Messagerie> messages = serviceMessagerie.getAll();
//        System.out.println("Number of messages retrieved: " + messages.size()); // Check if this line is printed
//
//        int rowIndex = 0; // Start from the first row
//
//        for (Messagerie message : messages) {
//            addMessageToUI(message, rowIndex);
//            rowIndex++;
//        }
//    }
//
//    private void addMessageToUI(Messagerie message, int rowIndex) {
//        Label contenuReciverLabel = new Label(message.getContenu());
//
//        contenuReciverLabel.setId("contenuReciver");
//        contenuReciverLabel.setLayoutX(70.0); // Adjusted layoutX to accommodate the profile image
//        contenuReciverLabel.setLayoutY(20.0);
//        contenuReciverLabel.setMaxWidth(340);
//        contenuReciverLabel.setWrapText(true);
//        contenuReciverLabel.setStyle("-fx-text-fill: #5955b3; -fx-font-weight: bold; -fx-font-size: 10;");
//        contenuReciverLabel.setText(message.getContenu());
//
//        // For receiver messages, use a profile image on the left
//        ImageView profileImage = new ImageView(new Image("/images/blog.png"));
//        profileImage.setFitHeight(45.0);
//        profileImage.setFitWidth(47.0);
//        profileImage.setLayoutX(23.0);
//        profileImage.setLayoutY(10.0);
//
//        // Add the profile image and labels to the chatGridPane
//        chatGridPane.addRow(rowIndex, profileImage, createMessageContainer(contenuReciverLabel));
//    }
//
//
//
//
//    private AnchorPane createMessageContainer(Label contenuReciverLabel) {
//        AnchorPane messageContainer = new AnchorPane();
//
//        messageContainer.prefWidthProperty().bind(contenuReciverLabel.widthProperty().add(100)); // Adjusted padding
//        messageContainer.prefHeightProperty().bind(contenuReciverLabel.heightProperty().add(20));
//
//        messageContainer.setStyle("-fx-background-color: #f2f5ec; -fx-background-radius: 0 30 30 30;");
//
//        // Add the label to the message container
//        messageContainer.getChildren().add(contenuReciverLabel);
//
//        return messageContainer;
//    }



}
