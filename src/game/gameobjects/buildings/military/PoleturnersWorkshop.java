package game.gameobjects.buildings.military;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class PoleturnersWorkshop extends Building {

    public PoleturnersWorkshop(Vector2D location)
    {
        super("building-military-2" , location);
        HBox toolbar = new HBox();
        //TODO fix this later


        super.setToolbar(toolbar);
    }
}
