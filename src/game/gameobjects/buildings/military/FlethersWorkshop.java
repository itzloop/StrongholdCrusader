package game.gameobjects.buildings.military;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FlethersWorkshop extends Building {

    public FlethersWorkshop(Vector2D location)
    {
        super("building-military-1" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Flethers Workshop");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }
}
