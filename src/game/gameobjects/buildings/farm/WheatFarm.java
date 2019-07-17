package game.gameobjects.buildings.farm;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class WheatFarm extends Building {

    public WheatFarm(Vector2D location)
    {
        super("building-farm-4" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Wheat Farm");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);

        super.setToolbar(toolbar);
    }

}
