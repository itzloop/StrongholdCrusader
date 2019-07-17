package game.gameobjects.buildings.military;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ArmourersWorkshop extends Building {

    public ArmourersWorkshop(Vector2D location)
    {
        super("building-military-5" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Armourers Workshop");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }
}
