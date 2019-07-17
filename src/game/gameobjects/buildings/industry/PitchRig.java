package game.gameobjects.buildings.industry;

import game.AssetManager;
import game.gameobjects.Building;
import game.gameobjects.GameObject;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.castle.ResourceName;
import game.map.Map;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PitchRig extends Building {



    public PitchRig(Vector2D location){
        super("building-industry-6" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Pitch Rig");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }



}
