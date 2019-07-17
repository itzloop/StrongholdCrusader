package game.gameobjects.buildings.townBuilding;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Apothecary extends Building {
    public Apothecary(Vector2D location)
    {
        super("building-home-5" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Apothecary");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }
}
