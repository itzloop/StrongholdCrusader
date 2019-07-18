package game.gameobjects.buildings.military;

import game.gameobjects.Building;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TannerWorkshop extends Building {

    public TannerWorkshop()
    {
        super("building-military-4" );
        setGameObjectHelper(new GameObjectHelper( "building-military-4" , getObjectId() , GameobjectType.TANNER_WORKSHOP ));
        HBox toolbar = new HBox();
        Label label = new Label("Tanner Workshop");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }
}
