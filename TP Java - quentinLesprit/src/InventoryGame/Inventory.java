package InventoryGame;

// Étape 2
import java.util.ArrayList;
import java.util.List;

// Création de la classe inventaire
public class Inventory {

    //Initialisation de la liste d'objets de type item
    private List<Item> items; // Liste d'objets de type Item

    // Initialiser chaque Items comme une liste
    public Inventory() {
        this.items = new ArrayList<>();
    }

    // Méthode pour ajouter un objet à l'inventaire
    public void addItem(String name, int quantity) {

        // Vérification de si l'objet existe déjà
        for (Item item : items) {
            if (item.getName().equals(name)) {
                // SI il existe déjà rajouter la quantiter
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new Item(name, quantity));
        // System.out.println("ajouter");
    }

    // Méthode pour afficher tous les objets dans l'inventaire
    public void displayItems() {
        // Tester si la list est vide
        if (items.isEmpty()) {
            //Afficher que la liste est vide
            System.out.println("L'inventaire est vide.");
        } else {
            //Boucle pour afficher tous les Items de la liste
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    // Méthode pour supprimer un objet de l'inventaire
    public void removeItem(String name) {
        // Boucle de l'inventaire
        for (Item item : items) {
            // comparaison de l'item
            if (item.getName().equals(name)) {
                items.remove(item);
                System.out.println("L'objet " + name + " a été supprimé de l'inventaire.");
                return;
            }
        }
        // Si l'item n'est pas trouvé
        System.out.println("L'objet " + name + " n'a pas été trouvé dans l'inventaire.");
    }

    // Méthode pour supprimer un objet de l'inventaire en fonction de la quantité
    public void removeItem(String name, int quantity) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                if (item.getQuantity() == quantity) {
                    // Si la quantité correspond à la quantité posée, l'objet est supprimé
                    items.remove(item);
                    System.out.println("L'objet " + name + " a été supprimé de l'inventaire.");
                } else if (item.getQuantity() > quantity) {
                    // Si la quantité à supprimer est inférieure à la quantité stockée, supprimée
                    item.setQuantity(item.getQuantity() - quantity);
                    System.out.println( quantity + " " + name + " on été retirée.");
                } else {
                    // Si la quantité à supprimer est supérieure à la quantité stockée, impossible de supprimer
                    System.out.println("Impossible de retirer " + quantity + " de " + name + ": quantité insuffisante.");
                }
                return;
            }
        }
        System.out.println("L'objet " + name + " n'éxiste pas");
    }

    // Méthode pour obtenir l'inventaire dans l'interface
    public List<Item> getItems() {
        return items;
    }
}


