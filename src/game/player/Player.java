package game.player;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;
import game.map.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Player
{
    private String name;
    private int id;
    private Map map;

    private final static AtomicInteger idCounter= new AtomicInteger(0);

    private Player(String name)
    {
        this.name = name;
        this.id = idCounter.getAndIncrement();
    }
    //TODO fix this so we can have more than one game at a time i think im wrong but anyways :)
    public static Player createPlayer(String name)
    {
        if(idCounter.get() < 4)
            return new Player(name);
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
