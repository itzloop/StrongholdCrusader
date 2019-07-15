package game.gameobjects;

import game.AssetManager;
import game.map.Vector2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.concurrent.atomic.AtomicInteger;

public class GameObject extends ImageView {
    private static final AtomicInteger idGenerator= new AtomicInteger(0);
    private int id;
    private int playerId;
    private double health;
    private GameObjectHelper gameObjectHelper;
    private Vector2D location;
    private HBox toolbar;
    public GameObject(String imageKey , Vector2D location ){
        super(AssetManager.assets.get(imageKey));
        super.setLayoutX(location.getX());
        super.setLayoutY(location.getY());
        this.id = idGenerator.getAndIncrement();
        this.location = location;
        this.health = 100;
        gameObjectHelper =new GameObjectHelper(imageKey , id , playerId , location);
    }

    public void setToolbar(HBox toolbar) {
        this.toolbar = toolbar;
    }

    public HBox getToolbar() {
        return toolbar;
    }

    public Vector2D getLocation() {
        return location;
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

    public void setLocation(double x , double y) {
        location.set(x , y);
        this.setX(x);
        this.setY(y);
    }

}
