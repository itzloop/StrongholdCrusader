package game.gameobjects.buildings.defense;

import game.gameobjects.Building;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

import java.util.concurrent.atomic.AtomicInteger;

public class MercenaryPost extends Building {

    private static final AtomicInteger count = new AtomicInteger(0);
    private MercenaryPost()
    {
        super("building-defense-6");
        setGameObjectHelper(new GameObjectHelper( "building-defense-6" , getObjectId() , GameobjectType.MERCENARY_POST ));
        count.getAndIncrement();
        HBox toolbar = new HBox();
        //TODO Fix the toobar

        super.setToolbar(toolbar);
    }

    public static MercenaryPost createMercenaryPost()
    {
        if(count.get() < 1)
            return new MercenaryPost();
        else return null;
    }

}

