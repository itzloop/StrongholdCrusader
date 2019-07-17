package game.gameobjects.buildings.military;

import game.AssetManager;
import game.gameobjects.Building;
import game.gameobjects.GameObject;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.castle.ResourceName;
import game.gameobjects.buildings.industry.IronMine;
import game.map.Map;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ArmourersWorkshop extends Building {


    //gold
    private final static int price = 10;
    //per second
    private final static int productionRate = 1;



    private ArmourersWorkshop(Vector2D location)
    {
        super("building-military-5" , location);
        HBox toolbar = new HBox();
        Label label = new Label("Armourers Workshop");
        label.setStyle("-fx-font-size: 50");
        toolbar.getChildren().add(label);
        toolbar.setAlignment(Pos.CENTER);


        super.setToolbar(toolbar);
    }


    public void produce()
    {
        if(Castle.resourceAmount(ResourceName.IRON) - productionRate >= 0)
        {

            if(Castle.resourceAmount(ResourceName.METAL_ARMOR) + productionRate <= Castle.getMaxArmoryCapacity().get())
            {
                Castle.setResouce(ResourceName.IRON , -productionRate);
                Castle.getCurrentStockPileCapacity().getAndAdd(-productionRate);
                Castle.setResouce(ResourceName.METAL_ARMOR , +productionRate);
                Castle.getCurrentArmoryCapacity().getAndAdd(productionRate);
            }
            else
                Map.playSound(AssetManager.sounds.get("full-armory"));

        }
        else
            Map.playSound(AssetManager.sounds.get("need-iron"));

    }


    public static GameObject create(Vector2D location)
    {
        if(Castle.getGold().get() - price >= 0 ) {
            Castle.getGold().getAndAdd(-price);
            return new ArmourersWorkshop(location);
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
