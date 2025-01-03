package fr.game;

import fr.game.inventory.Inventory;
import fr.game.inventory.Equipment;

import java.util.ArrayList;
import java.util.List;

public class Character {

    /**
     * Pseudo du personnage
     */
    public String pseudo;

    /**
     * inventaire du personnage
     */
    public Inventory inventory;

    /**
     * Liste des equipements afficher
     */
    public List<Equipment> equipments = new ArrayList<>();

    void equipItem(Equipment equipment) {
        equipments.add(equipment);

        System.out.println("A equipé l'item : " + equipment.name);

    }

}
