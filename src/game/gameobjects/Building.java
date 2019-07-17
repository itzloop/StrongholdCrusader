package game.gameobjects;

import game.AssetManager;
import game.gameobjects.GameObject;
import game.map.Tile;
import game.map.Vector2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Building extends GameObject {

    private boolean placed;

    public Building(String imageKey , Vector2D location , Tile on)
    {
        super(imageKey , location , on);
        placed = false;
    }

    public void produce(){}
    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
}
