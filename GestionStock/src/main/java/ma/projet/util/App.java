package ma.projet.util;

import ma.projet.classes.*;
import ma.projet.service.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        CategorieService categorieService = new CategorieService();
        ProduitService produitService = new ProduitService();
        CommandeService commandeService = new CommandeService();
        LigneCommandeProduitService ligneService = new LigneCommandeProduitService();

        // Créer une catégorie
        Categorie c1 = new Categorie("CAT01", "Ordinateurs");
        categorieService.create(c1);

        // Créer des produits
        Produit p1 = new Produit("HP", 7000, c1);
        Produit p2 = new Produit("DELL", 6500, c1);
        produitService.create(p1);
        produitService.create(p2);

        // Créer une commande
        Commande cmd1 = new Commande(new Date());
        commandeService.create(cmd1);

        // Ajouter des lignes de commande
        LigneCommandeProduit l1 = new LigneCommandeProduit(2, p1, cmd1);
        LigneCommandeProduit l2 = new LigneCommandeProduit(1, p2, cmd1);
        ligneService.create(l1);
        ligneService.create(l2);

        //  Afficher la commande avec les produits
        System.out.println("\n Commande n°" + cmd1.getId() + " | Date : " + cmd1.getDate());
        System.out.println("Liste des produits :");
        System.out.println("Référence\tPrix\tQuantité");

        for (LigneCommandeProduit ligne : ligneService.getAll()) {
            if (ligne.getCommande().getId() == cmd1.getId()) {
                System.out.println(
                        ligne.getProduit().getReference() + "\t" +
                                ligne.getProduit().getPrix() + " DH\t" +
                                ligne.getQuantite()
                );
            }
        }

        System.out.println("\n Commande affichée avec succès !");
    }
}
