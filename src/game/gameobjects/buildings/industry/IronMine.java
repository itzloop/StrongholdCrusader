package game.gameobjects.buildings.industry;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class IronMine extends Building {

    public IronMine(Vector2D location){
        super("building-industry-5" , location);
        HBox toolbar = new HBox();
        //TODO fix this later


        super.setToolbar(toolbar);
    }
}
