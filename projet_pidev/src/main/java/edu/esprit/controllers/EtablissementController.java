package edu.esprit.controllers;

import edu.esprit.entities.*;
import edu.esprit.services.ServiceEtablissement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import edu.esprit.services.MyListener;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class EtablissementController implements Initializable {
    @FXML
    private VBox chosenetablissementCard;

    @FXML
    private Label etablissementNameLable;

    @FXML
    private Label etablissementCodeLabel;

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

    @FXML
    private Label typeETF;
    @FXML
    private Label lieuETF;
    @FXML
    private Label cinETF;
    @FXML
private AnchorPane grandAnchor;
    @FXML
    private AnchorPane NavBar ;
    @FXML
    private AnchorPane anchorContenu;
    @FXML
    private Label nameUserLabel;
    @FXML
    private Button afficherWalletListImg;


    private List<Etablissement> etablissements = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    ServiceEtablissement se = new ServiceEtablissement();
    //-----------------------Wallet-------------------------------
    @FXML
    private Label balanceLabel;

    @FXML
    private Button ajouterWalletBtn;

    @FXML
    private Button acheterQuizzBtn;




    private List<Etablissement> getData() throws SQLException {



         Set<Etablissement> etablissements = se.getAll();//admin
        if (CurrentUser.getRole() != 0) {
          etablissements = se.getByUserId(CurrentUser.getId_utilisateur());//front
        }



        List<Etablissement> modifiedEtablissements = new ArrayList<>();

        for (Etablissement etablissement : etablissements) {
            // Assuming setNom, setPrice, and setImage methods are available in the Etablissement class
            Etablissement modifiedEtablissement = new Etablissement();
            modifiedEtablissement.setIdEtablissement(etablissement.getIdEtablissement());
            modifiedEtablissement.setNom(etablissement.getNom());
            modifiedEtablissement.setLieu(etablissement.getLieu());
            modifiedEtablissement.setCodeEtablissement(etablissement.getCodeEtablissement());
            modifiedEtablissement.setTypeEtablissement(etablissement.getTypeEtablissement());


            // If setPrice method is available, you can uncomment the following line
            // modifiedEtablissement.setPrice(etablissement.getPrice());
            modifiedEtablissement.setImage("/img/" + etablissement.getImage());

            modifiedEtablissement.setUser(etablissement.getUser());

            modifiedEtablissements.add(modifiedEtablissement);
        }

        return modifiedEtablissements;
    }


    private void setChosenetablissement(Etablissement etablissement) {



        etablissementNameLable.setText(etablissement.getNom());
        etablissementCodeLabel.setText(String.valueOf(etablissement.getCodeEtablissement()));

        image = new Image(getClass().getResourceAsStream(etablissement.getImage()));
        etablissementImg.setImage(image);
        lieuETF.setText(etablissement.getLieu());
        typeETF.setText(etablissement.getTypeEtablissement());
        cinETF.setText(String.valueOf(etablissement.getUser().getCin()));




                CurrentEtablissement.setIdEtablissement(etablissement.getIdEtablissement());




       Wallet wallet = se.getWalletByEtablissement(etablissement);

        if (wallet != null) {
            // Le portefeuille existe pour cet établissement




            if (wallet.getStatus() != 0) {
                int balance = wallet.getBalance();
                balanceLabel.setText(String.valueOf(balance) + "DT");
            } else {
                balanceLabel.setText("Non Actif");
            }



            CurrentWallet.setIdWallet(wallet.getIdWallet());

            // Afficher balanceLabel et masquer ajouterWalletBtn
            balanceLabel.setVisible(true);
            afficherWalletListImg.setVisible(true);
            ajouterWalletBtn.setVisible(false);
            acheterQuizzBtn.setVisible(true);

        } else {

            // Masquer balanceLabel et afficher ajouterWalletBtn
            balanceLabel.setVisible(false);
            afficherWalletListImg.setVisible(false);
            ajouterWalletBtn.setVisible(true);
            acheterQuizzBtn.setVisible(false);
            CurrentWallet.setIdWallet(0);
        }



        chosenetablissementCard.requestLayout(); // Force la mise à jour du layout
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameUserLabel.setText("Admin " + CurrentUser.getNom());

        /* Navbar */
        if (CurrentUser.getRole() != 0) {
            NavBar.setVisible(false);
            NavBar.setManaged(false);

            anchorContenu.setLayoutX(0);
            grandAnchor.setPrefWidth(anchorContenu.getPrefWidth());

            nameUserLabel.setText(CurrentUser.getNom());
        }

        try {
            etablissements.addAll(getData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setupListeners();

        if (etablissements.isEmpty()) {

            Hbox_no_data.setVisible(true);
            chosenetablissementCard.setVisible(false); // Hide chosenetablissementCard


        } else {
            Hbox_no_data.setVisible(false);
            chosenetablissementCard.setVisible(true); // Show chosenetablissementCard
            setChosenetablissement(etablissements.get(0));


            populateGrid();


        }
    }

    private void setupListeners() {
        myListener = new MyListener() {
            @Override
            public void onClickListener(Etablissement etablissement) {
                setChosenetablissement(etablissement);

                CurrentEtablissement.setIdEtablissement(etablissement.getIdEtablissement());
            }

            @Override
            public void onDeleteListener(Etablissement etablissement) {
                etablissements.remove(etablissement);
                rafraichirPage();

                if (etablissements.isEmpty()) {
                    // Display a message when there are no elements in the list
                    label_no_data.setText("Vous n'avez pas encore d'établissement.");
                    Hbox_no_data.setVisible(true);
                   // chosenetablissementCard.setVisible(false);// Show the HBox


                }
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
            fxmlLoader.setLocation(getClass().getResource("/ItemEtablissement.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            EtablissementItemController etablissementItemController = fxmlLoader.getController();
            etablissementItemController.setData(etablissements.get(i), myListener);

            if (column == 2) {
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
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sorry");
            alert.setTitle("Error");
            alert.show();
        }
    }
    //-------------------WALLETS------------------

    public void ajouterWallet(ActionEvent actionEvent) {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/AjouterWallet.fxml"));
                grid.getScene().setRoot(root);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Sorry");
                alert.setTitle("Error");
                alert.show();
            }


    }




    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setTitle("Error");
        alert.show();
    }

    public void rafraichirPage() {
        try {
            etablissements.clear(); // Efface les données existantes
            etablissements.addAll(getData()); // Recharge les données

            if (etablissements.isEmpty()) {

                Hbox_no_data.setVisible(true);
                chosenetablissementCard.setVisible(false); // Hide chosenetablissementCard



            } else {
                Hbox_no_data.setVisible(false);
                chosenetablissementCard.setVisible(true); // Show chosenetablissementCard
                setChosenetablissement(etablissements.get(0));
                populateGrid();


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void openAiHelper(ActionEvent actionEvent) {
    }

    public void afficherWalletList(ActionEvent actionEvent) {
        Etablissement etablissementConnecte = null;
        try {
            etablissementConnecte = se.getOneByID(CurrentEtablissement.getIdEtablissement());
        } catch (SQLException e) {
            e.printStackTrace();
            afficherAlerte("Erreur lors de la récupération de l'établissement connecté : " + e.getMessage());
        }

        // Vérifier d'abord si etablissementConnecte est null
        if (etablissementConnecte != null) {
            Wallet walletConnecte = null;
            try {
                walletConnecte = se.getWalletByEtablissement(etablissementConnecte);
            } catch (Throwable t) {
                t.printStackTrace();
                afficherAlerte("Erreur lors de la récupération du portefeuille : " + t.getMessage());
            }

            if (walletConnecte != null) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/AfficherWallet.fxml"));
                    grid.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                    afficherAlerte("Erreur lors du chargement d'AfficherWallet.fxml : " + e.getMessage());
                }
            } else {
                // Afficher une alerte demandant d'ajouter un portefeuille
                afficherAlerte("Ajoutez un portefeuille à votre établissement.");
            }
        } else {
            // Si etablissementConnecte est null, afficher un message spécifique
            afficherAlerte("Ajoutez un portefeuille à votre établissement.");
        }
    }
}
