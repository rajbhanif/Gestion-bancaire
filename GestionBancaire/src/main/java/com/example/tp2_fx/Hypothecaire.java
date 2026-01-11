package com.example.tp2_fx;

public class Hypothecaire extends Compte {
    // Constructeur de la classe Hypothecaire
    public Hypothecaire(String numeroNIP, String numeroCompte, double soldeCompte, double retraitMaximum, double montantTransfertMaximum) {
        super(numeroNIP, numeroCompte, soldeCompte, retraitMaximum, montantTransfertMaximum);
    }

    public Hypothecaire(Client client) {
        super(client.getNumeroNIP(), "N/A", 0.0, 0.0, 0.0);
    }

    // Méthode pour prélever un montant hypothécaire
    public void preleverMontantHypotheque(double montantPrelevement) {
        if (montantPrelevement > 0) {
            if (getSoldeCompte() >= montantPrelevement) {
                setSoldeCompte(getSoldeCompte() - montantPrelevement);
                System.out.println("Prélèvement hypothécaire de $" + montantPrelevement + " effectué avec succès.");
            } else {
                System.out.println("Solde insuffisant pour effectuer le prélèvement hypothécaire.");
            }
        } else {
            System.out.println("Le montant du prélèvement hypothécaire n'est pas valide.");
        }
    }

    // Méthode toString pour afficher les informations du compte hypothécaire
    @Override
    public String toString() {
        return "Compte Hypothécaire [numeroCompte=" + getNumeroCompte() + ", soldeCompte=" + getSoldeCompte() + "]";
    }
}

