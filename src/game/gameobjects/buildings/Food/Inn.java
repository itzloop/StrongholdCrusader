package game.gameobjects.buildings.Food;

import game.AssetManager;
import game.gameobjects.Building;
import game.gameobjects.GameObject;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.castle.ResourceName;
import game.gameobjects.buildings.farm.AppleFarm;
import game.map.Map;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Inn extends Building {


    private final static int price = 10;
    //per second
    private final static int productionRate = 10;


    private Inn()
    {
        super("building-food-5");
        setGameObjectHelper(new GameObjectHelper( "building-food-5" , getObjectId() , GameobjectType.INN ));
        HBox toolbar = new HBox();
        Label label = new Label("Inn");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);

    }


    public void produce()
    {
        Castle.setResouce(ResourceName.HOP , -productionRate);
    }

    public static GameObject create()
    {
        if(Castle.resourceAmount(ResourceName.WOOD) - price >= 0 ) {
            Castle.setResouce(ResourceName.WOOD , -price);
            return new Inn();
        }
        else {
            Map.playSound(AssetManager.sounds.get("need-wood"));
            return null;
        }
    }


    public static int getPrice() {
        return price;
    }
}
