package game.gameobjects.buildings.townBuilding;

import game.gameobjects.Building;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Church extends Building {
    public Church()
    {
        super("building-home-3");
        setGameObjectHelper(new GameObjectHelper( "building-home-5" , getObjectId() , GameobjectType.APOTHECARY ));
        HBox toolbar = new HBox();
        Label label = new Label("Church");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }
}
