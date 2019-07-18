package game.gameobjects.buildings.military;

import game.AssetManager;
import game.gameobjects.Building;
import game.gameobjects.GameObject;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.castle.ResourceName;
import game.map.Map;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BlacksmithsWorkshop extends Building {

        //gold
    private final static int price = 10;
    //per second
    private final static int productionRate = 1;



    private BlacksmithsWorkshop()
    {
        super("building-military-3");
        setGameObjectHelper(new GameObjectHelper( "building-military-3" , getObjectId() , GameobjectType.BLACKSMITHS_WORKSHOP ));
        HBox toolbar = new HBox();
        Label label = new Label("Blacksmiths Workshop");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);

        super.setToolbar(toolbar);
    }


    public void produce()
    {
        if(Castle.resourceAmount(ResourceName.IRON) - productionRate >= 0)
        {

            if(Castle.resourceAmount(ResourceName.SWORD) + productionRate <= Castle.getMaxArmoryCapacity().get())
            {
                Castle.setResouce(ResourceName.IRON , -productionRate);
                Castle.getCurrentStockPileCapacity().getAndAdd(-productionRate);
                Castle.setResouce(ResourceName.SWORD , +productionRate);
                Castle.getCurrentArmoryCapacity().getAndAdd(productionRate);
            }
            else
                Map.playSound(AssetManager.sounds.get("full-armory"));

        }
        else
            Map.playSound(AssetManager.sounds.get("need-iron"));

    }


    public static GameObject create()
    {
        if(Castle.getGold().get() - price >= 0 ) {
            Castle.getGold().getAndAdd(-price);
            return new BlacksmithsWorkshop();
        }
        else {
            Map.playSound(AssetManager.sounds.get("need-gold"));
            return null;
        }
    }


    public static int getPrice() {
        return price;
    }
}


