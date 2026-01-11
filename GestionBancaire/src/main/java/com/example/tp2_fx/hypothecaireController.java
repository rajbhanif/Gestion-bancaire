package com.example.tp2_fx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.TextField;

public class hypothecaireController {

    @FXML
    private TextField depot;
    @FXML
    private TextField soldTotal;

    // Variable pour suivre le solde total
    @FXML
    private static double soldeTotal = 0.0;

    public static double getSoldeTotal() {
        return soldeTotal;
    }

    @FXML
    private void initialize() {
        // Initialiser le champ soldTotal avec la valeur initiale
        soldTotal.setText(String.valueOf(soldeTotal));
    }

    @FXML
    private void handleRetourButtonClick(ActionEvent event) {
        showFXML("compte.fxml");
    }

    @FXML
    private void handleContinuerButtonClick(ActionEvent event) {


        try {
            // Vérifier si le montant de dépôt est positif
            double montantDepot = Double.parseDouble(depot.getText());
            if (montantDepot <= 0) {
                showFXML("echec.fxml");
                return;
            }

            // Afficher une fenêtre de confirmation
            if (showConfirmationDialog()) {
                // L'utilisateur a confirmé, effectué la transaction
                soldeTotal += montantDepot;  // Ajouter le montant de dépôt au solde total

                // Mise à jour du champ soldTotal dans l'interface utilisateur
                soldTotal.setText(String.valueOf(soldeTotal));

                // Afficher une fenêtre pour succès de transaction (reussiteTransaction.fxml)
                showFXML("reussiteTransaction.fxml");
                depot.setText("0");
            } else {
                // L'utilisateur a annulé la transaction
                showFXML("annuleTransaction.fxml");
            }

        } catch (NumberFormatException e) {
            // En cas d'erreur de conversion (lettre au lieu d'un chiffre), afficher une fenêtre pour chiffre.fxml
            showFXML("chiffre.fxml");
        }
    }

    // Méthode pour afficher une fenêtre de confirmation
    private boolean showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment effectuer cette transaction?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    // Méthode pour afficher une fenêtre FXML
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

    public void handleTransaction( double montantPaiementFac) {
        // Implement your transaction logic here
        soldeTotal += montantPaiementFac;
        soldTotal.setText(String.valueOf(soldeTotal));
    }

    @FXML
    public void handleSoldeButtonClick(ActionEvent actionEvent) {
        showFXML("solde.fxml");
    }

    public void handleTransfert( double montantTransfert) {
        // Implement your transaction logic here
        soldeTotal += montantTransfert;
        soldTotal.setText(String.valueOf(soldeTotal));
    }


    public boolean preleverMontant(double montant) {
        if (montant > 0 && montant <= soldeTotal) {
            soldeTotal -= montant;
            return true;
        }
        return false;
    }

}
