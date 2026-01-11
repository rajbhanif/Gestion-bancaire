package com.example.tp2_fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class margeController {

    @FXML
    private TextField solde1;

    private double soldeMargeCredit = 0.0;

    @FXML
    private void initialize() {
        updateSolde1Text();
    }

    @FXML
    private void btnRetour() {
        showFXML("compte.fxml");
    }

    @FXML
    private void btnSolde() {
        showFXML("solde.fxml");
    }

    private void updateSolde1Text() {
        solde1.setText(String.valueOf(soldeMargeCredit));
    }

    public void handleTransfert(double montantTransfert) {
        soldeMargeCredit += montantTransfert;
        solde1.setText(String.valueOf(soldeMargeCredit));
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

    public void augmenterSolde() {
        double augmentation = soldeMargeCredit * 0.05;
        soldeMargeCredit += augmentation;
           }
}