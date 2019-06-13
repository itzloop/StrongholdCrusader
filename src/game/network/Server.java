package game.network;

import com.google.gson.Gson;
import game.GV;
import game.comunication.RequestType;
import game.map.Map;
import game.player.Player;
import game.comunication.Request;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
    DatagramSocket socket;
    DatagramPacket packet = null;
    byte[] buffer = null;
    Thread requestListener = new Thread(() -> {
        while (true)
        { if(!requests.isEmpty()) handleRequest(requests.poll()); }
    });
    Thread server = new Thread(() -> {
        while (true)
        {
            try {
                packet = new DatagramPacket(buffer , buffer.length);
                socket.receive(packet);
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
        socket = new DatagramSocket(port);
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
            Request request = new Request(RequestType.ADD_SERVER , port + "");
            byte[] data = new Gson().toJson(request).getBytes();
            socket.send(new DatagramPacket(data , data.length , InetAddress.getLocalHost() , GV.serverHolderPort));
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
        System.out.println("socket started on port: " + port);
    }

    public BlockingQueue<Request> getRequests() {
        return requests;
    }


    public int getPort() {
        return port;
    }

    public static void main(String[] args) throws SocketException {
        Server socket = new Server(new Scanner(System.in).nextInt());
        socket.start();


    }
}
