package InventoryGame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Ceci est ma vertion remixer avec le chat de gpt

public class GameInventorydisign {

    public static void gameInventory2(Inventory inventory) {
        // Initialisation de la fenêtre
        JFrame frame = new JFrame();
        frame.setSize(800, 600); // Taille modérée
        frame.setTitle("InventoryGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définir une police moderne pour l'interface
        Font font = new Font("Arial", Font.PLAIN, 14);

        // Barre de gestion
        JLabel nameItem = new JLabel("Objet:");
        nameItem.setFont(font);
        JLabel quantityItem = new JLabel("Quantité:");
        quantityItem.setFont(font);

        JTextField nameItem1 = new JTextField(10);
        nameItem1.setFont(font);
        nameItem1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        JTextField quantityItem1 = new JTextField(5);
        quantityItem1.setFont(font);
        quantityItem1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Ajout des boutons
        JButton addButton = new JButton("Ajouter");
        addButton.setFont(font);
        addButton.setBackground(new Color(44, 130, 201)); // Bleu
        addButton.setForeground(Color.WHITE);
        addButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        JButton removeButton = new JButton("Supprimer");
        removeButton.setFont(font);
        removeButton.setBackground(new Color(233, 67, 53)); // Rouge
        removeButton.setForeground(Color.WHITE);
        removeButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Panneau principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // Espacement entre les composants
        mainPanel.setBackground(Color.WHITE);

        // Panneau de gestion
        JPanel panelGestion = new JPanel();
        panelGestion.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); // Espacement entre les éléments
        panelGestion.setBackground(Color.WHITE);
        panelGestion.add(nameItem);
        panelGestion.add(nameItem1);
        panelGestion.add(quantityItem);
        panelGestion.add(quantityItem1);
        panelGestion.add(addButton);
        panelGestion.add(removeButton);
        mainPanel.add(panelGestion, BorderLayout.NORTH);

        // Liste des items dans l'inventaire
        JList<String> itemList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Refresh(listModel, inventory);
        itemList.setModel(listModel);
        itemList.setFont(font);
        itemList.setBackground(new Color(245, 245, 245));
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JScrollPane listScrollPane = new JScrollPane(itemList);
        listScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panneau pour afficher les items dans une grille
        JPanel panelGrid = new JPanel(new GridLayout(0, 6, 10, 10)); // Espacement dans la grille
        panelGrid.setBackground(Color.WHITE);

        // Fonction pour rafraîchir la grille
        Runnable updateGrid = () -> {
            panelGrid.removeAll();
            for (int i = 0; i < listModel.size(); i++) {
                String product = listModel.get(i);

                JButton clonedButton = Clone.cloneButton(addButton);
                clonedButton.setText(product);
                clonedButton.setBackground(new Color(211, 211, 211));
                clonedButton.setForeground(Color.BLACK);
                clonedButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
                panelGrid.add(clonedButton);
            }
            panelGrid.revalidate();
            panelGrid.repaint();
        };

        updateGrid.run();
        mainPanel.add(panelGrid, BorderLayout.CENTER);

        // Affichage
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(listScrollPane, BorderLayout.WEST); // Liste des items à gauche
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

