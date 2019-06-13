package game;

import game.map.Map;
import game.network.Server;
import game.player.Player;

import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

public class Game
{
    private Server server;

    public Game(Server server)
    {
    }
    public void start() throws SocketException {
        server = new Server();
        //TODO initialize the map here
        server.start();

    }




}
