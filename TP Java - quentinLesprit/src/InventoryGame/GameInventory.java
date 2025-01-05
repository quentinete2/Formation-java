package InventoryGame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Partie 2
public class GameInventory {

    public static void gameInventory(Inventory inventory) {
        // Initialisation de la fenêtre
        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);
        frame.setTitle("InventoryGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création de la barre de gestion
        JLabel nameLabel = new JLabel("Item:");
        JTextField nameField = new JTextField(10);
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(5);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Delete");

        // Panneau principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panneau de gestion
        JPanel panelGestion = new JPanel();
        panelGestion.add(nameLabel);
        panelGestion.add(nameField);
        panelGestion.add(quantityLabel);
        panelGestion.add(quantityField);
        panelGestion.add(addButton);
        panelGestion.add(removeButton);
        mainPanel.add(panelGestion, BorderLayout.NORTH);

        // Création du panneau pour afficher l'inventaire
        JPanel ItemPanel = new JPanel();
        ItemPanel.setLayout(new GridLayout(0, 2));

        // Liste des items dans l'inventaire
        JList<String> itemList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Affichage des items dans l'inventaire
        Refresh(listModel, inventory);
        itemList.setModel(listModel);

        // Ajouter l'inventaire à un JScrollPane
        JScrollPane scrollPane = new JScrollPane(itemList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Affichage
        frame.add(mainPanel);
        frame.setVisible(true);

        // Bouton ajouter
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String quantityText = quantityField.getText();
            if (!name.isEmpty() && !quantityText.isEmpty()) {
                int quantity = Integer.parseInt(quantityText);
                inventory.addItem(name, quantity);
                Refresh(listModel, inventory);
                nameField.setText("");
                quantityField.setText("");
            }
        });

        // Boutton suprimé
        removeButton.addActionListener(e -> {
            String name = nameField.getText();
            String quantityText = quantityField.getText();
            if (!name.isEmpty() && !quantityText.isEmpty()) {
                int quantity = Integer.parseInt(quantityText);
                inventory.removeItem(name, quantity);
                Refresh(listModel, inventory);
                nameField.setText("");
                quantityField.setText("");
            }
            else if (!quantityText.isEmpty()) {
                int quantity = Integer.parseInt(quantityText);
                String selectedValue = itemList.getSelectedValue();
                // Extraire le nom de l'objet
                String itemName = selectedValue.split(" \\(x")[0];
                inventory.removeItem(itemName, quantity);
                Refresh(listModel, inventory);
                nameField.setText("");
                quantityField.setText("");
            }
            else if (!name.isEmpty()) {
                inventory.removeItem(name);
                Refresh(listModel, inventory);
                nameField.setText("");
                quantityField.setText("");
            }

            else {
                String selectedValue = itemList.getSelectedValue();
                // Extraire le nom de l'objet
                String itemName = selectedValue.split(" \\(x")[0];
                inventory.removeItem(itemName);
                Refresh(listModel, inventory);
            }
        });
    }

    private static void Refresh(DefaultListModel<String> listModel, Inventory inventory) {
        listModel.clear();
        List<Item> items = inventory.getItems();
        for (Item item : items) {
            listModel.addElement(item.getName() + " (x" + item.getQuantity() + ")");
        }
    }
}
