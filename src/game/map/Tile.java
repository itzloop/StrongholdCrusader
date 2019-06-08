package game.map;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle
{
    private TileType tileType;

    public Tile(TileType tileType) {
        this.tileType = tileType;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
