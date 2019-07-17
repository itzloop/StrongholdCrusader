package game.gameobjects.buildings.Food;

import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Bakery extends Building {

    public Bakery(Vector2D location)
    {
        super("building-food-2" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Bakery");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);

    }
}
