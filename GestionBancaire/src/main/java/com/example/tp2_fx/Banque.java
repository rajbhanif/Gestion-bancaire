package com.example.tp2_fx;

import java.util.ArrayList;

public class Banque  {
    private double montantMaximum;
    private double montantRemplissage;
    private double argentPapier;

    // Constructeur de la classe Banque
    public Banque(double montantMaximum, double montantRemplissage) {
        this.montantMaximum = montantMaximum;
        this.montantRemplissage = montantRemplissage;
        this.argentPapier = 0.0;
    }

    // Méthode pour obtenir le montant maximum de la banque
    public double getMontantMaximum() {
        return montantMaximum;
    }

    // Méthode pour obtenir le montant de remplissage
    public double getMontantRemplissage() {
        return montantRemplissage;
    }

    // Méthode pour obtenir le montant d'argent papier dans la banque
    public double getArgentPapier() {
        return argentPapier;
    }

    // Méthode pour remplir le guichet de la banque
    public void remplirGuichet() {
        if (argentPapier + montantRemplissage <= montantMaximum) {
            argentPapier += montantRemplissage;
            System.out.println("Le guichet a été rempli avec $" + montantRemplissage + " en argent papier.");
        } else {
            System.out.println("Le montant de remplissage dépasse la capacité maximale du guichet.");
        }
    }

    // Méthode toString pour afficher les informations de la banque
    @Override
    public String toString() {
        return "Banque [argentPapier=" + argentPapier + "]";
    }


}

