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

public class chequeController {

    @FXML
    private TextField depot;

    @FXML
    private TextField soldTotal;

    @FXML
    private TextField paiementFac;

    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    private TextField retrait;

    @FXML
    private TextField transfert;

    @FXML
    private ComboBox<String> comboBox2;

    @FXML
    private Button btnSolde;

    @FXML
    private Button btnContinuer;

    @FXML
    private Button btnRetour;



        // ... autres champs ...

        private double soldeTotal = 0.0;
        private EpargneController epargneController = new EpargneController();
        private hypothecaireController hypothecaireController = new hypothecaireController();
        private margeController margeController = new margeController();


    private void initializeComboBox() {
        // Initialisations, si nécessaire
        comboBox1.getItems().addAll("Compte Épargne", "Compte Hypothécaire");
        comboBox2.getItems().addAll("Compte Épargne", "Compte Marge", "Compte Hypothécaire");

        // Sélectionner le premier élément par défaut
        comboBox1.getSelectionModel().selectFirst();
        comboBox2.getSelectionModel().selectFirst();
    }




    @FXML
    private void initialize() {
        soldTotal.setText(String.valueOf(soldeTotal));
        initializeComboBox();
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
            double montantRetrait = Double.parseDouble(retrait.getText());
            double montantPaiementFac = Double.parseDouble(paiementFac.getText());
            double montantTransfert = Double.parseDouble(transfert.getText());

            if (montantRetrait % 10 != 0) {
                showFXML("multiple.fxml");
                return;
            }

            // Vérifier si le montant de retrait est inférieur à 1000
            if (montantRetrait >= 1000) {
                showFXML("erreur.fxml");
                return;
            }
            String comptePaiementFac = comboBox1.getSelectionModel().getSelectedItem();

            if (montantPaiementFac > 0 && comptePaiementFac != null && !comptePaiementFac.isEmpty()) {
                // Afficher une fenêtre de confirmation
                double frais = 1.25;
                montantPaiementFac += frais;
                if (showConfirmationDialog()) {

                    // Vérifier si le solde est suffisant après l'ajout des frais
                    if (soldeTotal >= montantPaiementFac) {
                        // Soustraire le montant du transfert et les frais du solde total
                        soldeTotal -= montantPaiementFac;

                        // Mise à jour du champ soldTotal dans l'interface utilisateur
                        soldTotal.setText(String.valueOf(soldeTotal));

                        // Afficher une fenêtre pour succès de transaction (reussiteTransaction.fxml)
                        showFXML("reussiteTransaction.fxml");
                        paiementFac.setText("0");
                    } else {
                        // Le solde n'est pas suffisant, afficher une fenêtre d'erreur (soldeErreur.fxml)
                        showFXML("soldeErreur.fxml");
                    }


                    // Effectuer le paiement en fonction du compte sélectionné
                    switch (comptePaiementFac) {
                        case "Compte Épargne":
                            epargneController.handleTransaction(montantPaiementFac);  // Retirer le montant du compte Épargne
                            break;
                        case "Compte Hypothécaire":
                            hypothecaireController.handleTransaction(montantPaiementFac);  // Retirer le montant du compte Hypothécaire
                            break;
                        default:
                            break;
                    }

                } else {
                    // L'utilisateur a annulé la transaction
                    showFXML("annuleTransaction.fxml");
                }
            }

            String compteTransfert = comboBox2.getSelectionModel().getSelectedItem();

            if (montantTransfert > 0 && compteTransfert != null && !compteTransfert.isEmpty()) {

                if (showConfirmationDialog()) {

                    // Vérifier si le solde est suffisant après l'ajout des frais
                    if (soldeTotal >= montantTransfert) {
                        // Soustraire le montant du transfert et les frais du solde total
                        soldeTotal -= montantTransfert;

                        // Mise à jour du champ soldTotal dans l'interface utilisateur
                        soldTotal.setText(String.valueOf(soldeTotal));

                        // Afficher une fenêtre pour succès de transaction (reussiteTransaction.fxml)
                        showFXML("reussiteTransaction.fxml");
                        transfert.setText("0");
                    } else {
                        // Le solde n'est pas suffisant, afficher une fenêtre d'erreur (soldeErreur.fxml)
                        showFXML("soldeErreur.fxml");
                    }

                    // Effectuer le transfert en fonction du compte sélectionné
                    switch (compteTransfert) {
                        case "Compte Épargne":
                            epargneController.handleTransfert(montantTransfert);  // Ajouter le montant au compte Épargne
                            break;
                        case "Compte Hypothécaire":
                            hypothecaireController.handleTransfert(montantTransfert);  // Ajouter le montant au compte Hypothécaire
                            break;
                        case "Compte Marge":
                            margeController.handleTransfert(montantTransfert);  // Ajouter le montant au compte marge
                            break;
                        default:
                            break;
                    }

                } else {
                    // L'utilisateur a annulé la transaction
                    showFXML("annuleTransaction.fxml");
                }
            }

            // Afficher une fenêtre de confirmation
            if (showConfirmationDialog()) {

                soldeTotal -= montantRetrait;
                // L'utilisateur a confirmé, effectué la transaction
                soldeTotal += montantDepot;  // Ajouter le montant de dépôt au solde total
                // Mise à jour du champ soldTotal dans l'interface utilisateur
                soldTotal.setText(String.valueOf(soldeTotal));

                // Afficher une fenêtre pour succès de transaction (reussiteTransaction.fxml)
                showFXML("reussiteTransaction.fxml");
                retrait.setText("0");
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

    @FXML
    public void handleSoldeButtonClick(ActionEvent actionEvent) {
        showFXML("solde.fxml");
    }

    public double getSoldeTotal() {
        return soldeTotal;
    }

}


