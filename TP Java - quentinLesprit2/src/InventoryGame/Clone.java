package InventoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clone {
    // Méthode utilitaire pour cloner un bouton
    public static JButton cloneButton(JButton originalButton) {
        JButton newButton = new JButton(originalButton.getText());
        newButton.setIcon(originalButton.getIcon());
        newButton.setEnabled(originalButton.isEnabled());
        newButton.setToolTipText(originalButton.getToolTipText());
        newButton.setFont(originalButton.getFont());
        newButton.setBackground(originalButton.getBackground());
        newButton.setForeground(originalButton.getForeground());
        newButton.setBounds(originalButton.getBounds());

        // Copier les ActionListeners
        for (ActionListener al : originalButton.getActionListeners()) {
            newButton.addActionListener(al);
        }

        return newButton;
    }
}
