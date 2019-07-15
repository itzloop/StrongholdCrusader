package game.gameobjects.buildings.defense;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Armory extends Building {

    public Armory(Vector2D location ){
        super("building-defense-7" , location );
        HBox toolbar = new HBox();
        //TODO Fix the toobar

        super.setToolbar(toolbar);
    }



}
