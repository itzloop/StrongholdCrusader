package game.map;

import game.AssetManager;
import game.GV;
import game.gameobjects.GameObject;
import game.gameobjects.GameObjectHelper;
import game.gameobjects.GameobjectType;
import javafx.scene.image.ImageView;

public class Tile extends ImageView implements Comparable<Tile>
{
    private TileType tileType;
    private Vector2D coordinate;
    private Vector2D pos;
    private GameobjectType occupiedType;
    private double g;
    private double f;
    private boolean obstacle;
    public Tile(TileType tileType ,Vector2D location , double x , double y) {
        super();
        switch (tileType)
        {
            case DUST:
                double rand =Math.random()*100000;
                if(rand < 50000) super.setImage(AssetManager.images.get("dust"));
                else if(rand <75500  && rand >=50000) super.setImage(AssetManager.images.get("dust2"));
                else super.setImage(AssetManager.images.get("dust2"));
                obstacle = false;
                break;
            case MOUNTAIN:
                double rand2 =Math.random()*100000;
                if(rand2 < 50000) super.setImage(AssetManager.images.get("mount1"));
                else if(rand2 <75500  && rand2 >=50000) super.setImage(AssetManager.images.get("mount2"));
                else super.setImage(AssetManager.images.get("mount3"));
                obstacle = true;
                break;
            case SEA:
                obstacle = true;
                super.setImage(AssetManager.images.get("sea"));
                break;
            case GRASS:
                obstacle = false;
                super.setImage(AssetManager.images.get("grass"));
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

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public GameobjectType getOccupiedType() {
        return occupiedType;
    }

    public void setOccupiedType(GameobjectType occupiedType) {
            this.occupiedType = occupiedType;
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
        return "pos = " + pos;
    }


    @Override
    public int compareTo(Tile that) {
        return Double.compare(this.f, that.f);
    }

    public boolean isObstacle() {
        return obstacle;
    }
}
