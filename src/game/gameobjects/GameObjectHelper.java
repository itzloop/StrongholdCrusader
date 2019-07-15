package game.gameobjects;

import game.map.Vector2D;

public class GameObjectHelper {
    private String imageKey;
    private int Id;
    private int playerId;
    private Vector2D location;

    public GameObjectHelper(String imageKey, int id, int playerId, Vector2D location) {
        this.imageKey = imageKey;
        Id = id;
        this.playerId = playerId;
        this.location = location;
    }

    public Vector2D getLocation() {
        return location;
    }

    public void setLocation(Vector2D location) {
        this.location = location;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
