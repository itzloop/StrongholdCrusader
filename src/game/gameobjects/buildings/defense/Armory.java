package game.gameobjects.buildings.defense;

import game.AssetManager;
import game.GV;
import game.gameobjects.Building;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Armory extends Building {

    private static ImageView img1;
    private static ImageView img2;
    private static ImageView img3;
    private static ImageView img4;
    private static ImageView img5;

    public Armory(){
        super("building-defense-7"  );
        setGameObjectHelper(new GameObjectHelper( "building-defense-7"  , getObjectId() , GameobjectType.ARMORY ));
        HBox toolbar = new HBox();
        Castle.getMaxArmoryCapacity().getAndAdd(GV.armoryCapacity);
        String[] keys = {
                "bow",
                "axe",
                "sword",
                "leather_armor",
                "metal_armor"
        };
        img1 = new ImageView(AssetManager.images.get("bow"));
        img2 = new ImageView(AssetManager.images.get("axe"));
        img3 = new ImageView(AssetManager.images.get("sword"));
        img4 = new ImageView(AssetManager.images.get("leather_armor"));
        img5 = new ImageView(AssetManager.images.get("metal_armor"));


        toolbar.getChildren().addAll(img1 , img2 , img3 , img4 , img5);
        toolbar.setSpacing(30);
        toolbar.setLayoutX(100);
        toolbar.setLayoutY(20);

        super.setToolbar(toolbar);
    }

    public static void update()
    {
        Tooltip.install(img1 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.BOW)));
        Tooltip.install(img2 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.AXE)));
        Tooltip.install(img3 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.SWORD)));
        Tooltip.install(img4 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.LEATHER_ARMOR)));
        Tooltip.install(img5 , new Tooltip("amount: " + Castle.resourceAmount(ResourceName.METAL_ARMOR)));
    }



}
