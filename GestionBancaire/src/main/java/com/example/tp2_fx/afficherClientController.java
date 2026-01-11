// AfficherClientController.java
package com.example.tp2_fx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class afficherClientController {

    @FXML
    private Label clientInfoLabel;

    public void setClientInformation(Client client) {
        if (client != null) {
            String clientInfo = "Nom: " + client.getNom() + ", Solde du compte: " + client.getCodeClient() +", Solde du compte: " + client.getPrenom();
            clientInfoLabel.setText(clientInfo);
        }
    }
}
