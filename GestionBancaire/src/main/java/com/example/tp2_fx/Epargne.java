package com.example.tp2_fx;
public class Epargne extends Compte {
    private final double tauxInteret;

    // Constructeur de la classe Epargne
    public Epargne(String numeroNIP, String numeroCompte, double soldeCompte, double retraitMaximum, double montantTransfertMaximum, double tauxInteret) {
        super(numeroNIP, numeroCompte, soldeCompte, retraitMaximum, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
    }

    public Epargne(Client client, double tauxInteret) {
        super(client.getNumeroNIP(), "N/A", 0.0, 0.0, 0.0);
        this.tauxInteret = tauxInteret;
    }

    // Méthode pour effectuer un paiement d'intérêt sur le compte épargne
    public void paiementInteret() {
        double interet = getSoldeCompte() * (tauxInteret / 100.0);
        depot(interet);
        System.out.println("Paiement d'intérêt de $" + interet + " effectué avec succès.");
    }

    // Méthode toString pour afficher les informations du compte épargne
    @Override
    public String toString() {
        return "Compte Épargne: " + getNumeroCompte() + ", Solde: " + getSoldeCompte();
    }
}
