package game.gameobjects.buildings.defense;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;
import java.util.concurrent.atomic.AtomicInteger;

public class Barracks extends Building {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Barracks(Vector2D location  )
    {
        super("building-defense-5" ,location );
        count.getAndIncrement();
        HBox toolbar = new HBox();
        //TODO Fix the toobar

        super.setToolbar(toolbar);

    }

    public static Barracks createBrracks(Vector2D location)
    {
        if(count.get() < 1)
            return new Barracks(location);
        else return null;
    }





}
