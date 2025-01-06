package InventoryGame;
import javax.swing.SwingUtilities;
import javax.swing.*;

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
        for (int i = 1; i <= 200; i++) {
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

        // Demander à l'utilisateur de choisir la version via une boîte de dialogue
        String[] options = {"Version Simple", "Version Design"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choisissez la version de l'inventaire",
                "Choisir la version",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        SwingUtilities.invokeLater(() -> {
            if (choice == 0) {
                // vertion simple
                SwingUtilities.invokeLater(() -> GameInventory.gameInventory(inventory));
            } else {
                // vertion disign
                SwingUtilities.invokeLater(() -> GameInventorydisign.gameInventory2(inventory));
            }
        });
    }
}
