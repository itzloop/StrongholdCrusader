package game.gameobjects;

import game.AssetManager;
import game.gameobjects.buildings.castle.Castle;
import game.gameobjects.buildings.industry.StockPile;
import game.map.Map;
import game.map.Tile;
import game.map.Vector2D;
import game.player.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.concurrent.atomic.AtomicInteger;

public class GameObject extends ImageView {
    private static final AtomicInteger idGenerator= new AtomicInteger(0);
    private int id;
    private int playerId;
    private double health;
    private int i;
    private int j;
    private GameObjectHelper gameObjectHelper;
    private HBox toolbar;
    public GameObject(String imageKey ){
        super(AssetManager.images.get(imageKey));
        this.id = idGenerator.getAndIncrement();
        this.health = 100;
        playerId = Player.getId();
    }


    public Tile getTile() {
        return Map.tiles[i][j];
    }

    public void setIJ(int i , int j)
    {
        this.i = i ;
        this.j = j;
        gameObjectHelper.setIJ(i , j);
        Tile on = Map.tiles[i][j];
        if(this instanceof Building)
        {
            if(this instanceof Castle)
            {
                on.setOccupiedType(gameObjectHelper.getType());
                for (Tile t : Map.adjList.get(on)){
                    t.setOccupiedType(gameObjectHelper.getType());
                    t.setImage(AssetManager.images.get("grass"));
                }
                for (Tile t : Map.adjList.get(Map.tiles[i+2][j])){
                    t.setOccupiedType(gameObjectHelper.getType());
                    t.setImage(AssetManager.images.get("grass"));
                }
            }else{
                on.setOccupiedType(gameObjectHelper.getType());
                for (Tile t : Map.adjList.get(on)){
                    t.setOccupiedType(gameObjectHelper.getType());
                    t.setImage(AssetManager.images.get("grass"));
                }
            }
        }
        this.setLayoutX(on.getPos().getX() - this.getImage().getWidth()/2);
        this.setLayoutY(on.getPos().getY() - this.getImage().getHeight()/2);
    }

    public void setTile(Tile t)
    {
        this.i = (int)t.getCoordinate().getY();
        this.j = (int)t.getCoordinate().getX();
        this.setLayoutX(t.getPos().getX() - this.getImage().getWidth()/2);
        this.setLayoutY(t.getPos().getY() - this.getImage().getHeight()/2);
    }




    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setToolbar(HBox toolbar) {
        this.toolbar = toolbar;
    }

    public HBox getToolbar() {
        return toolbar;
    }



    public static AtomicInteger getIdGenerator() {
        return idGenerator;
    }

    public int getObjectId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public GameObjectHelper getGameObjectHelper() {
        return gameObjectHelper;
    }

    public void setGameObjectHelper(GameObjectHelper gameObjectHelper) {
        this.gameObjectHelper = gameObjectHelper;
    }



}
