package edu.esprit.controllers;

import edu.esprit.entities.Messagerie;
import edu.esprit.entities.Publication;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Utilisateur;
import edu.esprit.services.ServiceMessagerie;
import edu.esprit.services.ServiceReclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

public class AjouterAfficherMessageController implements Initializable{
    @FXML
    private VBox chatVbox;
    @FXML
    private Label receiverNameLabel;

    @FXML
    private ImageView receiverProfileImage;
    @FXML
    private TextField TFmessage;

    private final ServiceMessagerie serviceMessagerie = new ServiceMessagerie();

    Utilisateur amen=new Utilisateur(1,11417264,"dhawadi","hachem","bizerte","123456789");
    Utilisateur hachem=new Utilisateur(9,11417264,"dhawadi","hachem","bizerte","123456789");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateReceiverInfo();
        updateChatMessages();
    }
    public void updateReceiverInfo() {
        // Get the name and profile image URL of the receiver
        //String receiverName = serviceMessagerie.getOneByID(9).getReciver_id().getNom(); // Replace with the actual receiver's name
        String receiverName = hachem.getNom()+" "+hachem.getPrenom();
        String profileImageUrl = "/images/manh.png"; // Replace with the actual image URL

        // Set the receiver's name and profile image
        receiverNameLabel.setText(receiverName);
        receiverProfileImage.setImage(new Image(profileImageUrl));
    }
    // AjouterAfficherMessageController class
    public void updateChatMessages() {
        // Get the messages from the database for both sender and receiver
        Set<Messagerie> senderMessages = serviceMessagerie.getAllMessagesByReciverAndSender(1); // Replace 1 with the sender's user ID
        Set<Messagerie> receiverMessages = serviceMessagerie.getAllMessagesByReciverAndSender(9); // Replace 9 with the receiver's user ID

        // Combine the messages and order them by date
        Set<Messagerie> allMessages = new TreeSet<>(Comparator.comparing(Messagerie::getDate));
        allMessages.addAll(senderMessages);
        allMessages.addAll(receiverMessages);

        // Clear the existing content
        chatVbox.getChildren().clear();

        // Iterate through the messages and add them to the chatVbox with margins
        for (Messagerie message : allMessages) {
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
        ImageView profileImage = new ImageView(new Image("/images/manh.png"));
        profileImage.setFitWidth(40.0); // Set the desired width
        profileImage.setFitHeight(40.0); // Set the desired height

        AnchorPane messagePane = new AnchorPane();
        messagePane.setStyle("-fx-background-color: #f2f5ec; -fx-background-radius: 0 30 30 30;");

        Label contentLabel = new Label(message.getContenu());
        contentLabel.setLayoutX(17.0);
        contentLabel.setLayoutY(10.0);
        //5955b3
        contentLabel.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold; -fx-font-size: 13; -fx-padding: 0 0 15 0;");

        contentLabel.setWrapText(true); // Enable text wrapping
        contentLabel.setMaxWidth(400.0); // Set a max width to trigger wrapping

        // Bind the prefWidth of the AnchorPane to the width of the Label
        messagePane.prefWidthProperty().bind(contentLabel.widthProperty().add(34.0)); // Adjusted for padding

        Label dateLabel = new Label(message.getDate().toString()); // Adjust this based on your date format
        dateLabel.setLayoutX(370.0); // Adjusted X position to place it in the bottom-right corner
        dateLabel.setLayoutY(40.0); // Adjusted Y position to place it below the message
        dateLabel.setStyle("-fx-text-fill: #888888; -fx-font-size: 10;");

        // Create a static button with an image
        // Create a static button with an image
        Button staticButton = new Button("", new ImageView(new Image("/images/de.png")));
        staticButton.setLayoutX(370);
        staticButton.setLayoutY(-40);

// Set the fit width and fit height for the image
        ((ImageView) staticButton.getGraphic()).setFitWidth(15);
        ((ImageView) staticButton.getGraphic()).setFitHeight(15);

        staticButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-background-radius: 10; -fx-font-size: 13; -fx-cursor: hand;");

// Attach an event handler to the button
        staticButton.setOnAction(event -> {
            // Show a confirmation dialog
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation Dialog");
            confirmationAlert.setHeaderText("Delete Message");
            confirmationAlert.setContentText("Are you sure you want to delete this message?");

            // Customize the buttons in the confirmation dialog
            ButtonType confirmButton = new ButtonType("Yes");
            ButtonType cancelButton = new ButtonType("No");

            confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);

            // Show and wait for the user's choice
            confirmationAlert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == confirmButton) {
                    // User clicked "Yes," so proceed with deletion
                    serviceMessagerie.supprimer(message.getId_message());
                    navigateToAfficherReclamationAction(event);
                } else {
                    // User clicked "No" or closed the dialog, do nothing
                }
            });
        });
        
        // <Button layoutX="519.0" layoutY="636.0" mnemonicParsing="false"prefHeight="32.0" prefWidth="98.0" style="-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-background-radius: 10; -fx-font-size: 13;" text="Cancel">
        if (message.getSender_id().getId_utilisateur() != 1) {
            profileImage = new ImageView(new Image("/images/manh.png"));
            profileImage.setFitWidth(40.0);
            profileImage.setFitHeight(40.0);
           // messageHbox.getChildren().addAll(messagePane, dateLabel);
            messagePane.getChildren().addAll(contentLabel);
            messageHbox.getChildren().addAll(profileImage, messagePane);
            messageHbox.getChildren().add(dateLabel);
            //messageHbox.getChildren().add(staticButton);
        } else {
             profileImage = new ImageView(new Image("/images/profile.png"));
            profileImage.setFitWidth(40.0);
            profileImage.setFitHeight(40.0);
            //messageHbox.getChildren().addAll(profileImage, messagePane, dateLabel);
            messagePane.getChildren().addAll(contentLabel);
            messageHbox.getChildren().addAll(profileImage, messagePane);
            messageHbox.getChildren().add(dateLabel);
            messageHbox.getChildren().add(staticButton);
        }

        /*messagePane.getChildren().addAll(contentLabel);
        messageHbox.getChildren().addAll(profileImage, messagePane);
        messageHbox.getChildren().add(dateLabel);*/

        return messageHbox;
    }

    public void navigateToAfficherReclamationAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterAfficherMessage.fxml"));
            TFmessage.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }

    }

    @FXML
    void ajouterMessageAction(ActionEvent event) {
        String messageContent = TFmessage.getText();

        // Check if TFmessage is empty
        if (messageContent.trim().isEmpty()) {
            // Show an alert indicating that the message is empty
            Alert emptyMessageAlert = new Alert(Alert.AlertType.WARNING);
            emptyMessageAlert.setTitle("Empty Message");
            emptyMessageAlert.setContentText("Please enter a non-empty message before sending.");
            emptyMessageAlert.showAndWait();
        } else {
            try {
                // Proceed to send the message if it's not empty
                serviceMessagerie.ajouter(new Messagerie("text", messageContent, new Date(), hachem, amen));
                TFmessage.setText("");
                navigateToAfficherReclamationAction(event);
            } catch (SQLException e) {
                // Show an alert for SQL exception
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }



}
