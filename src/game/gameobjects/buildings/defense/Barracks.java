package game.gameobjects.buildings.defense;

import game.gameobjects.Building;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import game.map.Vector2D;
import javafx.scene.layout.HBox;
import java.util.concurrent.atomic.AtomicInteger;

public class Barracks extends Building {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Barracks()
    {
        super("building-defense-5" );
        setGameObjectHelper(new GameObjectHelper( "building-defense-5" , getObjectId() , GameobjectType.BARRACKS ));

        count.getAndIncrement();
        HBox toolbar = new HBox();
        //TODO Fix the toobar

        super.setToolbar(toolbar);

    }

    public static Barracks createBrracks()
    {
        if(count.get() < 1)
            return new Barracks();
        else return null;
    }





}
