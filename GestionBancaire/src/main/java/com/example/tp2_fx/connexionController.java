package com.example.tp2_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class connexionController {

    private clientController clientController;

    @FXML
    private void handleAdminButtonClick(ActionEvent event) {
        loadNewScene("connexionAdmin.fxml");
    }

    @FXML
    private void handleClientButtonClick(ActionEvent event) {
        loadNewScene("client.fxml");
    }

    private void loadNewScene(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Définir le titre de la fenêtre
            stage.setTitle("Application");

            // Ajoute le gestionnaire d'événements pour la confirmation de sortie
            stage.setOnCloseRequest(windowEvent -> {
                System.out.println("Fermeture de la fenêtre détectée.");
                showSortiePage(stage);
            });

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showSortiePage(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sortie.fxml"));
            Parent root = loader.load();
            Stage sortieStage = new Stage();
            sortieStage.initModality(Modality.APPLICATION_MODAL);
            sortieStage.initOwner(stage);

            // Passer la référence de la fenêtre principale et de la scène précédente au contrôleur de sortie
            sortieController sortieController = loader.getController();
            sortieController.setStage(stage, stage.getScene());

            sortieStage.setScene(new Scene(root));
            sortieStage.setTitle("Confirmation de sortie");
            sortieStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClientController(clientController clientController) {
        this.clientController = clientController;
    }


    private void someMethod() {
        // Utilisez la référence du clientController pour appeler showErrorMessage
        if (clientController != null) {
            clientController.showErrorMessage();
        }

    }
}
