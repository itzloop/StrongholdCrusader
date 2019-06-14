package game.map;

import game.GV;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle
{
    private TileType tileType;

    public Tile(TileType tileType ,int x  , int y) {
        super(x,y,30,30);
        this.tileType = tileType;
        switch (tileType)
        {
            case DUST:
                super.setFill(Color.ORANGE);
                break;
            case MOUNTAIN:
                super.setFill(Color.BROWN);
                break;
            case SEA:
                super.setFill(Color.BLUE);
                break;
        }
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
