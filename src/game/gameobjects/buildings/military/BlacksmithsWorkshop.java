package game.gameobjects.buildings.military;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class BlacksmithsWorkshop extends Building {

    public BlacksmithsWorkshop(Vector2D location)
    {
        super("building-military-3" , location);
        HBox toolbar = new HBox();
        //TODO fix this later


        super.setToolbar(toolbar);
    }
}
