package game.gameobjects.buildings.industry;

import game.GV;
import game.gameobjects.Building;
import game.gameobjects.buildings.Castle;
import game.map.Vector2D;
import javafx.scene.layout.HBox;

import java.util.concurrent.atomic.AtomicInteger;

public class StockPile extends Building {



    private static AtomicInteger wood = new AtomicInteger(0);
    private static AtomicInteger stone = new AtomicInteger(0);

    public StockPile(Vector2D location){
        super("building-industry-1" , location);
        HBox toolbar = new HBox();
        Castle.getMaxStockPileCapacity().getAndAdd(GV.stockPileCapacity);

        super.setToolbar(toolbar);
    }


}
