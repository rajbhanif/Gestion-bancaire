package com.example.tp2_fx;

public class Cheque extends Compte {
    private final double fraisPaiementFacture;
    private final double montantFactureMaximum;

    // Constructeur de la classe Cheque
    public Cheque(String numeroNIP, String numeroCompte, double soldeCompte, double retraitMaximum, double montantTransfertMaximum,
                  double fraisPaiementFacture, double montantFactureMaximum) {
        super(numeroNIP, numeroCompte, soldeCompte, retraitMaximum, montantTransfertMaximum);
        this.fraisPaiementFacture = fraisPaiementFacture;
        this.montantFactureMaximum = montantFactureMaximum;
    }

    public Cheque(Client client, double fraisPaiementFacture, double montantFactureMaximum) {
        super(client.getNumeroNIP(), "N/A", 0.0, 0.0, 0.0);
        this.fraisPaiementFacture = fraisPaiementFacture;
        this.montantFactureMaximum = montantFactureMaximum;
    }

    // Méthode pour effectuer un paiement de facture depuis le compte chèque
    public void paiementFacture(double montant) {
        if (montant > 0 && montant <= montantFactureMaximum && montant <= getSoldeCompte()) {
            setSoldeCompte(getSoldeCompte() - montant);
            System.out.println("Paiement de facture de $" + montant + " effectué avec succès.");
        } else {
            System.out.println("Le paiement de facture n'a pas pu être effectué. Vérifiez le montant ou le solde.");
        }
    }

    // Méthode toString pour afficher les informations du compte chèque
    @Override
    public String toString() {
        return "Compte Chèque: " + getNumeroCompte() + ", Solde: " + getSoldeCompte();
    }
}

