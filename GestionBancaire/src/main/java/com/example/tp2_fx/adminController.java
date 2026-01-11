package com.example.tp2_fx;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class adminController {

    private GestionnaireGuichet gestionnaireGuichet= new GestionnaireGuichet();

    @FXML
    private Button creerclient;

    @FXML
    private Button creercompte;

    @FXML
    private Button liste;

    @FXML
    private Button bloquer;

    @FXML
    private Button ajout;

    @FXML
    private Button debloquer;

    @FXML
    private Button payer;

    @FXML
    private Button prelever;

    @FXML
    private Button augmenter;

    @FXML
    void btncreerclient(ActionEvent event) {
        showFXML("creerClient.fxml");
    }

    @FXML
    void btncreercompte(ActionEvent event) {
        showFXML("creerCompte.fxml");
    }

    @FXML
    void btnList(ActionEvent event) {
        showFXML("creerCompte.fxml");
    }

    @FXML
    void btnAjout(ActionEvent event) {
        showFXML("AjoutPanelController.fxml");
    }

    @FXML
    void btnPrelever(ActionEvent event) {
        showFXML("preleverController.fxml");
    }

    @FXML
    private void afficherTransactions() {
        List<Transaction> transactions = GestionnaireGuichet.getAllTransactions();

        ListView<String> listView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Transaction transaction : transactions) {
            items.add(transaction.toString());
        }

        listView.setItems(items);

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Liste des transactions");
        dialog.setHeaderText(null);
        dialog.getDialogPane().setMinWidth(400);
        dialog.getDialogPane().setContent(listView);

        dialog.showAndWait();
    }


    @FXML
    private void bloquerDebloquerClient() {
        List<Client> clients = gestionnaireGuichet.getAllClients();

        // Créer une boîte de dialogue de choix multiple
        ChoiceDialog<Client> dialog = new ChoiceDialog<>(null, clients);
        dialog.setTitle("Choisir un client");
        dialog.setHeaderText("Sélectionnez le client que vous souhaitez bloquer ou débloquer :");
        dialog.setContentText("Client :");

        Optional<Client> selectedClient = dialog.showAndWait();

        selectedClient.ifPresent(client -> {
            // Créer une boîte de dialogue de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Bloquer/Débloquer le client");
            alert.setContentText("Voulez-vous bloquer ou débloquer le client sélectionné ?");

            // Option pour confirmer le blocage/déblocage
            ButtonType boutonBloquer = new ButtonType("Bloquer");
            ButtonType boutonDebloquer = new ButtonType("Débloquer");

            // Ajouter les boutons à la boîte de dialogue
            alert.getButtonTypes().setAll(boutonBloquer, boutonDebloquer, ButtonType.CANCEL);

            // Afficher la boîte de dialogue de confirmation et attendre la réponse de l'utilisateur
            alert.showAndWait().ifPresent(response -> {
                if (response == boutonBloquer) {
                    client.blockAccount();
                    showAlert("Opération réussie", "Le client a été bloqué avec succès.");
                } else if (response == boutonDebloquer) {
                    client.unblockAccount();
                    showAlert("Opération réussie", "Le client a été débloqué avec succès.");
                }
            });
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

    public void fermerGuichet() {
        // Créer une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Êtes-vous sûr de vouloir fermer le guichet ?");
        alert.setContentText("Cela va fermer l'application.");

        // Option pour confirmer la sortie
        ButtonType boutonConfirmer = new ButtonType("Confirmer");
        // Option pour annuler la sortie
        ButtonType boutonAnnuler = new ButtonType("Annuler", ButtonType.CANCEL.getButtonData());

        // Ajouter les boutons à la boîte de dialogue
        alert.getButtonTypes().setAll(boutonConfirmer, boutonAnnuler);

        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
        alert.showAndWait().ifPresent(response -> {
            if (response == boutonConfirmer) {
                // Si l'utilisateur a confirmé, fermer l'application
                Platform.exit();
            } else {
                // Sinon, l'utilisateur a annulé, redirigé vers une autre vue
                showFXML("connexionController.fxml");
            }
        });
    }

    private double soldeGuichet = 0;

    @FXML
    private void ajouterArgentPapierAuGuichet(ActionEvent actionEvent) {
        // Demander à l'utilisateur combien d'argent papier il souhaite ajouter
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter de l'argent papier");
        dialog.setHeaderText(null);
        dialog.setContentText("Veuillez entrer le montant d'argent papier à ajouter :");

        Optional<String> result = dialog.showAndWait();

        // Traiter la réponse de l'utilisateur
        if (result.isPresent()) {
            // Récupérer le montant depuis la réponse de l'utilisateur
            try {
                double montantAjoute = Double.parseDouble(result.get());

                if (soldeGuichet + montantAjoute > 20000) {
                    // Gérer la limite atteinte (par exemple, afficher un message d'erreur)
                    showAlert("Limite atteinte", "La limite d'ajout d'argent papier est de 20 000$. Limite atteinte.");
                    return;
                }

                // Ajouter l'argent papier au guichet
                soldeGuichet += montantAjoute;

                // Afficher un message de succès
                showAlert("Succès", "Ajout d'argent papier au guichet réussi. Nouveau solde : " + soldeGuichet);
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer un montant valide.");
            }
        }
    }

    @FXML
    private void payerInteretComptesEpargne(ActionEvent actionEvent) {
        // Définir le taux d'intérêt à 1%
        double tauxInteret = 1.0/100;

        // Récupérer tous les comptes d'épargne
        List<EpargneController> comptesEpargne = getAllEpargneControllers(); // Remplacez cette méthode par la façon dont vous récupérez vos comptes d'épargne

        // Appliquer l'intérêt à tous les comptes d'épargne
        for (EpargneController epargneController : comptesEpargne) {
            epargneController.ajouterInteret(tauxInteret);
        }

        // Afficher un message de succès
        showAlert("Succès", "Paiement d'intérêt aux comptes épargne réussi.");
    }

    private List<EpargneController> getAllEpargneControllers() {
        List<EpargneController> epargneControllers = new ArrayList<>();
        epargneControllers.add(new EpargneController());
        epargneControllers.add(new EpargneController());
        // ...

        return epargneControllers;
    }

    public void augmenterSoldeMarges() {
        List<margeController> margesCredits = getAllMargesCredits();
        for (margeController margeCredit : margesCredits) {
            margeCredit.augmenterSolde();
        }

        showAlert("Succès", "Paiement d'intérêt aux comptes marges réussi.");

    }

    // Méthode pour récupérer toutes les marges de crédit
    private List<margeController> getAllMargesCredits() {
        List<margeController> margesCredits = new ArrayList<>();


        margesCredits.add(new margeController());
        margesCredits.add(new margeController());


        return margesCredits;
    }

    // Méthode pour prélever des montants hypothécaires
    public void preleverMontantHypo() {
        List<hypothecaireController> comptesHypo = getAllHypothecaireControllers();

        // Demander à l'administrateur de choisir le compte hypothécaire
        ChoiceDialog<hypothecaireController> dialog = new ChoiceDialog<>(comptesHypo.get(0), comptesHypo);
        dialog.setTitle("Prélèvement hypothécaire");
        dialog.setHeaderText(null);
        dialog.setContentText("Choisissez le compte hypothécaire :");

        Optional<hypothecaireController> selectedCompteHypo = dialog.showAndWait();

        // Traiter la réponse de l'administrateur
        selectedCompteHypo.ifPresent(compteHypo -> {
            // Demander le montant à prélever
            TextInputDialog montantDialog = new TextInputDialog();
            montantDialog.setTitle("Prélèvement hypothécaire");
            montantDialog.setHeaderText(null);
            montantDialog.setContentText("Entrez le montant à prélever :");

            Optional<String> montantResult = montantDialog.showAndWait();

            // Traiter la réponse du montant
            montantResult.ifPresent(montantStr -> {
                try {
                    double montant = Double.parseDouble(montantStr);

                    // Prélever le montant du compte hypothécaire
                    boolean prelevementReussi = compteHypo.preleverMontant(montant);

                    if (prelevementReussi) {
                        showAlert("Succès", "Prélèvement de $" + montant + " du compte hypothécaire réussi.");
                    } else {
                        showAlert("Erreur", "Le prélèvement a échoué. Montant invalide ou solde insuffisant.");
                    }
                } catch (NumberFormatException e) {
                    showAlert("Erreur", "Veuillez entrer un montant valide.");
                }
            });
        });
    }

    // Méthode pour récupérer tous les comptes hypothécaires (à remplacer avec votre propre logique)
    private List<hypothecaireController> getAllHypothecaireControllers() {
        List<hypothecaireController> comptesHypo = new ArrayList<>();

        // Ajoutez vos comptes hypothécaires à la liste
        comptesHypo.add(new hypothecaireController());
        comptesHypo.add(new hypothecaireController());

        return comptesHypo;
    }



}
