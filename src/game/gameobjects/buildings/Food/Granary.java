package game.gameobjects.buildings.Food;

import game.AssetManager;
import game.GV;
import game.gameobjects.Building;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.castle.ResourceName;
import game.gameobjects.buildings.castle.ResourceType;
import game.map.Vector2D;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Granary extends Building {

    private static ImageView img1;
    private static ImageView img2;
    private static ImageView img3;
    private static ImageView img4;
    private static ImageView img5;
    private static ImageView img6;
    private static ImageView img7;
    private static ImageView img8;

    public Granary(Vector2D location)
    {
        super("building-food-1" , location);
        HBox toolbar = new HBox();
        Castle.getMaxGranaryCapacity().getAndAdd(GV.GranaryCapacity);
        toolbar.getChildren().clear();
        img1 = new ImageView(AssetManager.images.get("meat"));
        img2 = new ImageView(AssetManager.images.get("cheese"));
        img3 = new ImageView(AssetManager.images.get("apple"));
        img4 = new ImageView(AssetManager.images.get("hop"));
        img5 = new ImageView(AssetManager.images.get("brew"));
        img6 = new ImageView(AssetManager.images.get("wheat"));
        img7 = new ImageView(AssetManager.images.get("flour"));
        img8 = new ImageView(AssetManager.images.get("bread"));

        toolbar.getChildren().addAll(img1 , img2 , img3 , img4 , img5 , img6 , img7 , img8 );
        toolbar.setSpacing(30);
        toolbar.setLayoutX(10);
        toolbar.setLayoutY(20);
        super.setToolbar(toolbar);
    }


    public static void update(){
        Tooltip.install(img1 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.MEAT)));
        Tooltip.install(img2 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.CHEESE)));
        Tooltip.install(img3 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.APPLE)));
        Tooltip.install(img4 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.HOP)));
        Tooltip.install(img5 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.BREW)));
        Tooltip.install(img6 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.WHEAT)));
        Tooltip.install(img7 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.FLOUR)));
        Tooltip.install(img8 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.BREAD)));
    }
}
