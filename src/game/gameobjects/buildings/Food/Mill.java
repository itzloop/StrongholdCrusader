package game.gameobjects.buildings.Food;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class Mill extends Building {


    public Mill(Vector2D location)
    {
        super("building-food-4" , location);
        HBox toolbar = new HBox();
        //TODO fix this latter


        super.setToolbar(toolbar);

    }
}
