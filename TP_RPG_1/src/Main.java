import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static Battler currentPlayer;
    static Battler currentTarget;

    // Player initialization
    public static void initData(List<Battler> perso) {
        Battler player = new Battler();
        player.name = "robert";
        player.power(50, 100);
        player.hp(80, 500);
        player.GS(80, 100);
        perso.add(player);

        Battler enemy = new Battler();
        enemy.name = "typhon";
        enemy.power(50, 100);
        enemy.hp(80, 500);
        enemy.GS(80, 100);
        perso.add(enemy);

        System.out.println("=============================================================================================");
        System.out.println("Le joueur s'appelle " + player.name +", il a " + player.hp + " HP, " + player.power + " de puissance, " + player.speed + " de vitesse.");
        System.out.println("L' enemy s'appelle " + enemy.name +", il a " + enemy.hp + " HP, " + enemy.power + " de puissance, " + enemy.speed + " de vitesse.");
        System.out.println("=============================================================================================");
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
    public static void displayWinner(List<Team> teams) {
        System.out.println("Le vainqueur est : " + currentPlayer.name + " de l'équipe " + getTeamName(teams, currentPlayer));
    }


    public static String getTeamName(List<Team> teams, Battler player) {
        for (Team team : teams) {
            for (Battler battler : team.battlers) {
                if (battler == player) {
                    return team.name;
                }
            }
        }
        return "Aucune équipe";
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

    //create team
    public static List<Team> createTeams(List<Battler> battlers) {
        // Initialization name
        Team team1 = new Team();
        team1.name = "white";
        Team team2 = new Team();
        team2.name = "black";

        // add player in team
        team1.addBattler(battlers.get(0));
        team2.addBattler(battlers.get(1));

        //print team
        System.out.println("Équipe 1 : " + team1.name);
        System.out.println("Équipe 2 : " + team2.name);

        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        return teams;
    }

    public static void main(String[] args) {
        // Initialization
        List<Battler> perso = new ArrayList<>();
        initData(perso);

        // Initialization teams
        // Create teams
        List<Team> teams = createTeams(perso);
        System.out.println("=============================================");
        System.out.println("Les équipes sont préte :");

        System.out.println(" ");
        System.out.println("les équipes sont :");
        for (Team team : teams) {
            team.displayTeamInfo();
            System.out.println("=============================================");
        }

        // Determine who starts
        firstPlayer(perso);

        // Game loop
        while (true) {
            // Current player attacks
            System.out.println(currentPlayer.name + " attaque !");
            currentTarget.hp -= currentPlayer.power; // Réduction des HP
            System.out.println(currentTarget.name + " a maintenant " + currentTarget.hp + " HP.");

            // Check if the game is finished
            if (testWinner()) {
                displayWinner(teams);
                break;
            }

            // Move on to the next player
            newPlayer(perso);
        }
    }
}

