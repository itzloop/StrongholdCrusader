package game.network;

import com.google.gson.Gson;
import game.GV;
import game.network.communications.*;
import game.map.Map;
import game.network.communications.message.Request;
import game.network.communications.message.RequestType;
import game.network.communications.message.Respond;
import game.network.communications.message.RespondType;
import game.network.console.Console;
import game.player.Player;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class Server
{
    private byte[]                     buffer;
    private int                        port;
    private Map                        map;
    private List<Player>               players;
    private DatagramSocket             server;
    private DatagramPacket             packet           = null;
    private Communication              communication;
    private Console console;
    private final static AtomicInteger playerId         = new AtomicInteger(0);
    private final static AtomicInteger portCounter      = new AtomicInteger(15152);


    //Constructor
    public Server()
    {
        //initialize the map
        map = new Map("sample Map" , 70 , 70);

        //initializing the Console for this server
        Function<String , Void> consoleBehavior = (command) -> {
            switch (command) {
                case "players":
                    System.out.println(this.getPlayers());
                    break;
                case "ip":
                    try {
                        System.out.println(this.getIp());
                    }
                    catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case "stop":
                    //TODO make the server stop by adding flags to Threads
                    break;
            }
            return null;
        };
        console = new Console(consoleBehavior);
        new Thread(console).start();

        //initialize and start respond and request communicationListener
        Handler requestHandler = (message) -> {
            Request request = (Request) message;
            Respond respond = null;
            System.out.println("new "+request.getRequestType());
            switch (request.getRequestType()) {
                case CONNECT_TO_SERVER:
                    Player p = new Gson().fromJson(request.getMessage(), Player.class);
                    if (playerId.get() < GV.maxPlayers) {
                    p.setId(playerId.getAndIncrement());
                    players.add(p);
                    System.out.println(players);
                    respond = new Respond(p.getId(),p.getPort(),request.getDestIp(), RespondType.PLAYER_CREATED, new Gson().toJson(map));
                    return respond;
                } else {
                    respond = new Respond(p.getPort(),request.getDestIp(),RespondType.MAX_PLAYERS_REACHED , "Max Players reached");
                    return respond;
                }
            }
            return respond;
        };
        Handler respondHandler = (message) -> {
            Respond respond = (Respond) message;
            switch (respond.getRespondType())
            {
                case PLAYER_CREATED:
                    buffer = new Gson().toJson(respond).getBytes();
                    try {
                        server.send(new DatagramPacket(buffer , buffer.length , respond.getDestIp() , respond.getPort()));
                        System.out.println("Respond has been sent");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return null;
        };
        communication = new Communication(requestHandler , respondHandler);

        //initialize the players list
        console.log("Players list initialized");
        players = new ArrayList<>();

        //assign an available port to this server
        portFinder();
        this.port = portCounter.getAndIncrement();

        //initialize the buffer byte-array with packet-size
        buffer = new byte[GV.packetSize];

        //Start the server
        start();

    }

    private void start() {
        try {
            //sending the request for adding this server to the list
            Request request = new Request(port,GV.Ip, RequestType.ADD_SERVER , "add me to the list");
            byte[] data = new Gson().toJson(request).getBytes();
            server.send(new DatagramPacket(data , data.length , GV.Ip , GV.serverHolderPort));
            console.log("server started on : " + getIp()+"/"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true)
        {
            try {
                packet = new DatagramPacket(buffer , buffer.length);
                server.receive(packet);
                communication.getParser().parse(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //assign a new port to this server
    private void portFinder()
    {
        while (true)
        {
            try {
                server = new DatagramSocket(portCounter.get() , GV.Ip);
                break;
            } catch (SocketException e) {
                portCounter.getAndIncrement();
                continue;
            }
        }
    }






    public void setPort(int port) {
        this.port = port;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getIp() {
        return server.getLocalAddress().getHostAddress();
    }

    public static void main(String[] args) throws SocketException {
        Server server = new Server();

    }



    public int getPort() {
        return port;
    }
}
