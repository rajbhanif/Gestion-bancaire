// ListeClientsController.java
package com.example.tp2_fx;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class listeClientsController {

    private ListView<String> clientsListView = new ListView<String>();
    public void afficherListeClients(List<Client> clients) {
        clientsListView.getItems().clear();

        for (Client client : clients) {
            String clientInfo = "Nom: " + client.getNom() + ", Solde du compte: " + client.getCodeClient() +", Solde du compte: " + client.getPrenom();
            clientsListView.getItems().add(clientInfo);
        }
    }
}
