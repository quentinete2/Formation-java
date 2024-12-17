import java.util.Random;

public class Battler {
    public String name;
    public int hp;
    public int power;
    public int speed;

    public void power(int min, int max) {
        Random random = new Random();
        this.power = random.nextInt((max - min) + 1) + min;
    }
    public void hp(int min, int max) {
        Random random = new Random();
        this.hp = random.nextInt((max - min) + 1) + min;
    }
    public void GS(int min, int max) {
        Random random = new Random();
        this.speed = random.nextInt((max - min) + 1) + min;
    }
}
