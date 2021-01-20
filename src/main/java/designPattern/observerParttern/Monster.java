package designPattern.observerParttern;

import java.util.Random;

public class Monster {
    private long experience;

    public Monster(long experience) {
        this.experience = experience;
    }

    public Monster() {
        Random random = new Random();
        this.experience = random.nextInt(100);
    }

    public long getExperience() {
        return experience;
    }
}
