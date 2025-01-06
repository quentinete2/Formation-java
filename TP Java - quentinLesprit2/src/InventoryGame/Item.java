package InventoryGame;

// Étape 1
public class Item {
    // Définition des deux attributs
    private String name;
    private int quantity;

    // Ajouts du constructeur
    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }


    // Méthode d'accès (getters)
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }

    // Méthode modification (setters)
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Méthode toString
    public String toString() {
        return "name = " + name + "| quantity = " + quantity;
    }
}





