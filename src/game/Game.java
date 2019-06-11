package game;

import game.map.Map;
import game.network.Server;
import game.player.Client;
import java.io.IOException;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

public class Game
{
    private List<Client> players;
    private Map map;
    private Server server;

    public Game(Server server)
    {
        players = new LinkedList<>();

    }
    public void start() throws SocketException {
        server = new Server(GV.port);
        //TODO initialize the map here
        server.listen();

    }




}