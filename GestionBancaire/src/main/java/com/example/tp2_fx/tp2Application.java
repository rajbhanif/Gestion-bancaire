package com.example.tp2_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class tp2Application extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("connexion.fxml"));
            Parent root = loader.load();

            // Obtenez une référence vers le contrôleur de connexion
            connexionController controller = loader.getController();

            // Obtenez une référence vers le contrôleur de client
            FXMLLoader clientLoader = new FXMLLoader(getClass().getResource("client.fxml"));
            Parent clientRoot = clientLoader.load();
            clientController clientController = clientLoader.getController();

            // Passez la référence du clientController au connexionController
            controller.setClientController(clientController);

            primaryStage.setTitle("Connexion");
            primaryStage.setScene(new Scene(root, 342, 320));

            // Utilisez setOnHiding au lieu de setOnCloseRequest
            primaryStage.setOnHiding(event -> {
                System.out.println("Hiding event triggered.");
                controller.showSortiePage(primaryStage);
            });

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
