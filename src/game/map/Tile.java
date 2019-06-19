package game.map;

import game.GV;
import game.gameobjects.Gameobject;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane
{
    private TileType tileType;
    private Rectangle rectangle;
    private Gameobject gameobject;
    private Vector2D locatoion;
    public Tile(TileType tileType ,Vector2D location) {
        this.tileType = tileType;
        this.locatoion = location;
        rectangle = new Rectangle();
        rectangle.setWidth(GV.tileWidth);
        rectangle.setHeight(GV.tileHeight);
        super.setLayoutX(location.getX());
        super.setLayoutY(location.getY());
        rectangle.setX(location.getX());
        rectangle.setY(location.getY());

        switch (tileType)
        {
            case DUST:
                rectangle.setFill(Color.ORANGE);
                break;
            case MOUNTAIN:
                rectangle.setFill(Color.BROWN);
                break;
            case SEA:
                rectangle.setFill(Color.BLUE);
                break;
        }
        super.getChildren().add(rectangle);
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    @Override
    public String toString() {
        return "[source = pane , " + "tile type = " + tileType + " , location = " + locatoion;
    }
}
