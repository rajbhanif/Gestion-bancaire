package com.example.tp2_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class creerCompteController {

    @FXML
    private TextField codeField;

    @FXML
    private TextField nipField;

    @FXML
    private TextField soldeField; // Ajout du champ de solde

    @FXML
    private ChoiceBox<String> typeCompteChoiceBox;

    @FXML
    private ComboBox<String> clientComboBox;

    @FXML
    private Button btnSolde;

    @FXML
    private Button btnContinuer;

    @FXML
    private GestionnaireGuichet gestionnaireGuichet = GestionnaireGuichet.getInstance();

    @FXML
    public void setGestionnaireGuichet(GestionnaireGuichet gestionnaireGuichet) {
        this.gestionnaireGuichet = gestionnaireGuichet;
        initializeComboBox();
    }

    @FXML
    private void initialize() {
        // Initialisations, si nécessaire
        typeCompteChoiceBox.getItems().addAll("Compte Chèque", "Compte Épargne", "Compte Marge", "Compte Hypothécaire");
    }

    @FXML
    private void initializeComboBox() {
        if (gestionnaireGuichet != null) {
            gestionnaireGuichet.getAllClients();
            List<Client> clients = gestionnaireGuichet.getAllClients();
            for (Client client : clients) {
                clientComboBox.getItems().add(client.getNom() + " " + client.getPrenom() + " - NIP: " + client.getNumeroNIP());
            }
        } else {
            System.err.println("Erreur : gestionnaireGuichet non initialisé.");
        }
    }

    @FXML
    private void handleContinuerButtonClick() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/guicher_auto/form-compte.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Créer un nouveau compte");
        stage.setScene(new Scene(root));
        creerCompteController creerCompteController = loader.getController();
        creerCompteController.setGestionnaireGuichet(gestionnaireGuichet);
        ComboBox<String> clientComboBox = new ComboBox<>();
        List<Client> clients = gestionnaireGuichet.getAllClients();
        for (Client client : clients) {
            clientComboBox.getItems().add(client.getNom() + " " + client.getPrenom() + " - NIP: " + client.getNumeroNIP());
        }

        creerCompteController.setGestionnaireGuichet(gestionnaireGuichet);
        stage.showAndWait();
    }

    @FXML
    private void listComptes() {
        List<Compte> comptes = gestionnaireGuichet.getAllComptes();

        ListView<String> listView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Compte compte : comptes) {
            items.add("Type: " + compte.getNumeroCompte() + " - NIP: " + compte.getNumeroNIP());
        }

        listView.setItems(items);

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Liste des comptes");
        dialog.setHeaderText(null);
        dialog.getDialogPane().setMinWidth(450);
        dialog.getDialogPane().setContent(listView);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.showAndWait();
    }

    private void showFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRetourButtonClick() {
        showFXML("admin.fxml");
    }
}
