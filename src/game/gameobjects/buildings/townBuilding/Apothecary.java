package game.gameobjects.buildings.townBuilding;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class Apothecary extends Building {
    public Apothecary(Vector2D location)
    {
        super("building-home-5" , location);
        HBox toolbar = new HBox();
        //TODO fix this later


        super.setToolbar(toolbar);
    }
}
