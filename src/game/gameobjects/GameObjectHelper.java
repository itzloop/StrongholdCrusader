package game.gameobjects;

import game.map.Vector2D;
import game.player.Player;

public class GameObjectHelper {

    private int Id;
    private int playerId;
    private GameobjectType type;
    private int i , j;
    public GameObjectHelper(String imageKey, int id, GameobjectType type) {
        Id = id;
        this.type = type;
        this.playerId = Player.getId();

    }

    public void setIJ(int i , int j)
    {
        this.i = i;
        this.j = j;
    }


    public GameobjectType getType() {
        return type;
    }

    public void setType(GameobjectType type) {
        this.type = type;
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
