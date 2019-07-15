package game.map;

import game.AssetManager;
import game.GV;
import game.gameobjects.GameObject;
import game.gameobjects.GameObjectHelper;
import javafx.scene.image.ImageView;

public class Tile extends ImageView
{




    private TileType tileType;
    private Vector2D coordinate;
    private Vector2D pos;
    private GameObjectHelper placedGameobject;
    public Tile(TileType tileType ,Vector2D location , double x , double y) {
        super();
        switch (tileType)
        {
            case DUST:
                super.setImage(AssetManager.assets.get("dust"));
                break;
            case MOUNTAIN:

                super.setImage(AssetManager.assets.get("dust"));
                break;
            case SEA:

                super.setImage(AssetManager.assets.get("dust"));
                break;
        }

        this.tileType = tileType;
        this.coordinate = location;

        this.pos = new Vector2D(x + GV.tileSize.getX()/2 , y+ GV.tileSize.getY()/2);
        super.setX(x);
        super.setY(y);
        super.setFitWidth(GV.tileSize.getX());
        super.setFitHeight(GV.tileSize.getY());
    }


    public GameObjectHelper getPlacedGameobject() {
        return placedGameobject;
    }

    public void setPlacedGameobject(GameObjectHelper placedGameobject) {
        this.placedGameobject = placedGameobject;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public Vector2D getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double x, double y) {
        this.coordinate.set(x , y);
    }

    public void setPos(double x , double y) {
        this.pos.set(x ,y);
        this.setX(x);
        this.setY(y);
    }

    public Vector2D getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return "[source = pane , " + "tile type = " + tileType + " , coordinate = " + coordinate + " , pos = " + pos;
    }
}
