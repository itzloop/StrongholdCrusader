package game.network;

import com.google.gson.Gson;
import game.GV;
import game.comunication.RequestType;
import game.comunication.Respond;
import game.comunication.RespondType;
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
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements requestHandler
{
    BlockingQueue<Request> requests;
    BlockingQueue<Respond> responds;
    private final static AtomicInteger portCounter = new AtomicInteger(15152);
    private Map map;
    private java.util.Map<Integer, Player> players;
    private final int port;
    DatagramSocket socket;
    DatagramPacket packet = null;
    byte[] buffer = null;
    Thread requestListener = new Thread(() -> {
        System.out.println("request listener has started...");
        while (true)
        { if(!requests.isEmpty()) handleRequest(requests.poll()); }
    });

    Thread respondListener = new Thread(() -> {
        System.out.println("respond listener has started...");
        while (true) { if(!responds.isEmpty()) handleRespond(responds.poll()); }
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

    //Constructor
    public Server()
    {
        //initialize the map
        map = new Map("sample Map" , 70 , 70);
        //initialize and start respond and request listeners
        requests = new ArrayBlockingQueue<>(1000);
        responds = new ArrayBlockingQueue<>(1000);
        requestListener.start();
        respondListener.start();

        //initialize the players list
        players = new HashMap<>();

        //assign an available port to this server
        portSetter();
        this.port = portCounter.getAndIncrement();

        //initialize the buffer byte-array with packet-size
        buffer = new byte[GV.packetSize];

    }
    //assign a new port to this server
    void portSetter()
    {
        while (true)
        {
            try {
                socket = new DatagramSocket(portCounter.get());
                break;
            } catch (SocketException e) {
                portCounter.getAndIncrement();
                continue;
            }
        }
    }
    public void start() {
        try {
            Request request = new Request(RequestType.ADD_SERVER , port + "");
            byte[] data = new Gson().toJson(request).getBytes();
            socket.send(new DatagramPacket(data , data.length , InetAddress.getLocalHost() , GV.serverHolderPort));
            server.start();
            System.out.println("socket started on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BlockingQueue<Request> getRequests() {
        return requests;
    }


    public int getPort() {
        return port;
    }


    @Override
    public void parseRequest(DatagramPacket packet) {
        byte[] data = new byte[packet.getLength()];
        System.arraycopy(packet.getData() , 0 , data , 0 , packet.getLength());
        String requestStr = new String(data);
        Request request = new Gson().fromJson(requestStr , Request.class);
        System.out.println(request.getBody());
        requests.add(request);
    }

    @Override
    public void handleRespond(Respond respond) {
        switch (respond.getRespondType())
        {
            case PLAYER_CREATED:
                buffer = new Gson().toJson(respond).getBytes();
                try {
                    socket.send(new DatagramPacket(buffer , buffer.length , InetAddress.getLocalHost() , respond.getReceiverPort()));
                    System.out.println("Respond has been sent");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void handleRequest(Request request) {
        switch (request.getRequestType()) {
            case ESTABLISHING_CONNECTION:
                Player p = new Gson().fromJson(request.getBody(), Player.class);
                if (!players.containsKey(p.getId())) {
                    players.put(p.getId(), p);
                    System.out.println(players);
                    Respond respond = new Respond(RespondType.PLAYER_CREATED, new Gson().toJson(map), p.getPort());
                    responds.add(respond);
                    break;
                }
        }


    }

    public static void main(String[] args) throws SocketException {
        Server socket = new Server();
        socket.start();


    }
}
