package game.gameobjects.buildings.industry;

import game.AssetManager;
import game.gameobjects.Building;
import game.gameobjects.GameObject;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.castle.ResourceName;
import game.gameobjects.buildings.castle.ResourceType;
import game.map.Vector2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MarketPlace extends Building {

    final static AtomicInteger count = new AtomicInteger(0);

    Map<String , Integer> prices;

    private MarketPlace(){
        super("building-industry-7");
        setGameObjectHelper(new GameObjectHelper( "building-industry-7" , getObjectId() , GameobjectType.MARKETPLACE ));
        count.incrementAndGet();
        prices = new HashMap<>();
        prices.put("meat" , 100);
        prices.put("cheese" , 50);
        prices.put("bread" , 15);
        prices.put("apple" , 10);
        prices.put("hop" , 10);
        prices.put("brew" , 50);
        prices.put("wheat" , 20);
        prices.put("flour" , 60);
        prices.put("wood" ,10);
        prices.put("stone" , 20);
        prices.put("iron" , 70);
        prices.put("bow" , 50);
        prices.put("axe" , 100);
        prices.put("sword" , 200);
        prices.put("leather_armor" , 210);
        prices.put("metal_armor" , 250);

        ImageView imgFood = new ImageView(AssetManager.images.get("market-food"));
        ImageView imgResources= new ImageView(AssetManager.images.get("market-resource"));
        ImageView imgArmor = new ImageView(AssetManager.images.get("market-armor"));
        HBox toolbar = new HBox();

        toolbar.getChildren().addAll(imgFood , imgResources , imgArmor);
        toolbar.setSpacing(30);
        toolbar.setLayoutX(100);
        toolbar.setLayoutY(20);


        //events
        addEventHandler(MouseEvent.MOUSE_CLICKED , event ->{
            getToolbar().getChildren().clear();
            toolbar.getChildren().addAll(imgFood , imgResources , imgArmor);
            toolbar.setSpacing(30);
            toolbar.setLayoutX(100);
            toolbar.setLayoutY(20);
        });

        imgFood.addEventHandler(MouseEvent.MOUSE_CLICKED , event -> {
            String[] keys = {
                    "meat",
                    "cheese",
                    "apple",
                    "hop",
                    "brew",
                    "wheat",
                    "flour",
                    "bread"
            };
            toolbar.getChildren().clear();
            HBox imageContainer = new HBox();
            imageContainer.setSpacing(30);
            Slider slider = new Slider(1 , 1000 , 0);
            slider.setMinWidth(400);
            Label value = new Label("amount: " +slider.getValue() );
            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                value.setText("amount: " + newValue.intValue());
            });
            HBox sliderContainer = new HBox(slider , value);
            VBox vBox= new VBox(imageContainer , sliderContainer);

            for(String key : keys ){
                ImageView img = new ImageView(AssetManager.images.get(key));
                img.addEventHandler(MouseEvent.MOUSE_CLICKED , event1 ->{
                    if(event1.getButton() == MouseButton.PRIMARY)
                    {
                        //Buy
                        Castle.buy(ResourceType.FOOD , ResourceName.valueOf(key.toUpperCase()) , prices.get(key) , (int)slider.getValue());
                    }else if(event1.getButton() == MouseButton.SECONDARY)
                    {
                        //SELl
                        Castle.sell(ResourceType.FOOD , ResourceName.valueOf(key.toUpperCase()) , prices.get(key) , (int)slider.getValue());
                    }
                });
                Tooltip.install(img , new Tooltip("price: " + prices.get(key)));
                imageContainer.getChildren().add(img);
            }
            toolbar.getChildren().add(vBox);
            toolbar.setSpacing(30);
            toolbar.setLayoutX(10);
            toolbar.setLayoutY(20);

        });
        imgResources.addEventHandler(MouseEvent.MOUSE_CLICKED , event -> {
            String[] keys = {
                    "wood",
                    "stone",
                    "iron"
            };
            toolbar.getChildren().clear();
            HBox imageContainer = new HBox();
            imageContainer.setSpacing(50);
            Slider slider = new Slider(1 , 1000 , 0);
            slider.setMinWidth(400);
            Label value = new Label("amount: " +slider.getValue() );
            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                value.setText("amount: " + newValue.intValue());
            });
            HBox sliderContainer = new HBox(slider , value);
            VBox vBox= new VBox(imageContainer , sliderContainer);
            for(String key : keys ){
                ImageView img = new ImageView(AssetManager.images.get(key));
                img.addEventHandler(MouseEvent.MOUSE_CLICKED , event1 ->{
                    if(event1.getButton() == MouseButton.PRIMARY)
                    {
                        //Buy
                        Castle.buy(ResourceType.RESOURCE , ResourceName.valueOf(key.toUpperCase()) , prices.get(key) , (int)slider.getValue());

                    }else if(event1.getButton() == MouseButton.SECONDARY)
                    {
                        //SELl
                        Castle.sell(ResourceType.RESOURCE , ResourceName.valueOf(key.toUpperCase()) , prices.get(key) , (int)slider.getValue());
                    }
                });
                Tooltip.install(img , new Tooltip("price: " + prices.get(key)));
                imageContainer.getChildren().add(img);
                toolbar.setSpacing(30);
                toolbar.setLayoutX(10);
                toolbar.setLayoutY(20);
                toolbar.setAlignment(Pos.CENTER);
            }
           toolbar.getChildren().add(vBox);
        });
        imgArmor.addEventHandler(MouseEvent.MOUSE_CLICKED , event -> {
            String[] keys = {
                    "bow",
                    "axe",
                    "sword",
                    "leather_armor",
                    "metal_armor"
            };
            toolbar.getChildren().clear();
            HBox imageContainer = new HBox();
            imageContainer.setSpacing(50);
            Slider slider = new Slider(1 , 1000 , 0);
            slider.setMinWidth(400);
            Label value = new Label("amount: " +slider.getValue() );
            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                value.setText("amount: " + newValue.intValue());
            });
            HBox sliderContainer = new HBox(slider , value);
            VBox vBox= new VBox(imageContainer , sliderContainer);
            for(String key : keys ){
                ImageView img = new ImageView(AssetManager.images.get(key));
                img.addEventHandler(MouseEvent.MOUSE_CLICKED , event1 ->{
                    if(event1.getButton() == MouseButton.PRIMARY)
                    {
                        //Buy
                        Castle.buy(ResourceType.WEAPONRY , ResourceName.valueOf(key.toUpperCase()) , prices.get(key) , (int)slider.getValue());

                    }else if(event1.getButton() == MouseButton.SECONDARY)
                    {
                        //SELl
                        Castle.sell(ResourceType.WEAPONRY , ResourceName.valueOf(key.toUpperCase()) , prices.get(key) , (int)slider.getValue());
                    }
                });
                Tooltip.install(img , new Tooltip("price: " + prices.get(key)));
                imageContainer.getChildren().add(img);
            }
            toolbar.getChildren().add(vBox);
            toolbar.setSpacing(30);
            toolbar.setLayoutX(10);
            toolbar.setLayoutY(20);
        });

        super.setToolbar(toolbar);
    }

    public static GameObject createMarketPlace()
    {
        if(count.get() < 1)
            return new MarketPlace();
        else return null;
    }

}
