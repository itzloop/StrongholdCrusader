package game.gameobjects.buildings.industry;

import game.AssetManager;
import game.gameobjects.Building;
import game.gameobjects.GameObject;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.castle.ResourceName;
import game.map.Map;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StoneQuarry extends Building {



    //wood
    private final static int price = 10;
    //per second
    private final static int productionRate = 10;



    private StoneQuarry(Vector2D location){
        super("building-industry-3" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Stone Quarry");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }


    public void produce()
    {
        if(Castle.getCurrentStockPileCapacity().get() + productionRate <= Castle.getMaxStockPileCapacity().get())
        {
            Castle.setResouce(ResourceName.STONE , productionRate);
            Castle.getCurrentStockPileCapacity().addAndGet(productionRate);
        }
        else
            Map.playSound(AssetManager.sounds.get("full-stockpile"));
    }


    public static GameObject create(Vector2D location)
    {
        if(Castle.resourceAmount(ResourceName.WOOD) - price >= 0 ) {
            Castle.setResouce(ResourceName.WOOD , -price);
            return new StockPile(location);
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
