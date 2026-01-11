package com.example.tp2_fx;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String codeClient;
    private String nom;
    private String prenom;
    private String telephone;
    private String courriel;
    private String numeroNIP;
    private List<Compte> comptes;

    // Constructeur de la classe Client
    public Client(String codeClient, String nom, String prenom, String telephone, String courriel, String numeroNIP) {
        this.codeClient = codeClient;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.courriel = courriel;
        this.numeroNIP = numeroNIP;
        this.comptes = new ArrayList<>();
    }
    // Méthode pour obtenir le code du client
    public String getCodeClient() {
        return codeClient;
    }
    // Méthode pour obtenir le nom du client
    public String getNom() {
        return nom;
    }
    // Méthode pour obtenir le prénom du client
    public String getPrenom() {
        return prenom;
    }
    // Méthode pour obtenir le numéro NIP du client
    public String getNumeroNIP() {
        return numeroNIP;
    }
    // Méthode pour obtenir la liste des comptes du client
    public List<Compte> getComptes() {
        return comptes;
    }
    // Méthode pour ajouter un compte à la liste des comptes du client
    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
    }
    // Méthode toString pour afficher les informations du client
    @Override
    public String toString() {
        return "Client [codeClient=" + codeClient + ", nom=" + nom + ", prenom=" + prenom + "]";
    }

    public boolean isBlocked() {
        // Retourne vrai si au moins un des comptes du client est bloqué, sinon retourne faux
        return comptes.stream().anyMatch(Compte::isBlocked);
    }

    public void blockAccount() {
        // Bloque tous les comptes du client
        comptes.forEach(Compte::block);
    }

    public void unblockAccount() {
        // Débloque tous les comptes du client
        comptes.forEach(Compte::unblock);
    }
}

