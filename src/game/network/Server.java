package game.network;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import game.GV;
import game.map.Map;
import game.player.Player;
import game.player.Request;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.*;


public class Server
{
    Queue<Request> requests;
    private Map map;
    private java.util.Map<Integer, Player> players;
    private final int port;
    DatagramSocket server;
    DatagramPacket packet = null;
    byte[] buffer = null;
    public Server(int port) throws SocketException
    {
        players = new HashMap<>();
        server = new DatagramSocket(port);
        buffer = new byte[GV.packetSize];
        this.port = port;
    }

    //TODO az shabke samte client req begir handle kon javabo bargardoon
    public void handle(DatagramPacket packet)  {
        new Thread(() -> {
            byte[] data = new byte[packet.getLength()];
            System.arraycopy(packet.getData() , 0 , data , 0 , packet.getLength());
            System.out.println(data.length);
            String requestStr = new String(data);
            System.out.println(requestStr);

            Request request = new Gson().fromJson(requestStr , Request.class);
            switch (request.getRequestType())
            {
                case ESTABLISHING_CONNECTION:
                    Player p = new Gson().fromJson(request.getBody() , Player.class);
                    players.put(p.getId() , p);
                    System.out.println(players);
                    break;
            }
        }).start();

    }

    public void listen() {
        new Thread(() -> {
            System.out.println("server started on port: " + port);
           while (true)
           {
               packet = new DatagramPacket(buffer , buffer.length);
               try {
                   server.receive(packet);
                   System.out.println("packet received");
                    handle(packet);
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
        }).start();
    }

    public static void main(String[] args) throws SocketException {
        Server server = new Server(GV.port);
        server.listen();

    }
    public int getPort() {
        return port;
    }
}
