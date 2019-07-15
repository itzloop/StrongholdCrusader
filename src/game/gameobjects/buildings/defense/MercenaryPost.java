package game.gameobjects.buildings.defense;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

import java.util.concurrent.atomic.AtomicInteger;

public class MercenaryPost extends Building {

    private static final AtomicInteger count = new AtomicInteger(0);
    private MercenaryPost(Vector2D location)
    {
        super("building-defense-6" , location);
        count.getAndIncrement();
        HBox toolbar = new HBox();
        //TODO Fix the toobar

        super.setToolbar(toolbar);
    }

    public static MercenaryPost createMercenaryPost(Vector2D location)
    {
        if(count.get() < 1)
            return new MercenaryPost(location);
        else return null;
    }

}
