import java.util.ArrayList;
import java.util.List;

public class Main {

    static Battler currentPlayer;
    static Battler currentTarget;

    // Player initialization
    public static void initData(List<Battler> speed) {
        Battler player = new Battler();
        player.name = "robert";
        player.power(50, 100);
        player.hp(80, 500);
        player.GS(80, 100);
        speed.add(player);

        Battler enemy = new Battler();
        enemy.name = "typhon";
        enemy.power(50, 100);
        enemy.hp(80, 500);
        enemy.GS(80, 100);
        speed.add(enemy);

        System.out.println("Le joueur a " + player.hp + " HP.");
        System.out.println("Le joueur s'appelle " + player.name);
        System.out.println("Le joueur a " + player.power + " de puissance.");
        System.out.println("Le joueur a " + player.speed + " de vitesse.");
        System.out.println("L'enemy a " + enemy.hp + " HP.");
        System.out.println("L'enemy s'appelle " + enemy.name);
        System.out.println("L'enemy a " + enemy.power + " de puissance.");
        System.out.println("L'enemy a " + enemy.speed + " de vitesse.");
    }

    //Who plays first
    public static void firstPlayer(List<Battler> speed) {
        Battler player = speed.get(0);
        Battler enemy = speed.get(1);

        if (player.speed >= enemy.speed) {
            currentPlayer = player;
            currentTarget = enemy;
            System.out.println("Le joueur joue en premier.");
        } else {
            currentPlayer = enemy;
            currentTarget = player;
            System.out.println("L'enemy joue en premier.");
        }
    }

    // Check if anyone won
    public static boolean testWinner() {
        if (currentTarget.hp <= 0) {
            System.out.println(currentTarget.name + " est vaincu !");
            return true;
        }
        return false;
    }

    // Diplay Winner
    public static void displayWinner() {
        System.out.println("Le vainqueur est : " + currentPlayer.name);
    }

    // Change player
    public static void newPlayer(List<Battler> speed) {
        if (currentPlayer == speed.get(0)) {
            currentPlayer = speed.get(1);
            currentTarget = speed.get(0);
        } else {
            currentPlayer = speed.get(0);
            currentTarget = speed.get(1);
        }
    }

    public static void main(String[] args) {
        // Initialization
        List<Battler> speed = new ArrayList<>();
        initData(speed);

        // Determine who starts
        firstPlayer(speed);

        // Game loop
        while (true) {
            // Current player attacks
            System.out.println(currentPlayer.name + " attaque !");
            currentTarget.hp -= currentPlayer.power; // Réduction des HP
            System.out.println(currentTarget.name + " a maintenant " + currentTarget.hp + " HP.");

            // Check if the game is finished
            if (testWinner()) {
                displayWinner();
                break;
            }

            // Move on to the next player
            newPlayer(speed);
        }
    }
}

