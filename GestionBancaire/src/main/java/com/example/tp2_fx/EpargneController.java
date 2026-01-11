package com.example.tp2_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class EpargneController {

    @FXML
    private TextField retrait;
    @FXML
    private TextField depot;
    @FXML
    private TextField soldTotal;

    private  double soldeTotal = 0.0;

    private margeController margeController= new margeController();


    @FXML
    private void initialize() {
        updateSoldeText();
    }

    @FXML
    private void handleRetourButtonClick(ActionEvent event) {
        showFXML("compte.fxml");
    }

    @FXML
    private void handleContinuerButtonClick(ActionEvent event) {
        try {
            double montantRetrait = Double.parseDouble(retrait.getText());
            double montantDepot = Double.parseDouble(depot.getText());

            if (montantRetrait % 10 != 0) {
                showFXML("multiple.fxml");
                return;
            }

            if (montantRetrait > 1000) {
                showFXML("erreur.fxml");
                return;
            }



            if (montantRetrait > soldeTotal) {
                boolean confirmationAjoutMarge = showCustomConfirmationDialog(
                        "Ajout à la marge de crédit",
                        "Le montant de retrait dépasse le solde total. Souhaitez-vous ajouter à la marge de crédit?"
                );

                if (confirmationAjoutMarge) {
                    margeController.handleTransfert(montantRetrait);
                    showFXML("reussiteTransaction.fxml");
                    showFXML("marge.fxml");
                    return;
                } else {
                    showFXML("annuleTransaction.fxml");
                    return;
                }
            }



            if (showConfirmationDialog()) {
                // L'utilisateur a confirmé, effectué la transaction
                soldeTotal += montantDepot;  // Ajouter le montant de dépôt au solde total
                soldeTotal -= montantRetrait;  // Retirer le montant de retrait du solde total

                // Mise à jour du champ soldTotal dans l'interface utilisateur
                updateSoldeText();

                // Afficher une fenêtre pour succès de transaction (reussiteTransaction.fxml)
                showFXML("reussiteTransaction.fxml");

                // Réinitialiser les champs retraits et depot à zéro après la transaction réussie
                retrait.setText("0");
                depot.setText("0");
            } else {
                // L'utilisateur a annulé la transaction
                showFXML("annuleTransaction.fxml");
            }

        } catch (NumberFormatException e) {
            showFXML("chiffre.fxml");
        }
    }

    private void updateSoldeText() {
        soldTotal.setText(String.valueOf(soldeTotal));
    }

    private boolean showCustomConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private boolean showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment effectuer cette transaction?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
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
    public void handleSoldeButtonClick(ActionEvent actionEvent) {
        showFXML("solde.fxml");
    }


    public void handleTransaction( double montantPaiementFac) {
        soldeTotal += montantPaiementFac;
        soldTotal.setText(String.valueOf(soldeTotal));
    }

    public void handleTransfert( double montantTransfert) {
        soldeTotal += montantTransfert;
        soldTotal.setText(String.valueOf(soldeTotal));
    }

    public void ajouterInteret(double tauxInteret) {
        soldeTotal += tauxInteret;
    }


    public double getSoldeTotal() {
    return soldeTotal;

    }
}
