package com.example.tp2_fx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Compte {
    private String numeroNIP;
    private String numeroCompte;
    private double soldeCompte;
    private double retraitMaximum;
    private double montantTransfertMaximum;

    // Constructeur de la classe Compte
    public Compte(String numeroNIP, String numeroCompte, double soldeCompte, double retraitMaximum, double montantTransfertMaximum) {
        this.numeroNIP = numeroNIP;
        this.numeroCompte = numeroCompte;
        this.soldeCompte = soldeCompte;
        this.retraitMaximum = retraitMaximum;
        this.montantTransfertMaximum = montantTransfertMaximum;
    }


    // Méthode pour obtenir le numéro NIP du compte
    public String getNumeroNIP() {
        return numeroNIP;
    }
    // Méthode pour obtenir le numéro de compte
    public String getNumeroCompte() {
        return numeroCompte;
    }
    // Méthode pour obtenir le solde du compte
    public double getSoldeCompte() {
        return soldeCompte;
    }
    // Méthode pour obtenir le montant maximum de retrait autorisé
    public double getRetraitMaximum() {
        return retraitMaximum;
    }
    // Méthode pour obtenir le montant maximum de transfert autorisé
    public double getMontantTransfertMaximum() {
        return montantTransfertMaximum;
    }
    // Méthode pour définir le solde du compte
    public void setSoldeCompte(double soldeCompte) {
        this.soldeCompte = soldeCompte;
    }
    // Méthode pour effectuer un retrait depuis le compte
    public boolean retrait(double montant) {
        if (montant > 0 && montant <= retraitMaximum && montant <= soldeCompte) {
            soldeCompte -= montant;
            System.out.println("Retrait de $" + montant + " effectué avec succès.");
            return true;
        } else {
            System.out.println("Le retrait n'a pas pu être effectué. Vérifiez le montant ou le solde.");
            return false;
        }
    }
    // Méthode pour effectuer un dépôt dans le compte
    public boolean depot(double montant) {
        if (montant > 0) {
            soldeCompte += montant;
            System.out.println("Dépôt de $" + montant + " effectué avec succès.");
            return true;
        } else {
            System.out.println("Le dépôt n'a pas pu être effectué. Vérifiez le montant.");
            return false;
        }
    }
    // Méthode pour effectuer un transfert vers un autre compte
    public boolean transfert(Compte compteDestination, double montant) {
        if (montant > 0 && montant <= montantTransfertMaximum && montant <= soldeCompte) {
            soldeCompte -= montant;
            compteDestination.depot(montant);
            System.out.println("Transfert de $" + montant + " effectué avec succès.");
            return true;
        } else {
            System.out.println("Le transfert n'a pas pu être effectué. Vérifiez le montant ou le solde.");
            return false;
        }
    }
    // Méthode toString pour afficher les informations du compte
    @Override
    public String toString() {
        return "Compte [numeroCompte=" + numeroCompte + ", soldeCompte=" + soldeCompte + "]";
    }
    private boolean blocked = false;

    // ... (constructeur et autres méthodes)

    // Méthode pour vérifier si le compte est bloqué
    public boolean isBlocked() {
        return blocked;
    }

    // Méthode pour bloquer le compte
    public void block() {
        blocked = true;
        System.out.println("Le compte a été bloqué.");
    }

    // Méthode pour débloquer le compte
    public void unblock() {
        blocked = false;
        System.out.println("Le compte a été débloqué.");
    }


}
