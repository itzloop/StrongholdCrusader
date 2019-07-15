package game.gameobjects.buildings.Food;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class Bakery extends Building {

    public Bakery(Vector2D location)
    {
        super("building-food-2" , location);
        HBox toolbar = new HBox();
        //TODO fix this latter


        super.setToolbar(toolbar);

    }
}
