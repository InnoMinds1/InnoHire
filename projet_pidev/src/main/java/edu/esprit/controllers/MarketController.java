package edu.esprit.controllers;

import edu.esprit.entities.Etablissement;
import edu.esprit.services.ServiceEtablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import edu.esprit.tests.MainFX;
import edu.esprit.services.MyListener;
import edu.esprit.entities.Etablissement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class MarketController implements Initializable {
    @FXML
    private VBox chosenetablissementCard;

    @FXML
    private Label etablissementNameLable;

    @FXML
    private Label etablissementPriceLabel;

    @FXML
    private Label label_no_data;
@FXML
    private HBox Hbox_no_data;

    @FXML
    private ImageView etablissementImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Etablissement> etablissements = new ArrayList<>();
    private Image image;
    private MyListener myListener;



    private List<Etablissement> getData() throws SQLException {
        ServiceEtablissement se = new ServiceEtablissement();
        Set<Etablissement> etablissements = se.getAll();
        List<Etablissement> modifiedEtablissements = new ArrayList<>();

        for (Etablissement etablissement : etablissements) {
            // Assuming setNom, setPrice, and setImage methods are available in the Etablissement class
            Etablissement modifiedEtablissement = new Etablissement();
            modifiedEtablissement.setIdEtablissement(etablissement.getIdEtablissement());
            modifiedEtablissement.setNom(etablissement.getNom());
            // If setPrice method is available, you can uncomment the following line
            // modifiedEtablissement.setPrice(etablissement.getPrice());
            modifiedEtablissement.setImage("/img/" + etablissement.getImage());

            modifiedEtablissements.add(modifiedEtablissement);
        }

        return modifiedEtablissements;
    }


    private void setChosenetablissement(Etablissement etablissement) {
        etablissementNameLable.setText(etablissement.getNom());
        //etablissementPriceLabel.setText(MainFX.CURRENCY + etablissement.getPrice());
        image = new Image(getClass().getResourceAsStream(etablissement.getImage()));
        etablissementImg.setImage(image);
      /*  chosenetablissementCard.setStyle("-fx-background-color: #" + etablissement.getColor() + ";\n" +
                "    -fx-background-radius: 30;");*/

    }

   /* @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            etablissements.addAll(getData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (etablissements.size() > 0) {
            setChosenetablissement(etablissements.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(etablissement etablissement) {
                    setChosenetablissement(etablissement);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < etablissements.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(etablissements.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            etablissements.addAll(getData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setupListeners();

        if (etablissements.isEmpty()) {
            // Display a message when there are no elements in the list
            label_no_data.setText("Vous n'avez pas encore d'établissement.");
            Hbox_no_data.setVisible(true);  // Show the HBox
        } else {
            setChosenetablissement(etablissements.get(0));
            populateGrid();
            Hbox_no_data.setVisible(false);  // Hide the HBox when there is data
        }
    }

    private void setupListeners() {
        myListener = new MyListener() {
            @Override
            public void onClickListener(Etablissement etablissement) {
                setChosenetablissement(etablissement);
            }

            @Override
            public void onDeleteListener(Etablissement etablissement) {
                etablissements.remove(etablissement);
                populateGrid();
            }
        };
    }

    private void populateGrid() {
        grid.getChildren().clear();

        int column = 0;
        int row = 1;

        for (int i = 0; i < etablissements.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/item.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ItemController itemController = fxmlLoader.getController();
            itemController.setData(etablissements.get(i), myListener);

            if (column == 3) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row); //(child, column, row)
            GridPane.setMargin(anchorPane, new Insets(10));

            // Adjust grid dimensions
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
        }
    }



    public void ajouterEtablissement(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterEtablissement.fxml"));
            grid.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }
}
