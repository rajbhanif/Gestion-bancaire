package com.example.tp2_fx;

public class Marge extends Compte {
    private double tauxInteret;

    // Constructeur de la classe Marge
    public Marge(String numeroNIP, String numeroCompte, double soldeCompte, double retraitMaximum, double montantTransfertMaximum, double tauxInteret) {
        super(numeroNIP, numeroCompte, soldeCompte, retraitMaximum, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
    }

    public Marge(Client client) {
        // Utilisez des valeurs fictives pour les autres paramètres, ou ajustez selon vos besoins
        super(client.getNumeroNIP(), "N/A", 0.0, 0.0, 0.0);
        this.tauxInteret = tauxInteret;
    }



    // Méthode pour obtenir le taux d'intérêt de la marge de crédit
    public double getTauxInteret() {
        return tauxInteret;
    }

    // Méthode pour augmenter le solde de la marge de crédit en fonction d'un pourcentage d'augmentation
    public void augmenterSoldeMarge(double pourcentageAugmentation) {
        if (pourcentageAugmentation > 0) {
            double interet = getSoldeCompte() * (tauxInteret / 100.0);
            double augmentation = (interet * pourcentageAugmentation / 100.0);
            depot(augmentation);
            System.out.println("Augmentation du solde de la marge de crédit de $" + augmentation + " effectuée avec succès.");
        } else {
            System.out.println("Le pourcentage d'augmentation n'est pas valide.");
        }
    }

    // Méthode toString pour afficher les informations de la marge de crédit
    @Override
    public String toString() {
        return "Compte Marge de Crédit [numeroCompte=" + getNumeroCompte() + ", soldeCompte=" + getSoldeCompte() + "]";
    }

    public void setSolde(double nouveauSolde) {
        // Implémentez votre logique ici
        if (nouveauSolde >= 0) {
            // Utiliser la méthode publique pour mettre à jour le solde
            super.setSoldeCompte(nouveauSolde);
            System.out.println("Le solde de la marge de crédit a été mis à jour avec succès.");
        } else {
            System.out.println("Le nouveau solde doit être un montant positif.");
        }
    }

    public double getSolde() {
        // Utiliser la méthode publique pour obtenir le solde
        return super.getSoldeCompte();
    }
}
