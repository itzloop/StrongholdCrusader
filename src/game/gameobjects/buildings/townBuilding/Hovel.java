package game.gameobjects.buildings.townBuilding;

import game.GV;
import game.gameobjects.Building;
import game.gameobjects.buildings.castle.Castle;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Hovel extends Building {

    public static  int rand= (int)(Math.random()*4 );

    public Hovel(Vector2D location)
    {
        super("building-home-1-" + rand, location);
        System.out.println(rand);
        Castle.getMaxPopulationSize().getAndAdd(GV.HovelCapacity);
        HBox toolbar = new HBox();
        Label label = new Label("Hovel");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);

        super.setToolbar(toolbar);
    }
}
