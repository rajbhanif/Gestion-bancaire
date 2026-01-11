package com.example.tp2_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class creerClientController {

    @FXML
    private TextField codeField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField courrielField;
    @FXML
    private TextField nipField;
    @FXML
    private Button createButton;
    @FXML
    private Button affichage;

    private GestionnaireGuichet gestionnaireGuichet = new GestionnaireGuichet();

    public void setGestionnaireguicher(GestionnaireGuichet gestionnaireGuichet) {
        this.gestionnaireGuichet = gestionnaireGuichet;
    }

    public void initialize() {
        createButton.setDisable(true);

        codeField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        nomField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        prenomField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        telephoneField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        courrielField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
        nipField.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
    }

    private void updateButtonState() {
        boolean isAnyFieldEmpty = codeField.getText().trim().isEmpty() ||
                nomField.getText().trim().isEmpty() ||
                prenomField.getText().trim().isEmpty() ||
                telephoneField.getText().trim().isEmpty() ||
                courrielField.getText().trim().isEmpty() ||
                nipField.getText().trim().isEmpty();

        createButton.setDisable(isAnyFieldEmpty);
    }

    @FXML
    private void createClient(ActionEvent event) {
        Client newClient = new Client(
                codeField.getText(),
                nomField.getText(),
                prenomField.getText(),
                telephoneField.getText(),
                courrielField.getText(),
                nipField.getText()
        );
        gestionnaireGuichet.ajouterClient(newClient);
        createButton.setDisable(true);
        showFXML("successClient.fxml");
        new Tooltip();
    }

    private void showFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Désactiver le bouton après la fermeture de la fenêtre modale
            createButton.setDisable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void afficherListeClients() {
        List<Client> clients = gestionnaireGuichet.getAllClients();

        ListView<String> listView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();

        for (Client client : clients) {
            items.add("Prénom: " + client.getPrenom() + "\nNom: " + client.getNom() + "\nNIP: " + client.getNumeroNIP() + "\nCode: " + client.getCodeClient() + "\n");
        }


        listView.setItems(items);

        // Personnalisation du style
        listView.setStyle("-fx-background-color: grey;"); // Fond blanc
        listView.setStyle("-fx-control-inner-background: grey;"); // Fond blanc

        // Création de la boîte de dialogue
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Liste des clients");
        alert.setHeaderText(null);
        alert.getDialogPane().setMinWidth(300);
        alert.getDialogPane().setContent(listView);

        // Personnalisation du style de la boîte de dialogue
        alert.getDialogPane().setStyle("-fx-background-color: grey;"); // Fond blanc

        alert.showAndWait();
    }


    private listeClientsController listeClientsController = new listeClientsController();

    public void setListeClientsController(listeClientsController listeClientsController) {
        this.listeClientsController = listeClientsController;
    }

    @FXML
    private void handleRetourButtonClick() {
        showFXML("admin.fxml");
    }
}
