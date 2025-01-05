package InventoryGame;
import javax.swing.SwingUtilities;

// Étape 3
public class Main {
    public static void main(String[] args) {

        // Instanciez un objet Inventory
        Inventory inventory = new Inventory();

        // Ajouter des objets dans un inventaire avec une quantité
        inventory.addItem("potion", 10);
        inventory.addItem("grenade", 5);
        inventory.addItem("épée", 1);

        //boucle de test
        for (int i = 1; i <= 10; i++) {
            inventory.addItem("test "+i , 10);
        }

        // Affiché l'inventaire
        inventory.displayItems();

        // Delete un objet de l'inventaire
        inventory.removeItem("potion", 5);
        inventory.removeItem("grenade");

        // Affiché l'inventaire
        inventory.displayItems();

        //Ajout de nouvel potion
        inventory.addItem("potion", 10);

        // Affiché l'inventaire
        inventory.displayItems();

        SwingUtilities.invokeLater(() -> GameInventory.gameInventory(inventory));
    }
}
