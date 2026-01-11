package com.example.tp2_fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class soldeController {

    @FXML
    private TextField solde1;

    @FXML
    private TextField solde2;

    @FXML
    private TextField solde3;

    @FXML
    private TextField soldeTotal;

    @FXML
    private Button retour;

    private EpargneController epargneController;
    private hypothecaireController HypothecaireController;
    private chequeController ChequeController;

    // Méthode appelée lors de l'initialisation du contrôleur
    @FXML
    public void initialize() {
        // Initialisez les champs à 0 lors de l'initialisation
        solde1.setText("0.0");
        solde2.setText("0.0");
        solde3.setText("0.0");
        soldeTotal.setText("0.0");
        // Initialisez les contrôleurs
        epargneController = new EpargneController();
        HypothecaireController = new hypothecaireController();
        ChequeController = new chequeController();
        updateSoldeFields();    }


    // Méthode pour mettre à jour les champs de solde
    private void updateSoldeFields() {
        // Récupérer les soldes depuis les contrôleurs respectifs
        double soldeEpargne = epargneController.getSoldeTotal();
        double soldeHypothecaire = HypothecaireController.getSoldeTotal();
        double soldeCheque = ChequeController.getSoldeTotal();

        // Mettre à jour les champs de solde dans l'interface utilisateur
        solde1.setText(String.valueOf(soldeEpargne));
        solde2.setText(String.valueOf(soldeHypothecaire));
        solde3.setText(String.valueOf(soldeCheque));

        // Calculer et mettre à jour le solde total
        double total = soldeEpargne + soldeHypothecaire + soldeCheque;
        soldeTotal.setText(String.valueOf(total));
    }


    // Méthode appelée lorsqu'on clique sur le bouton "Retour"
    @FXML
    private void btnRetour() {
        showFXML("compte.fxml");
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
}