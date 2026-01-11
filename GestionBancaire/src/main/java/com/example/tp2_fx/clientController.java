package com.example.tp2_fx;



import java.io.IOException;
import java.util.Optional;import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class clientController {

    @FXML
    private TextField codeclient;

    @FXML
    private PasswordField nip; // Utiliser PasswordField à la place de TextField

    private int failedLoginAttempts = 0;

    @FXML
    private void handleContinuerButtonClick(ActionEvent event) {
        String codeClientText = codeclient.getText();
        String nipText = nip.getText(); // Utiliser getPassword pour obtenir le texte masqué

        if (authenticate(codeClientText, nipText)) {
            loadCompteScene();
        } else {
            failedLoginAttempts++;
            System.out.println("Authentication failed. Please try again.");

            if (failedLoginAttempts >= 3) {
                showErrorMessage();
                return;
            }
        }
    }

    static boolean authenticate(String codeClient, String nip) {
        // Comparez les valeurs avec celles attendues
        return "user123".equals(codeClient) && "pass123".equals(nip);
    }

    private void loadCompteScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("compte.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Compte");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins
        }
    }

    static void showErrorMessage() {
        try {
            FXMLLoader loader = new FXMLLoader(clientController.class.getResource("echec.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Ajouter le gestionnaire d'événements pour fermer l'application après la confirmation
            stage.setOnCloseRequest(windowEvent -> System.exit(0));

            stage.setTitle("Erreur d'authentification");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
