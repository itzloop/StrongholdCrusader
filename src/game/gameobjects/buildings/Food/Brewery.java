package game.gameobjects.buildings.Food;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class Brewery extends Building {


    public Brewery(Vector2D location)
    {
        super("building-food-3" , location);
        HBox toolbar = new HBox();
        //TODO fix this latter


        super.setToolbar(toolbar);

    }
}
