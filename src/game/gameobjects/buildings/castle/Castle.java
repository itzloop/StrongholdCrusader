package game.gameobjects.buildings.castle;

import game.AssetManager;
import game.GV;
import game.gameobjects.Building;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import game.gameobjects.buildings.Food.Granary;
import game.gameobjects.buildings.defense.Armory;
import game.gameobjects.buildings.industry.MarketPlace;
import game.gameobjects.buildings.industry.StockPile;
import game.map.Map;
import game.map.Tile;
import game.map.TileType;
import game.map.Vector2D;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class Castle  extends Building{
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final AtomicInteger maxStockPileCapacity = new AtomicInteger(0);
    private static final AtomicInteger currentStockPileCapacity = new AtomicInteger(industryResources.wood.get());
    private static final AtomicInteger maxArmoryCapacity = new AtomicInteger(0);
    private static final AtomicInteger currentArmoryCapacity = new AtomicInteger(0);
    private static final AtomicInteger maxGranaryCapacity = new AtomicInteger(0);
    private static final AtomicInteger currentGranaryCapacity = new AtomicInteger(0);
    private static final AtomicInteger gold= new AtomicInteger(GV.initialGold);
    private static final AtomicInteger maxPopulationSize = new AtomicInteger(GV.initialPopulation);
    private static final AtomicInteger currentPopulation = new AtomicInteger(GV.initialPopulation);
    private static final AtomicInteger popularity= new AtomicInteger(GV.initialPopularity);
    private static final AtomicInteger popularityRatio= new AtomicInteger(0);
    private static final AtomicInteger popularityAdded = new AtomicInteger(0);
    private static final AtomicInteger tax = new AtomicInteger(0);
    private static Label lblStockpile ;
    private static Label lblGranary ;
    private static Label lblArmory ;

    private Castle()
    {
        super("building-castle" );

        setGameObjectHelper(new GameObjectHelper( "building-castle" , getObjectId() , GameobjectType.CASTLE ));


        HBox toolbar = new HBox();
        Label lblTax = new Label("tax rate: ");
        lblTax.setStyle("-fx-font-size: 18");
        Slider slider = new Slider(-1000 , 1000 ,tax.get());
        slider.setMinWidth(300);
        Label lblTaxValue = new Label("tax: 0");
        lblTaxValue.setStyle("-fx-font-size: 18");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                lblTaxValue.setText("tax: " + newValue.intValue());
                tax.set(newValue.intValue());
                popularityRatio.set(-(newValue.intValue()));
        });
        lblStockpile = new Label();
        lblGranary= new Label();
        lblArmory= new Label();
        lblGranary.setStyle("-fx-font-size: 15");
        lblArmory.setStyle("-fx-font-size: 15");
        lblStockpile.setStyle("-fx-font-size: 15");
        VBox vBox= new VBox(lblGranary , lblStockpile , lblArmory);
        vBox.setLayoutY(20);
        HBox hBox = new HBox(lblTax , slider , lblTaxValue);
        Pane pane = new Pane( hBox,vBox);
        toolbar.setLayoutX(20);
        toolbar.getChildren().addAll(pane);
        super.setToolbar(toolbar);
        count.getAndIncrement();
    }


    public static Castle createCastle()
    {
        if(count.get() < 1){
            return new Castle();
        }
        else return null;
    }

    public static void caculate()
    {
        //set the gold based on the tax
        gold.addAndGet(tax.get());


        //calculate popularity
        if(popularityAdded.get() >= 500 || popularityAdded.get() <= -500)
        {
            popularity.addAndGet(popularityAdded.get()/500);
            popularityAdded.set(0);
        }
        else
            popularityAdded.getAndAdd(popularityRatio.get());
        if(popularity.get() >= 100 )
        {
            popularity.set(100);
        }else if(popularity.get() <= 0)
        {
            popularity.set(0);
        }

        StockPile.update();
        Granary.update();
        Armory.update();

        Platform.runLater(() -> {
            lblStockpile.setText("Stock Pile Capacity: "+ currentStockPileCapacity.get() +"/" +  maxStockPileCapacity.get() );
            lblGranary.setText("Granary Capacity: "+ currentGranaryCapacity.get() + "/" + maxGranaryCapacity.get());
            lblArmory.setText("Armory Capacity: "+ currentArmoryCapacity.get() + "/" + maxArmoryCapacity.get());
        });
    }


    //************************* BUY ************************
    public static boolean buy(ResourceType type ,ResourceName name , int price,int amount){
        //see if we have the money
        if(gold.get() - price*amount < 0)
        {
            Map.playSound(AssetManager.sounds.get("need-gold"));
            return false;
        }

        switch (type)
        {
            case FOOD:
                return buyFood(name , price,amount);
            case RESOURCE:
                return buyResource(name ,price, amount);
            case WEAPONRY:
                return buyWeaponry(name , price,amount);
            default:
                return false;
        }
    }

    private static boolean buyFood(ResourceName name,int price ,int amount) {


        //if we dont have a granary
        if(maxGranaryCapacity.get() == 0)
        {
            Map.playSound(AssetManager.sounds.get("no-granary"));
            return false;
        }
        if(currentGranaryCapacity.get() + amount > maxGranaryCapacity.get())
        {
            Map.playSound(AssetManager.sounds.get("full-granary"));
            return false;
        }

        switch (name)
        {
            case MEAT:
                Food.meat.getAndAdd(amount);
                break;
            case CHEESE:
                Food.cheese.getAndAdd(amount);
                break;
            case APPLE:
                Food.apple.getAndAdd(amount);
                break;
            case HOP:
                Food.hop.getAndAdd(amount);
                break;
            case BREW:
                Food.brew.getAndAdd(amount);
                break;
            case WHEAT:
                Food.wheat.getAndAdd(amount);
                break;
            case FLOUR:
                Food.flour.getAndAdd(amount);
                break;
            case BREAD:
                Food.bread.getAndAdd(amount);
                break;
        }
        currentGranaryCapacity.getAndAdd(amount);
        gold.getAndAdd(-price*amount);
        return true;
    }

    private static boolean buyResource(ResourceName name, int price,int amount) {

        if(currentStockPileCapacity.get() + amount > maxStockPileCapacity.get())
        {
            Map.playSound(AssetManager.sounds.get("full-stockpile"));
            return false;
        }

        switch (name)
        {
            case WOOD:
                industryResources.wood.getAndAdd(amount);
                break;
            case STONE:
                industryResources.stone.getAndAdd(amount);
                break;
            case IRON:
                industryResources.iron.getAndAdd(amount);
                break;
        }
        currentStockPileCapacity.getAndAdd(amount);
        gold.getAndAdd(-price*amount);
        return true;
    }

    private static boolean buyWeaponry(ResourceName name, int price , int amount) {

        if(maxArmoryCapacity.get() == 0)
        {
            Map.playSound(AssetManager.sounds.get("no-armory"));
            return false;
        }

        if(currentArmoryCapacity.get() + amount > maxArmoryCapacity.get())
        {
            Map.playSound(AssetManager.sounds.get("full-armory"));
            return false;
        }
        switch (name)
        {
            case BOW:
                Weaponry.bow.getAndAdd(amount);
                break;
            case AXE:
                Weaponry.axe.getAndAdd(amount);
                break;
            case SWORD:
                Weaponry.sword.getAndAdd(amount);
                break;
            case LEATHER_ARMOR:
                Weaponry.leatherArmor.getAndAdd(amount);
                break;
            case METAL_ARMOR:
                Weaponry.metalArmor.getAndAdd(amount);
                break;
        }
        currentArmoryCapacity.getAndAdd(amount);
        gold.getAndAdd(-price*amount);
        return true;
    }


    //************************* SELL ************************


    public static boolean sell(ResourceType type ,ResourceName name , int price,int amount){

        switch (type)
        {
            case FOOD:
                return sellFood(name , price,amount);
            case RESOURCE:
                return sellResource(name ,price, amount);
            case WEAPONRY:
                return sellWeaponry(name , price,amount);
            default:
                return false;
        }
    }


    private static boolean sellFood(ResourceName name,int price ,int amount) {


        if(currentGranaryCapacity.get() == 0)
        {
            Map.playSound(AssetManager.sounds.get("empty-granary"));
            return false;
        }

        switch (name)
        {
            case MEAT:
                if(Food.meat.get()- amount >= 0)
                    Food.meat.getAndAdd(-amount);
                else return false;
                break;
            case CHEESE:
                if(Food.cheese.get() -amount >=0)
                    Food.cheese.getAndAdd(-amount);
                else return false;
                break;
            case APPLE:
                if(Food.apple.get() - amount >= 0 )
                    Food.apple.getAndAdd(-amount);
                else return false;
                break;
            case HOP:
                if(Food.hop.get() - amount >= 0)
                    Food.hop.getAndAdd(-amount);
                else return false;
            case BREW:
                if(Food.brew.get() - amount >= 0)
                    Food.brew.getAndAdd(-amount);
                else return false;
                break;
            case WHEAT:
                if(Food.wheat.get() - amount >= 0)
                    Food.wheat.getAndAdd(-amount);
                else return false;
                break;
            case FLOUR:
                if(Food.flour.get() - amount >= 0 )
                    Food.flour.getAndAdd(-amount);
                else return false;
                break;
            case BREAD:
                if(Food.brew.get() - amount >= 0)
                    Food.bread.getAndAdd(-amount);
                else return false;
                break;
        }
        currentGranaryCapacity.addAndGet(-amount);
        gold.getAndAdd(price*amount);
        return true;
    }

    private static boolean sellResource(ResourceName name, int price,int amount) {

        switch (name)
        {
            case WOOD:
                if(industryResources.wood.get() - amount >= 0)
                    industryResources.wood.getAndAdd(-amount);
                else return false;
                break;
            case STONE:
                if(industryResources.stone.get() - amount >= 0)
                    industryResources.stone.getAndAdd(-amount);
                else return false;
                break;
            case IRON:
                if(industryResources.iron.get() - amount >= 0)
                    industryResources.iron.getAndAdd(-amount);
                else return false;
                break;
        }
        currentStockPileCapacity.addAndGet(-amount);
        gold.getAndAdd(price*amount);
        return true;
    }

    private static boolean sellWeaponry(ResourceName name, int price , int amount) {
        switch (name)
        {
            case BOW:
                if(Weaponry.bow.get() - amount >= 0)
                    Weaponry.bow.getAndAdd(-amount);
                else return false;
                break;
            case AXE:
                if(Weaponry.axe.get() - amount >= 0)
                    Weaponry.axe.getAndAdd(-amount);
                else return false;
                break;

            case SWORD:
                if(Weaponry.sword.get() - amount >= 0)
                    Weaponry.sword.getAndAdd(-amount);
                else return false;
                break;
            case LEATHER_ARMOR:
                if(Weaponry.leatherArmor.get() - amount >= 0)
                    Weaponry.leatherArmor.getAndAdd(-amount);
                else return false;
                break;
            case METAL_ARMOR:
                if(Weaponry.metalArmor.get() - amount >= 0)
                    Weaponry.metalArmor.getAndAdd(amount);
                else return false;
                break;
        }
        currentArmoryCapacity.getAndAdd(-amount);
        gold.getAndAdd(price*amount);
        return true;
    }


    public static int resourceAmount(ResourceName name)
    {
        switch (name)
        {
            case BREAD:
                return Food.bread.get();
            case APPLE:
                return Food.apple.get();
            case CHEESE:
                return Food.cheese.get();
            case FLOUR:
                return Food.flour.get();
            case HOP:
                return Food.hop.get();
            case MEAT:
                return Food.meat.get();
            case WHEAT:
                return Food.wheat.get();
            case BREW:
                return Food.brew.get();
            case WOOD:
                return industryResources.wood.get();
            case STONE:
                return industryResources.stone.get();
            case IRON:
                return industryResources.iron.get();
            case AXE:
                return Weaponry.axe.get();
            case BOW:
                return Weaponry.bow.get();
            case SWORD:
                return Weaponry.sword.get();
            case LEATHER_ARMOR:
                return Weaponry.leatherArmor.get();
            case METAL_ARMOR:
                return Weaponry.metalArmor.get();
            default:
                return -1;
        }
    }


    public static void setResouce(ResourceName name , int amount)
    {
        switch (name)
        {
            case BREAD:
                Food.bread.getAndAdd(amount);
                break;
            case APPLE:
                Food.apple.getAndAdd(amount);
                break;
            case CHEESE:
                Food.cheese.getAndAdd(amount);
                break;
            case FLOUR:
                Food.flour.getAndAdd(amount);
                break;
            case HOP:
                Food.hop.getAndAdd(amount);
                break;
            case MEAT:
                Food.meat.getAndAdd(amount);
                break;
            case WHEAT:
                Food.wheat.getAndAdd(amount);
                break;
            case BREW:
                Food.brew.getAndAdd(amount);
                break;
            case WOOD:
                industryResources.wood.getAndAdd(amount);
                break;
            case STONE:
                industryResources.stone.getAndAdd(amount);
                break;
            case IRON:
                industryResources.iron.getAndAdd(amount);
                break;
            case AXE:
                Weaponry.axe.getAndAdd(amount);
                break;
            case BOW:
                Weaponry.bow.getAndAdd(amount);
                break;
            case SWORD:
                Weaponry.sword.getAndAdd(amount);
                break;
            case LEATHER_ARMOR:
                Weaponry.leatherArmor.getAndAdd(amount);
                break;
            case METAL_ARMOR:
                Weaponry.metalArmor.getAndAdd(amount);
                break;
        }
    }






    public static AtomicInteger getMaxStockPileCapacity() {
        return maxStockPileCapacity;
    }

    public static AtomicInteger getMaxGranaryCapacity() {
        return maxGranaryCapacity;
    }

    public static AtomicInteger getGold() {
        return gold;
    }

    public static AtomicInteger getMaxPopulationSize() {
        return maxPopulationSize;
    }

    public static AtomicInteger getCurrentPopulation() {
        return currentPopulation;
    }

    public static AtomicInteger getPopularity() {
        return popularity;
    }

    public static AtomicInteger getCurrentStockPileCapacity() {
        return currentStockPileCapacity;
    }

    public static AtomicInteger getMaxArmoryCapacity() {
        return maxArmoryCapacity;
    }

    public static AtomicInteger getCurrentArmoryCapacity() {
        return currentArmoryCapacity;
    }

    public static AtomicInteger getCurrentGranaryCapacity() {
        return currentGranaryCapacity;
    }

    public static AtomicInteger getPopularityRatio() {
        return popularityRatio;
    }

    public static AtomicInteger getTax() {
        return tax;
    }


}


