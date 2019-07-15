package game.gameobjects.buildings;

import game.GV;
import game.gameobjects.Building;
import game.map.Vector2D;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class Castle  extends Building{
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final AtomicInteger maxStockPileCapacity = new AtomicInteger(0);
    private static final AtomicInteger currentStockPileCapacity = new AtomicInteger(0);
    private static final AtomicInteger maxArmoryCapacity = new AtomicInteger(0);
    private static final AtomicInteger currentArmoryCapacity = new AtomicInteger(0);
    private static final AtomicInteger maxGranaryCapacity = new AtomicInteger(0);
    private static final AtomicInteger currentGranaryCapacity = new AtomicInteger(0);
    private static final AtomicInteger gold= new AtomicInteger(GV.initialGold);
    private static final AtomicInteger maxPopulationSize = new AtomicInteger(GV.initialPopulation);
    private static final AtomicInteger currentPopulation = new AtomicInteger(GV.initialPopulation);
    private static final AtomicInteger popularity= new AtomicInteger(GV.initialPopularity);
    private static final AtomicInteger popularityRatio= new AtomicInteger(0);
    private static final AtomicInteger temp = new AtomicInteger(0);
    private static final AtomicInteger tax = new AtomicInteger(0);

    private static Label lblStockpile ;
    private static Label lblGranary ;
    private Castle(Vector2D location)
    {
        super("building-castle" , location);
        HBox toolbar = new HBox();
        Label lblTax = new Label("tax rate: ");
        lblTax.setStyle("-fx-font-size: 18");
        Slider slider = new Slider(-100 , 100 ,tax.get());
        Label lblTaxValue = new Label("tax: 0");
        lblTaxValue.setStyle("-fx-font-size: 18");
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                lblTaxValue.setText("tax: " + newValue.intValue());
                tax.set(newValue.intValue());
                popularityRatio.set(-(newValue.intValue()));
        });
        lblStockpile = new Label("Stock Pile Capacity: " + maxStockPileCapacity.get());
        lblGranary= new Label("Granary Capacity: " + maxGranaryCapacity.get());
        lblGranary.setStyle("-fx-font-size: 15");
        lblStockpile.setStyle("-fx-font-size: 15");
        VBox vBox= new VBox(lblGranary , lblStockpile);
        vBox.setLayoutY(25);
        HBox hBox = new HBox(lblTax , slider , lblTaxValue);
        Pane pane = new Pane( hBox,vBox);
        toolbar.setLayoutX(20);
        toolbar.setLayoutY(10);
        toolbar.getChildren().addAll(pane);
        super.setToolbar(toolbar);
        count.getAndIncrement();
    }


    public static Castle createCastle(Vector2D location)
    {
        if(count.get() < 1){
            return new Castle(location);
        }
        else return null;
    }

    public static void caculate()
    {
        gold.addAndGet(tax.get());
        if(temp.get() >= 500 || temp.get() <= -500)
        {
            popularity.addAndGet(temp.get()/500);
            temp.set(0);
        }
        else
            temp.getAndAdd(popularityRatio.get());
        if(popularity.get() >= 100 )
        {
            popularity.set(100);
        }else if(popularity.get() <= 0)
        {
            popularity.set(0);
        }

        System.out.println(maxStockPileCapacity.get());
        lblStockpile.setText("Stock Pile Capacity: "+ maxStockPileCapacity.get() );
        lblGranary.setText("Granary Capacity: " + maxGranaryCapacity.get());
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
