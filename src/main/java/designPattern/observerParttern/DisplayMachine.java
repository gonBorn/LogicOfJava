package designPattern.observerParttern;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class DisplayMachine implements Observer {
    private Player player;

    public DisplayMachine(Player player) {
        this.player = player;
        player.addObserver(this);
    }

    // 可以由观察者拉数据，即arg
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Player) {
            display((Player)o);
        }
    }

    private void display(Player o) {
        System.out.println(String.format(Locale.ENGLISH, "Congratulations on player %d's promotion to level %d.", o.getPlayerId(), o.getLevel()));
    }
}
