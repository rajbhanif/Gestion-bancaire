package com.example.tp2_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class connexionAdminController {

    @FXML
    private TextField codeAdmin;

    @FXML
    private PasswordField nip;

    private int failedLoginAttempts = 0;

    @FXML
    private void handleContinuerButtonClick(ActionEvent event) {
        String codeAdminText = codeAdmin.getText();
        String nipText = nip.getText();

        if (authenticate(codeAdminText, nipText)) {
            loadAdminDashboardScene();
        } else {
            failedLoginAttempts++;
            System.out.println("Authentication failed. Please try again.");

            if (failedLoginAttempts >= 3) {
                showErrorMessage();
                return;
            }
        }
    }

    static boolean authenticate(String codeAdmin, String nip) {
        return "admin".equals(codeAdmin) && "123".equals(nip);
    }

    private void loadAdminDashboardScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tableau de bord de l'administrateur");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    static void showErrorMessage() {
        try {
            FXMLLoader loader = new FXMLLoader(connexionAdminController.class.getResource("echec.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Ajoute le gestionnaire d'événements pour fermer l'application après la confirmation
            stage.setOnCloseRequest(windowEvent -> System.exit(0));

            stage.setTitle("Erreur d'authentification");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
