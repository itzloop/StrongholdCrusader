package game.gameobjects.buildings.military;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class ArmourersWorkshop extends Building {

    public ArmourersWorkshop(Vector2D location)
    {
        super("building-military-5" , location);
        HBox toolbar = new HBox();
        //TODO fix this later


        super.setToolbar(toolbar);
    }
}
