package com.example.tp2_fx;

import javafx.beans.binding.BooleanExpression;

class Transaction {
    private int numeroTransaction; // Ajout de la variable pour le numéro de transaction
    private double montant;
    private Compte compte;
    private Compte compteTransfert; // Le compte destinataire, le cas échéant

    // Constructeur pour une transaction sans destinataire
    public Transaction(double montant, Compte compte) {
        this.numeroTransaction = numeroTransaction; // Initialisation du numéro de transaction
        this.montant = montant;
        this.compte = compte;
        this.compteTransfert = null; // Par défaut, il n'y a pas de compte destinataire
    }

    // Constructeur pour une transaction avec destinataire
    public Transaction(Compte compte, Compte compteTransfert) {
        this.numeroTransaction = numeroTransaction; // Initialisation du numéro de transaction
        this.montant = montant;
        this.compte = compte;
        this.compteTransfert = compteTransfert;
    }

    // Méthode pour obtenir le numéro de transaction
    public int getNumeroTransaction() {
        return numeroTransaction;
    }

    // Méthode pour obtenir le montant de la transaction
    public double getMontant() {
        return montant;
    }

    // Méthode pour obtenir le compte émetteur de la transaction
    public Compte getCompte() {
        return compte;
    }

    // Méthode pour obtenir le compte destinataire de la transaction (le cas échéant)
    public Compte getCompteTransfert() {
        return compteTransfert;
    }

    // Méthode toString pour afficher les informations de la transaction
    @Override
    public String toString() {
        String compteDestinataire = (compteTransfert != null) ? compteTransfert.getNumeroCompte() : "N/A";
        return "Transaction [numeroTransaction=" + numeroTransaction + ", montant=" + montant
                + ", compte=" + compte.getNumeroCompte() + ", compteTransfert=" + compteDestinataire + "]";
    }


}

