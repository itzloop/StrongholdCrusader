package game.gameobjects.buildings.industry;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StoneQuarry extends Building {

    public StoneQuarry(Vector2D location){
        super("building-industry-3" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Stone Quarry");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }
}
