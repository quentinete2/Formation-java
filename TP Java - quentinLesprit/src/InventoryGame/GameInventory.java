package InventoryGame;

import javax.swing.*;
import java.awt.*;

// Partie 2
// Étape 1
public class GameInventory {

    public static void gameInventory(Inventory inventory) {
        // Initialisation de la fenètre
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setTitle("InventoryGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // création de la barre de gestion
        JLabel nameLabel = new JLabel("Item:");
        JTextField nameField = new JTextField(10);
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(5);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Delete");

        // Panneau de l'inventaire
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panneau de gestion
        JPanel inputPanel = new JPanel();

        // ajout de composant
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        refreshList(listModel, inventory); // Initialise la liste avec les éléments existants

        JList<String> itemList = new JList<>(listModel);
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(listPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);

        //Boutton ajouter
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String quantityText = quantityField.getText();
            if (!name.isEmpty() && !quantityText.isEmpty()) {
                try {
                    int quantity = Integer.parseInt(quantityText);
                    inventory.addItem(name, quantity);
                    refreshList(listModel, inventory);
                    nameField.setText("");
                    quantityField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer une quantité valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Boutton suprimé
        removeButton.addActionListener(e -> {
            String name = nameField.getText();
            String quantityText = quantityField.getText();
            if (!name.isEmpty() && !quantityText.isEmpty()) {
                int quantity = Integer.parseInt(quantityText);
                inventory.removeItem(name, quantity);
                refreshList(listModel, inventory);
                nameField.setText("");
                quantityField.setText("");
            }
            else if (!quantityText.isEmpty()) {
                int quantity = Integer.parseInt(quantityText);
                String selectedValue = itemList.getSelectedValue();
                // Extraire le nom de l'objet
                String itemName = selectedValue.split(" \\(x")[0];
                inventory.removeItem(itemName, quantity);
                refreshList(listModel, inventory);
                nameField.setText("");
                quantityField.setText("");
            }
            else if (!name.isEmpty()) {
                inventory.removeItem(name);
                refreshList(listModel, inventory);
                nameField.setText("");
                quantityField.setText("");
            }

            else {
                String selectedValue = itemList.getSelectedValue();
                // Extraire le nom de l'objet
                String itemName = selectedValue.split(" \\(x")[0];
                inventory.removeItem(itemName); // Supprimer de l'inventaire
                refreshList(listModel, inventory); // Rafraîchir la liste
            }
        });
    }

    // Rafraichissement de l'inventaire visible
    private static void refreshList(DefaultListModel<String> listModel, Inventory inventory) {
        listModel.clear();
        for (Item item : inventory.getItems()) {
            listModel.addElement(item.getName() + " (x" + item.getQuantity() + ")");
        }
    }
}
