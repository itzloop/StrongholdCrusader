package game.gameobjects.buildings.townBuilding;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class Cathedral extends Building {
    public Cathedral(Vector2D location)
    {
        super("building-home-4" , location);
        HBox toolbar = new HBox();
        //TODO fix this later


        super.setToolbar(toolbar);
    }
}
