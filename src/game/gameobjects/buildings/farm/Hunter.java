package game.gameobjects.buildings.farm;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class Hunter extends Building {

    public Hunter(Vector2D location)
    {
        super("building-farm-1" , location);
        HBox toolbar = new HBox();
        //TODO fix this later

        super.setToolbar(toolbar);
    }
}
