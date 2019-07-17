package game.gameobjects.buildings.Food;

import game.AssetManager;
import game.gameobjects.Building;
import game.gameobjects.GameObject;
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


    private Inn(Vector2D location)
    {
        super("building-food-5" , location);
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

    public static GameObject create(Vector2D location)
    {
        if(Castle.resourceAmount(ResourceName.WOOD) - price >= 0 ) {
            Castle.setResouce(ResourceName.WOOD , -price);
            return new Inn(location);
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
