package com.example.tp2_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class sortieController {

    private Stage stage;
    private Scene previousScene;

    public void setStage(Stage stage, Scene previousScene) {
        this.stage = stage;
        this.previousScene = previousScene;
    }

    @FXML
    private void handleOuiButtonClick(ActionEvent event) {
        // Fermer l'application
        System.exit(0);
    }

    @FXML
    private void handleNonButtonClick(ActionEvent event) {
        // Fermer la fenêtre de confirmation
        stage.close();

        // Revenir à la scène précédente
        if (previousScene != null) {
            stage.setScene(previousScene);
        }
    }
}
