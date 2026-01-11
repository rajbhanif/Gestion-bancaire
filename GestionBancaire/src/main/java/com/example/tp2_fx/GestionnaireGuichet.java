package com.example.tp2_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class GestionnaireGuichet {
    private Banque banque;
    private List<Client> clients;
    private List<Compte> comptes;
    private List<Transaction> transactions;

    // Constructeur de la classe GestionnaireGuichet
    public GestionnaireGuichet(Banque banque) {
        this.banque = banque;
        this.clients = new ArrayList<>();
        this.comptes = new ArrayList<>();  // Ajoutez cette ligne pour initialiser la liste
        this.transactions = new ArrayList<>();
    }


    // Méthode pour ajouter un compte à la liste des comptes
    public void ajouterCompte(Client selectedClient, Compte compte) {
        comptes.add(compte);
    }

    // Méthode pour effectuer un retrait depuis un compte
    public void effectuerRetrait(Compte compte, double montant) {
        // try {
        if (compte.retrait(montant)) {
            transactions.add(new Transaction(montant, compte));
            System.out.println("Retrait de $" + montant + " effectué avec succès.");
        } else {
            System.out.println("Le retrait n'a pas pu être effectué. Montant invalide ou solde insuffisant.");
        }
        // } catch (Exception e) {
        // System.out.println("Une erreur s'est produite lors du retrait : " + e.getMessage());
        // }
    }


    // Méthode pour effectuer un dépôt dans un compte
    public void effectuerDepot(Compte compte, double montant) {
        if (compte.depot(montant)) {
            transactions.add(new Transaction(montant, compte));
            System.out.println("Dépôt de $" + montant + " effectué avec succès.");
        } else {
            System.out.println("Le dépôt n'a pas pu être effectué. Montant invalide.");
        }
    }

    // Méthode pour effectuer un virement entre deux comptes
    public void effectuerVirement(Compte source, Compte destinataire, double montant) {
        if (source.transfert(destinataire, montant)) {
            transactions.add(new Transaction(source, destinataire));
            System.out.println("Virement de $" + montant + " effectué avec succès.");
        } else {
            System.out.println("Le virement n'a pas pu être effectué. Montant invalide ou solde insuffisant.");
        }
    }

    // Méthode pour consulter le solde d'un compte
    public double consulterSolde(Compte compte) {
        return compte.getSoldeCompte();
    }

    // Méthode pour remplir le guichet de la banque


    // Méthode pour trouver un compte par son numéro
    public Compte trouverCompteParNumero(String numeroCompte) {
        for (Compte compte : comptes) {
            if (compte.getNumeroCompte().equals(numeroCompte)) {
                return compte;
            }
        }
        return null;
    }

    // Méthode pour effectuer un dépôt de chèque dans un compte
    public void depotCheque(String numeroNIP, double montantDepot) {
        Compte compte = trouverCompteParNumero(numeroNIP);
        if (compte != null) {
            compte.depot(montantDepot);
            System.out.println("Dépôt de chèque de $" + montantDepot + " effectué avec succès.");
        } else {
            System.out.println("Compte introuvable. Le dépôt de chèque a échoué.");
        }
    }

    // Méthode pour afficher le solde d'un compte
    public double afficheSoldeCompte(String numeroNIP) {
        Compte compte = trouverCompteParNumero(numeroNIP);
        if (compte != null) {
            return compte.getSoldeCompte();
        } else {
            System.out.println("Compte introuvable.");
            return 0.0;
        }
    }

    // Méthode pour valider un utilisateur en fonction de son code client et NIP
    public Client validerUtilisateur(String codeClient, String nip) {
        for (Client client : clients) {
            if (client.getCodeClient().equals(codeClient) && client.getNumeroNIP().equals(nip)) {
                return client;
            }
        }
        return null; // Aucun client correspondant au code client et NIP donnés
    }

    private static GestionnaireGuichet instance;


    GestionnaireGuichet() {
        clients = new ArrayList<>();
    }


    public static GestionnaireGuichet getInstance() {
        if (instance == null) {
            instance = new GestionnaireGuichet();
        }
        return instance;
    }

    static List<Transaction> getAllTransactions() {
        GestionnaireGuichet gestionnaireGuichet = GestionnaireGuichet.getInstance();
        if (gestionnaireGuichet != null) {
            return gestionnaireGuichet.transactions;
        } else {
            return new ArrayList<>(); // ou retournez une liste vide, selon vos besoins
        }
    }


    public Banque getBanque() {
        return banque;
    }

    public void setBanque() {
        this.banque = banque;
    }
    // Liste statique pour stocker les clients de manière persistante
    private static List<Client> allClients = new ArrayList<>();

    public void ajouterArgentPapierAuGuichet(double montantAjoute) {
    }

    // Méthode pour ajouter un client à la liste
    public void ajouterClient(Client client) {
        allClients.add(client);
    }

    // Méthode pour récupérer tous les clients
    public List<Client> getAllClients() {
        return allClients;
    }


    public List<Client> getListeClients() {
        return getAllClients();
    }

    public void ajouterCompte(Compte compte) {
        if (compte != null) {
            comptes.add(compte);
        } else {
            System.out.println("Erreur : Tentative d'ajouter un compte nul.");
        }
    }

    List<Compte> getAllComptes() {
        return new ArrayList<>(comptes);
    }

}
