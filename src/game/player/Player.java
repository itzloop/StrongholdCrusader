package game.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import game.GV;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import game.map.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
public class Player
{


    private int id;
    private int port;
    private final static AtomicInteger idCounter= new AtomicInteger(0);
    private final static AtomicInteger portCounter = new AtomicInteger(15152);
    private String name;
    private transient Map map;
    private transient DatagramSocket socket;




    private Player(String name) throws SocketException {
        this.id = idCounter.getAndIncrement();
        this.port = portCounter.getAndIncrement();
        this.name = name;
        while (true)
        {
            try {
                this.socket = new DatagramSocket(port);
            }
            catch (SocketException e)
            {
                this.port = portCounter.getAndIncrement();
                continue;
            }
            break;
        }
    }

    private Player(String name , int id , int port)
    {
        this.port = port;
        this.name = name;
        this.id = id;
    }

    //TODO fix this so we can have more than one game at a time i think im wrong but anyways :)
    public static Optional<Player> createPlayer(String name) throws SocketException {
        if(idCounter.get() < GV.maxPlayers)
            return Optional.ofNullable(new Player(name));
        return Optional.empty();
    }


    public void sendRequest(RequestType requestType) throws IOException {

        switch (requestType)
        {
            case ESTABLISHING_CONNECTION:
                Request request = new Request(RequestType.ESTABLISHING_CONNECTION , new Gson().toJson(this));
                byte[] requestByte =new Gson().toJson(request).getBytes();
                System.out.println(requestByte.length);
                socket.send(new DatagramPacket(requestByte , requestByte.length , InetAddress.getLocalHost() ,GV.port ));
                break;
        }
    }


    private String sendPlayer()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
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

    public static void main(String[] args) throws SocketException {
        Optional<Player> p = Player.createPlayer("Qazal");
        Optional<Player> p1 = Player.createPlayer("Sina");
        Optional<Player> p2 = Player.createPlayer("Sina");
        if(p.isPresent())
        {
            List<Player> players = Arrays.asList(p.get() , p1.get() , p2.get());
            Gson gson = new Gson();
            System.out.println(gson.toJson(players));

        }


    }
}
