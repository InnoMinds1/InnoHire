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
    @FXML
    private Label receiverNameLabel;

    @FXML
    private ImageView receiverProfileImage;

    private final ServiceMessagerie serviceMessagerie = new ServiceMessagerie();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateReceiverInfo();
        updateChatMessages();
    }
    public void updateReceiverInfo() {
        // Get the name and profile image URL of the receiver
        String receiverName = "Receiver Name"; // Replace with the actual receiver's name
        String profileImageUrl = "/images/inner.png"; // Replace with the actual image URL

        // Set the receiver's name and profile image
        receiverNameLabel.setText(receiverName);
        receiverProfileImage.setImage(new Image(profileImageUrl));
    }
    public void updateChatMessages() {
        // Get the reference to the chatVbox
        // VBox chatVbox ;// reference to your chatVbox, you can use FXMLLoader to get it.

        // Get the messages from the database
        Set<Messagerie> messages = serviceMessagerie.getAllMessagesByReciverAndSender(1,9);

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
        profileImage.setFitWidth(40.0); // Set the desired width
        profileImage.setFitHeight(40.0); // Set the desired height

        AnchorPane messagePane = new AnchorPane();
        messagePane.setStyle("-fx-background-color: #f2f5ec; -fx-background-radius: 0 30 30 30;");

        Label contentLabel = new Label(message.getContenu());
        contentLabel.setLayoutX(17.0);
        contentLabel.setLayoutY(10.0);
        //5955b3
        contentLabel.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold; -fx-font-size: 10; -fx-padding: 0 0 15 0;");

        contentLabel.setWrapText(true); // Enable text wrapping
        contentLabel.setMaxWidth(400.0); // Set a max width to trigger wrapping

        // Bind the prefWidth of the AnchorPane to the width of the Label
        messagePane.prefWidthProperty().bind(contentLabel.widthProperty().add(34.0)); // Adjusted for padding

        Label dateLabel = new Label(message.getDate().toString()); // Adjust this based on your date format
        dateLabel.setLayoutX(370.0); // Adjusted X position to place it in the bottom-right corner
        dateLabel.setLayoutY(40.0); // Adjusted Y position to place it below the message
        dateLabel.setStyle("-fx-text-fill: #888888; -fx-font-size: 10;");

        if (message.getSender_id().getId_utilisateur() == 1) {
            profileImage = new ImageView(new Image("/images/business.png"));
            profileImage.setFitWidth(40.0);
            profileImage.setFitHeight(40.0);
           // messageHbox.getChildren().addAll(messagePane, dateLabel);
            messagePane.getChildren().addAll(contentLabel);
            messageHbox.getChildren().addAll(profileImage, messagePane);
            messageHbox.getChildren().add(dateLabel);
        } else {
             profileImage = new ImageView(new Image("/images/profile.png"));
            profileImage.setFitWidth(40.0);
            profileImage.setFitHeight(40.0);
            //messageHbox.getChildren().addAll(profileImage, messagePane, dateLabel);
            messagePane.getChildren().addAll(contentLabel);
            messageHbox.getChildren().addAll(profileImage, messagePane);
            messageHbox.getChildren().add(dateLabel);
        }

        /*messagePane.getChildren().addAll(contentLabel);
        messageHbox.getChildren().addAll(profileImage, messagePane);
        messageHbox.getChildren().add(dateLabel);*/

        return messageHbox;
    }
}
