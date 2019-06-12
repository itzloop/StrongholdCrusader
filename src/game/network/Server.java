package game.network;

import com.google.gson.Gson;
import game.GV;
import game.map.Map;
import game.player.Player;
import game.player.Request;
import game.player.RequestType;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server
{
    BlockingQueue<Request> requests;
    private Map map;
    private java.util.Map<Integer, Player> players;
    private final int port;
    DatagramSocket server;
    DatagramPacket packet = null;
    byte[] buffer = null;
    Thread requestListener = new Thread(() -> {
        while (true)
        { if(!requests.isEmpty()) handleRequest(requests.poll()); }
    });
    Thread serverStarter = new Thread(() -> {
        while (true)
        {
            try {
                packet = new DatagramPacket(buffer , buffer.length);
                server.receive(packet);
                parseRequest(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });

    public Server(int port) throws SocketException
    {
        requests = new ArrayBlockingQueue<>(1000);
        requestListener.start();
        players = new HashMap<>();
        server = new DatagramSocket(port);
        buffer = new byte[GV.packetSize];
        this.port = port;
    }


    private void parseRequest(DatagramPacket packet)
    {
        byte[] data = new byte[packet.getLength()];
        System.arraycopy(packet.getData() , 0 , data , 0 , packet.getLength());
        String requestStr = new String(data);
        Request request = new Gson().fromJson(requestStr , Request.class);
        requests.add(request);
    }

    public void handleRequest(Request request)  {
        switch (request.getRequestType())
        {
            case ESTABLISHING_CONNECTION:
                Player p = new Gson().fromJson(request.getBody() , Player.class);
                players.put(p.getId() , p);
                break;
        }
    }
    public void start() {
        try {
            server.send(new DatagramPacket(new byte[1] , 1 , InetAddress.getLocalHost() , GV.serverHolderPort));
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverStarter.start();
        System.out.println("server started on port: " + port);
    }

    public BlockingQueue<Request> getRequests() {
        return requests;
    }


    public int getPort() {
        return port;
    }

    public static void main(String[] args) throws SocketException {
        Server server = new Server(new Scanner(System.in).nextInt());
        server.start();


    }
}
