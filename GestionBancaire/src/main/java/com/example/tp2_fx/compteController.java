package com.example.tp2_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class compteController {
    @FXML
    private void handleChequeButtonClick(ActionEvent event) {
        loadNewScene("cheque.fxml");
    }
    @FXML
    private void handleepargneButtonClick(ActionEvent event) {
        loadNewScene("Epargne.fxml");
    }
    @FXML
    private void handleHypothecaireButtonClick(ActionEvent event) {
        loadNewScene("hypothecaire.fxml");
    }
    @FXML
    private void handleMargeButtonClick(ActionEvent event) { loadNewScene("marge.fxml"); }
    @FXML
    private void handleSoldeButtonClick(ActionEvent event) { loadNewScene("solde.fxml"); }

    private void loadNewScene(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    
    
}