package game.gameobjects.buildings.military;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class FlethersWorkshop extends Building {

    public FlethersWorkshop(Vector2D location)
    {
        super("building-military-1" , location);
        HBox toolbar = new HBox();
        //TODO fix this later


        super.setToolbar(toolbar);
    }
}
