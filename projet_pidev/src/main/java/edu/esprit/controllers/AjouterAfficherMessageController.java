package edu.esprit.controllers;

import edu.esprit.entities.*;
import edu.esprit.services.ServiceMessagerie;
import edu.esprit.services.ServiceUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    @FXML
    private Label messageError;
    @FXML
    private Label emptyMessage;
    String userPhoto;
    Utilisateur userReciver;
    @FXML
    private Label userFullName;
    @FXML
    private Label userEmail;
    @FXML
    private Label userCin;
    @FXML
    private Label userRole;
    @FXML
    private ImageView userPic;
    @FXML
    private AnchorPane RepresentantPane;

    @FXML
    private AnchorPane AdminPane;
    @FXML
    private AnchorPane CandidatPane;


    private final ServiceMessagerie serviceMessagerie = new ServiceMessagerie();
    private final ServiceUtilisateur su = new ServiceUtilisateur();
    Utilisateur userSender = su.getOneByID(CurrentUser.getId_utilisateur());




    //Utilisateur amen=new Utilisateur(1,11417264,"dhawadi","hachem","bizerte","123456789","edit.png");
    //Utilisateur userReciver=new Utilisateur(9,11417264,"dhawadi","hachem","bizerte","123456789","edit.png");

    public void initData(Reclamation selectedReclamation) {
        userReciver = selectedReclamation.getUser();
        userPhoto=userReciver.getImage();
        //System.out.println(userReciver);
        updateReceiverInfo();
        updateChatMessages();
        // You can do additional initialization with the passed data if needed
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (CurrentUser.getRole() == 0) {
            // Admin role, so show AdminPane and hide RepresentantPane
            AdminPane.setVisible(true);
            RepresentantPane.setVisible(false);
            CandidatPane.setVisible(false);
        } else if (CurrentUser.getRole()==1){
            // Representant role, so show RepresentantPane and hide AdminPane
            RepresentantPane.setVisible(true);
            AdminPane.setVisible(false);
            CandidatPane.setVisible(false);
        }else {
            CandidatPane.setVisible(true);
            AdminPane.setVisible(false);
            RepresentantPane.setVisible(false);
        }
       // updateReceiverInfo();
        //updateChatMessages();
    }
    public void updateReceiverInfo() {
        // Get the name and profile image URL of the receiver
        //String receiverName = serviceMessagerie.getOneByID(9).getReciver_id().getNom(); // Replace with the actual receiver's name
        String receiverName = userReciver.getNom()+" "+userReciver.getPrenom();
        String profileImageUrl = userReciver.getImage(); // Replace with the actual image URL

        // Set the receiver's name and profile image
        receiverNameLabel.setText(receiverName);
        String imageName = userReciver.getImage();
        if (imageName != null && !imageName.isEmpty()) {
            String imagePath = "/images/" + imageName; // Assuming images are stored in src/main/resources/images
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            receiverProfileImage.setImage(image);
            if (userReciver != null) {
                //userFullName.setText(userReciver.getNom()+" "+userReciver.getPrenom());
                userEmail.setText(userReciver.getAdresse());
                userCin.setText(String.valueOf(userReciver.getCin()));
                userRole.setText("Represnetant");
                //userPic.setImage(image);
            }
        } else {
            // Set a default image if the name is not available
            receiverProfileImage.setImage(new Image(getClass().getResource("/images/edit.png.jpg").toExternalForm()));
        }
        //receiverProfileImage.setImage(new Image(profileImageUrl));
    }
    // AjouterAfficherMessageController class
    public void updateChatMessages() {
        // Get the messages from the database for both sender and receiver
        Set<Messagerie> senderMessages = serviceMessagerie.getAllMessagesByReciverAndSender(CurrentUser.getId_utilisateur(), userReciver.getId_utilisateur()); // Replace 1 with the sender's user ID
        Set<Messagerie> receiverMessages = serviceMessagerie.getAllMessagesByReciverAndSender(userReciver.getId_utilisateur(), CurrentUser.getId_utilisateur()); // Replace 9 with the receiver's user ID

        // Combine the messages and order them by date
        Set<Messagerie> allMessages = new TreeSet<>(Comparator.comparing(Messagerie::getDate));
        allMessages.addAll(senderMessages);
        allMessages.addAll(receiverMessages);

        // Clear the existing content
        chatVbox.getChildren().clear();

        // Check if there are no messages
        if (allMessages.isEmpty()) {
            emptyMessage.setText("Start chatting now!");
            emptyMessage.setVisible(true);
        } else {
            emptyMessage.setVisible(false);

            // Iterate through the messages and add them to the chatVbox with margins
            for (Messagerie message : allMessages) {
                HBox messageBox = createMessageBox(message);
                VBox.setMargin(messageBox, new Insets(0.0, 0.0, 10.0, 0.0)); // Set top and bottom margins
                chatVbox.getChildren().add(messageBox);
            }
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

        Label   contentLabel = new Label(message.getContenu());
        contentLabel.setLayoutX(17.0);
        contentLabel.setLayoutY(10.0);

        //5955b3
        contentLabel.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold; -fx-font-size: 13; -fx-padding: 0 0 15 0;");

        contentLabel.setWrapText(true); // Enable text wrapping
        contentLabel.setMaxWidth(350); // Set a max width to trigger wrapping

        // Bind the prefWidth of the AnchorPane to the width of the Label
        messagePane.prefWidthProperty().bind(contentLabel.widthProperty().add(34.0)); // Adjusted for padding

        Label dateLabel = new Label(message.getDate().toString()); // Adjust this based on your date format
        dateLabel.setLayoutX(370.0); // Adjusted X position to place it in the bottom-right corner
        dateLabel.setLayoutY(40.0); // Adjusted Y position to place it below the message
        dateLabel.setStyle("-fx-text-fill: #888888; -fx-font-size: 10;");

        // Create a static button with an image
        // Create a static button with an image
        Button DeleteButton = new Button("", new ImageView(new Image("/images/de.png")));
        DeleteButton.setLayoutX(370);
        DeleteButton.setLayoutY(-40);

        Button EditButton = new Button("", new ImageView(new Image("/images/message.png")));
        EditButton.setLayoutX(380);
        EditButton.setLayoutY(-40);

// Set the fit width and fit height for the image
        ((ImageView) DeleteButton.getGraphic()).setFitWidth(15);
        ((ImageView) DeleteButton.getGraphic()).setFitHeight(15);
        ((ImageView) EditButton.getGraphic()).setFitWidth(17);
        ((ImageView) EditButton.getGraphic()).setFitHeight(17);

        DeleteButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-background-radius: 10; -fx-font-size: 13; -fx-cursor: hand;");
        EditButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-background-radius: 10; -fx-font-size: 13; -fx-cursor: hand;");

        EditButton.setOnAction(event -> {
            // Open a pop-up window to edit the message
            openEditMessagePopup(message);
        });
// Attach an event handler to the button
        DeleteButton.setOnAction(event -> {
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
                    serviceMessagerie.supprimer(message.getIdMessage());
                    updateChatMessages();
                    //navigateToAfficherReclamationAction(event);
                } else {
                    // User clicked "No" or closed the dialog, do nothing
                }
            });
        });

        // <Button layoutX="519.0" layoutY="636.0" mnemonicParsing="false"prefHeight="32.0" prefWidth="98.0" style="-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-background-radius: 10; -fx-font-size: 13;" text="Cancel">
        //System.out.println(currentUser.getId_utilisateur());
        //System.out.println(currentUser.getRole());
        if(CurrentUser.getRole()==0){
            if (message.getSenderId().getId_utilisateur()!=CurrentUser.getId_utilisateur()) {
                //  if (message.getSender_id().getId_utilisateur() == 1) {
                //System.out.println(receiverProfileImage);
                profileImage = new ImageView(new Image("/images/"+userPhoto));
                profileImage.setFitWidth(40.0);
                profileImage.setFitHeight(40.0);
                // messageHbox.getChildren().addAll(messagePane, dateLabel);
                messagePane.getChildren().addAll(contentLabel);
                messageHbox.getChildren().addAll(profileImage, messagePane);
                messageHbox.getChildren().add(dateLabel);
                messageHbox.getChildren().add(DeleteButton);
                messageHbox.getChildren().add(EditButton);
            } else {
                profileImage = new ImageView(new Image("/images/"+CurrentUser.getProfileImagePath()));
                profileImage.setFitWidth(40.0);
                profileImage.setFitHeight(40.0);
                //DeleteButton.setLayoutX(370);
                //DeleteButton.setLayoutY(-40);
                //messageHbox.getChildren().addAll(profileImage, messagePane, dateLabel);
                messagePane.getChildren().addAll(contentLabel);
                messageHbox.getChildren().addAll(profileImage, messagePane);
                messageHbox.getChildren().add(dateLabel);
                messageHbox.getChildren().add(DeleteButton);
                messageHbox.getChildren().add(EditButton);
            }
        }else {
            if (message.getSenderId().getId_utilisateur()!=CurrentUser.getId_utilisateur()) {
                //  if (message.getSender_id().getId_utilisateur() == 1) {
                //System.out.println(receiverProfileImage);
                profileImage = new ImageView(new Image("/images/"+userPhoto));
                profileImage.setFitWidth(40.0);
                profileImage.setFitHeight(40.0);
                // messageHbox.getChildren().addAll(messagePane, dateLabel);
                messagePane.getChildren().addAll(contentLabel);
                messageHbox.getChildren().addAll(profileImage, messagePane);
                messageHbox.getChildren().add(dateLabel);
                //messageHbox.getChildren().add(DeleteButton);
                //messageHbox.getChildren().add(EditButton);
            } else {
                System.out.println(CurrentUser.getProfileImagePath());
                profileImage = new ImageView(new Image("/images/"+CurrentUser.getProfileImagePath()));
                profileImage.setFitWidth(40.0);
                profileImage.setFitHeight(40.0);
                //DeleteButton.setLayoutX(370);
                //DeleteButton.setLayoutY(-40);
                //messageHbox.getChildren().addAll(profileImage, messagePane, dateLabel);
                messagePane.getChildren().addAll(contentLabel);
                messageHbox.getChildren().addAll(profileImage, messagePane);
                messageHbox.getChildren().add(dateLabel);
                messageHbox.getChildren().add(DeleteButton);
                messageHbox.getChildren().add(EditButton);
            }
        }



        /*messagePane.getChildren().addAll(contentLabel);
        messageHbox.getChildren().addAll(profileImage, messagePane);
        messageHbox.getChildren().add(dateLabel);*/

        return messageHbox;
    }

    public void navigateToAfficherReclamationAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherReclamation.fxml"));
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
            // Set the messageError label to red
            messageError.setTextFill(Color.RED);
            //messageError.setText("Please enter a non-empty message before sending.");
            TFmessage.setStyle("-fx-border-color:  #FF0000;");
        } else {
            try {
                // Proceed to send the message if it's not empty
                //System.out.println(CurrentUser.getId_utilisateur());
            //System.out.println(userReciver);
                serviceMessagerie.ajouter(new Messagerie("text", messageContent, new Date(), userSender, userReciver));
                TFmessage.setText("");
                updateChatMessages();
                //navigateToAfficherReclamationAction(event);
            } catch (SQLException e) {
                // Show an alert for SQL exception
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private void openEditMessagePopup(Messagerie message) {
        // Create a new stage for the pop-up window
        Stage editStage = new Stage();
        editStage.setTitle("Edit Message");

        // Create a TextArea to allow the user to edit the message content
        TextArea editTextArea = new TextArea(message.getContenu());
        editTextArea.setWrapText(true); // Enable text wrapping
        editTextArea.setMaxWidth(300); // Set a max width to trigger wrapping
        editTextArea.setPrefRowCount(5);
        editTextArea.setPrefColumnCount(20);

        // Create a button to save the edited message
        Button saveButton = new Button("Update", new ImageView(new Image("/images/edit.png")));
        ((ImageView) saveButton.getGraphic()).setFitWidth(15);
        ((ImageView) saveButton.getGraphic()).setFitHeight(15);

        saveButton.setStyle("-fx-background-color:  #008000; -fx-text-fill: #FFFFFF; -fx-font-weight: bold; -fx-background-radius: 10; -fx-font-size: 13;");
        saveButton.setOnAction(saveEvent -> {
            // Update the message content in the database
            message.setContenu(editTextArea.getText());
            //message.setDate(new Date());
            serviceMessagerie.modifier(message);
            updateChatMessages();
            // Close the pop-up window
            editStage.close();
            // Update the chat messages
            //updateChatMessages();
        });

        // Create a VBox to hold the TextArea and the Save button
        VBox editVBox = new VBox(editTextArea, saveButton);
        editVBox.setSpacing(10.0);
        editVBox.setPadding(new Insets(10.0));

        // Create a scene and set it on the stage
        Scene editScene = new Scene(editVBox);
        editStage.setScene(editScene);

        // Show the pop-up window
        editStage.show();
    }



}
