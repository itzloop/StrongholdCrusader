package game.gameobjects;

import game.AssetManager;
import game.gameobjects.GameObject;
import game.map.Vector2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Building extends GameObject {

    private boolean placed;

    private HBox toolbar;
    public Building(String imageKey , Vector2D location )
    {
        super(imageKey , location );
        placed = false;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
}
