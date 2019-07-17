package game.gameobjects.buildings.military;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PoleturnersWorkshop extends Building {

    public PoleturnersWorkshop(Vector2D location)
    {
        super("building-military-2" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Poleturners Workshop");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }
}
