package game.gameobjects;

import game.AssetManager;
import game.gameobjects.GameObject;
import game.map.Tile;
import game.map.Vector2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Building extends GameObject {

    private boolean placed;

    public Building(String imageKey)
    {
        super(imageKey);
    }


    public void produce(){}
    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
}
