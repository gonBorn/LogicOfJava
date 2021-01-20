package designPattern.observerParttern;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicInteger;

public class Player extends Observable {
    public static final int LEVEL_EXPERIENCE = 100;

    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    private int playerId;

    private long experience;

    private int level;

    public Player() {
        this.playerId = idGenerator.getAndIncrement();
        this.experience = 0L;
        this.level = 0;
    }

    public void beatMonster(Monster monster) {
        this.experience += monster.getExperience();
        calculateLevel();
    }

    private void calculateLevel() {
        while (this.experience >= LEVEL_EXPERIENCE) {
            this.experience = experience - LEVEL_EXPERIENCE;
            level++;
            setChanged();
            notifyObservers();
        }
    }

    public int getPlayerId() {
        return playerId;
    }

    public long getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }
}
