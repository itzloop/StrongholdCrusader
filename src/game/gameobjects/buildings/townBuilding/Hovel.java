package game.gameobjects.buildings.townBuilding;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class Hovel extends Building {

    public static  int rand= (int)(Math.random()*4 );

    public Hovel(Vector2D location)
    {
        super("building-home-1-" + rand, location);
        System.out.println(rand);
        HBox toolbar = new HBox();
        //TODO fix this later


        super.setToolbar(toolbar);
    }
}
