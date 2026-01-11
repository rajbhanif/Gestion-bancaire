# Gestion Bancaire

Application de **gestion bancaire** développée en **Java (JavaFX)** permettant de gérer des clients, des comptes et différents types d’opérations bancaires à travers une interface graphique claire et structurée.

## Aperçu du Projet

Cette application simule le fonctionnement d’un système bancaire. Elle propose des interfaces distinctes pour les **clients** et pour l’**administration**, avec gestion des comptes, consultation des soldes et opérations bancaires courantes.

Le projet repose sur une architecture JavaFX avec plusieurs contrôleurs dédiés à chaque fonctionnalité.

## Fonctionnalités

### Gestion des Clients

* Création de nouveaux clients
* Affichage de la liste des clients
* Consultation des informations d’un client

### Gestion des Comptes Bancaires

* Création de comptes bancaires
* Consultation du solde
* Gestion de différents types de comptes :

  * Compte chèque
  * Compte hypothécaire
  * Marge de crédit

### Opérations Bancaires

* Consultation du solde
* Navigation entre les différents services
* Gestion des erreurs et écrans d’échec

### Authentification

* Connexion client
* Connexion administrateur
* Interfaces distinctes selon le rôle

## Caractéristiques Techniques

* **Langage** : Java
* **Interface graphique** : JavaFX
* **Architecture MVC** :

  * Controllers dédiés à chaque vue
  * Séparation claire entre logique et interface
* **Gestion de projet** : Maven
* **Interface utilisateur** : Navigation par écrans (FXML)

## Comment Lancer le Projet

### Prérequis

* Java JDK 17 ou compatible
* Maven
* JavaFX configuré sur votre machine

### Lancement

1. Cloner le projet
2. Ouvrir le dossier `GestionBancaire` dans votre IDE
3. Lancer l’application via la classe principale JavaFX

Ou via Maven :

```bash
./mvnw javafx:run
```

## Démonstration

### Vidéo de démonstration (cmd + click)

<a href="https://rajbhanif.github.io/Gestion-bancaire/gestion-bancaire.mp4" target="_blank">▶ Cliquez ici pour ouvrir la vidéo de démo</a>

*La vidéo montre la navigation complète : connexion → gestion clients → comptes → opérations.*

## Projet Académique

Ce projet a été réalisé dans un cadre académique afin de pratiquer :

* Java orienté objet
* JavaFX
* Architecture MVC
* Gestion d’un système applicatif structuré

Le code est fourni à des fins éducatives.

## Auteur

**Raj Beghum Hanif**
Projet académique – Gestion Bancaire (Java / JavaFX)
ss
