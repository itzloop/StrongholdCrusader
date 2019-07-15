package game.gameobjects.buildings.farm;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

public class WheatFarm extends Building {

    public WheatFarm(Vector2D location)
    {
        super("building-farm-4" , location);
        HBox toolbar = new HBox();
        //TODO fix this later

        super.setToolbar(toolbar);
    }

}
