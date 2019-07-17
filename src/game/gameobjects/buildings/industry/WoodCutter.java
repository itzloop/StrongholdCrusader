package game.gameobjects.buildings.industry;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class WoodCutter extends Building {

    public WoodCutter(Vector2D location){
        super("building-industry-2" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Wood Cutter");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }
}

