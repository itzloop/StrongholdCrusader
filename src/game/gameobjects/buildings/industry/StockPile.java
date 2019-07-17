package game.gameobjects.buildings.industry;

import game.AssetManager;
import game.GV;
import game.gameobjects.Building;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.castle.ResourceName;
import game.gameobjects.buildings.castle.ResourceType;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class StockPile extends Building {
    private static ImageView img1 ;
    private static ImageView img2 ;
    private static ImageView img3 ;

    public StockPile(Vector2D location){
        super("building-industry-1" , location);
        HBox toolbar = new HBox();
        Castle.getMaxStockPileCapacity().getAndAdd(GV.stockPileCapacity);
        img1 = new ImageView(AssetManager.images.get("wood"));
        img2 = new ImageView(AssetManager.images.get("stone"));
        img3 = new ImageView(AssetManager.images.get("iron"));
        toolbar.getChildren().addAll(img1,img2 , img3);
        toolbar.setSpacing(30);
        toolbar.setLayoutX(100);
        toolbar.setLayoutY(20);
        toolbar.setAlignment(Pos.CENTER);

        super.setToolbar(toolbar);

        }

        public static void update() {
            Tooltip.install(img1 , new Tooltip("amount: " +Castle.resourceAmount(ResourceName.WOOD)));
            Tooltip.install(img2 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.STONE)));
            Tooltip.install(img3 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.IRON)));
        }



}
