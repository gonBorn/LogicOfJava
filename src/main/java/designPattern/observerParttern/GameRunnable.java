package designPattern.observerParttern;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameRunnable implements Runnable{
    private AtomicBoolean isGameShutDown = new AtomicBoolean(false);

    @Override
    public void run() {
        Player player = new Player();
        DisplayMachine displayMachine = new DisplayMachine(player);
        while (!isGameShutDown.get() && player.getLevel() < 10) {
            player.beatMonster(new Monster());
            System.out.println(String.format(Locale.ENGLISH, "Player %d has %d experience now.", player.getPlayerId(), player.getExperience()));
        }
        System.out.println(String.format(Locale.ENGLISH, "Game end, player %d promoted at level %d.", player.getPlayerId(), player.getLevel()));
    }

    public AtomicBoolean getIsGameShutDown() {
        return isGameShutDown;
    }

    public void setIsGameShutDown(AtomicBoolean isGameShutDown) {
        this.isGameShutDown = isGameShutDown;
    }
}
