package InventoryGame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameInventory {

    public static void gameInventory(Inventory inventory) {
        // Initialisation de la fenêtre
        JFrame frame = new JFrame();
        frame.setSize(800, 600); // Taille plus modérée
        frame.setTitle("InventoryGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Barre de gestion
        JLabel nameItem = new JLabel("Objet:");
        JLabel quantityItem = new JLabel("Quantité:");

        JTextField nameItem1 = new JTextField(10);
        JTextField quantityItem1 = new JTextField(5);

        // Ajout des boutons
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Delete");

        // Panneau principal
        JPanel mainPanel = new JPanel(
                new BorderLayout()
        );

        // Panneau de gestion
        JPanel panelGestion = new JPanel();
        panelGestion.add(nameItem);
        panelGestion.add(nameItem1);
        panelGestion.add(quantityItem);
        panelGestion.add(quantityItem1);
        panelGestion.add(addButton);
        panelGestion.add(removeButton);
        mainPanel.add(panelGestion, BorderLayout.NORTH);

        //Création d'un bouton clone
        JButton originalButton = new JButton("Original");
        originalButton.setBounds(50, 50, 150, 30);
        originalButton.addActionListener(e -> System.out.println("Action sur le bouton original"));

        // Liste des items dans l'inventaire
        JList<String> itemList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Refresh(listModel, inventory);
        itemList.setModel(listModel);

        JPanel panelGrid = new JPanel(new GridLayout(0, 10));

        // Fonction pour rafraîchir la grille
        Runnable updateGrid = () -> {
            panelGrid.removeAll();
            for (int i = 0; i < listModel.size(); i++) {
                String product = listModel.get(i);

                JButton clonedButton = Clone.cloneButton(originalButton);
                clonedButton.setText(product);
                panelGrid.add(clonedButton);
            }
            panelGrid.revalidate();
            panelGrid.repaint();
        };

        updateGrid.run();
        mainPanel.add(panelGrid, BorderLayout.CENTER);

        // Affichage
        frame.add(mainPanel);
        frame.setVisible(true);

        // Bouton ajouter
        addButton.addActionListener(e -> {
            String name = nameItem1.getText().trim();
            String quantityText = quantityItem1.getText().trim();
            try {
                if (!name.isEmpty() && !quantityText.isEmpty()) {
                    int quantity = Integer.parseInt(quantityText);
                    inventory.addItem(name, quantity);
                    Refresh(listModel, inventory);
                    updateGrid.run();
                    nameItem1.setText("");
                    quantityItem1.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Bouton supprimer
        removeButton.addActionListener(e -> {
            String name = nameItem1.getText().trim();
            String quantityText = quantityItem1.getText().trim();
            String selectedValue = itemList.getSelectedValue();

            try {
                if (!name.isEmpty() && !quantityText.isEmpty()) {
                    int quantity = Integer.parseInt(quantityText);
                    inventory.removeItem(name, quantity);
                } else if (selectedValue != null && !quantityText.isEmpty()) {
                    int quantity = Integer.parseInt(quantityText);
                    String itemName = selectedValue.split(" \\(x")[0];
                    inventory.removeItem(itemName, quantity);
                } else if (!name.isEmpty()) {
                    inventory.removeItem(name);
                } else if (selectedValue != null) {
                    String itemName = selectedValue.split(" \\(x")[0];
                    inventory.removeItem(itemName);
                }
                Refresh(listModel, inventory);
                updateGrid.run();
                nameItem1.setText("");
                quantityItem1.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for quantity.", "Error", JOptionPane.ERROR_MESSAGE);
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
