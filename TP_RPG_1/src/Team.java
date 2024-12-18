import java.util.ArrayList;
import java.util.List;

public class Team {
    public String name;
    public List<Battler> battlers = new ArrayList<>();

    public void addBattler(Battler battler) {
        battlers.add(battler);
    }

    public void displayTeamInfo() {
        System.out.println("Équipe: " + name);
        for (Battler battler : battlers) {
            System.out.println("- " + battler.name + ": HP = " + battler.hp + ", Power = " + battler.power + ", Speed = " + battler.speed);
        }
    }

}