package game.gameobjects.buildings.industry;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class StoneQuarry extends Building {

    public StoneQuarry(Vector2D location){
        super("building-industry-3" , location);
        HBox toolbar = new HBox();
        //TODO fix this later


        super.setToolbar(toolbar);
    }
}
