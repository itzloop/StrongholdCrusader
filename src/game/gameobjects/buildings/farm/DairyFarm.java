package game.gameobjects.buildings.farm;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DairyFarm extends Building {
    public DairyFarm(Vector2D location)
    {
        super("building-farm-2" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Dairy Farm");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);

        super.setToolbar(toolbar);
    }
}
