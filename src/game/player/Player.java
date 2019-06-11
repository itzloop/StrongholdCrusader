package game.player;

import game.GV;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import game.map.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Player
{
    private String name;
    private int id;
    private Map map;
    private DatagramSocket socket;
    private final static AtomicInteger idCounter= new AtomicInteger(0);

    private Player(String name) throws SocketException {
        this.socket = new DatagramSocket();
        this.name = name;
        this.id = idCounter.getAndIncrement();
    }
    //TODO fix this so we can have more than one game at a time i think im wrong but anyways :)
    public static Player createPlayer(String name) throws SocketException {
        if(idCounter.get() < 4)
            return new Player(name);
        return null;
    }
    public void sendRequest(Request request) throws IOException {
        String reqStr;
        byte[] req;
        switch (request)
        {
            case ESTABLISHING_CONNECTION:
                reqStr = request.ordinal()+":"+getId()+":"+getName();
                req = reqStr.getBytes();
                socket.send(new DatagramPacket(req , req.length , InetAddress.getLocalHost() , GV.port));
                break;
        }
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
