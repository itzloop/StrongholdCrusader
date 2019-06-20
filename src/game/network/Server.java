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
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements requestHandler
{
    private final static AtomicInteger playerId = new AtomicInteger(0);
    private final static AtomicInteger portCounter = new AtomicInteger(15152);
    private int                        port;
    private Map                        map;
    private List<Player>               players;
    private DatagramSocket             socket;
    private DatagramPacket             packet = null;
    private BlockingQueue<Request>     requests;
    private BlockingQueue<Respond>     responds;
    private byte[]                     buffer = null;


    Thread requestListener = new Thread(() -> {
        System.out.println("request listener has started...");
        while (true)
        { if(!requests.isEmpty())
            handleRequest(requests.poll());
            else {
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
    });

    Thread respondListener = new Thread(() -> {
        System.out.println("respond listener has started...");
        while (true) {
            if(!responds.isEmpty())
                handleRespond(responds.poll());
            else {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    Thread server = new Thread(() -> {
        try {
            //sending the request for adding this server to the list
            Request request = new Request(port,GV.Ip, RequestType.ADD_SERVER , "add me to the list");
            byte[] data = new Gson().toJson(request).getBytes();
            socket.send(new DatagramPacket(data , data.length , InetAddress.getLocalHost() , GV.serverHolderPort));
            System.out.println("socket started on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        players = new ArrayList<>();

        //assign an available port to this server
        portSetter();
        this.port = portCounter.getAndIncrement();

        //initialize the buffer byte-array with packet-size
        buffer = new byte[GV.packetSize];

        server.start();

    }
    //assign a new port to this server
    private void portSetter()
    {
        while (true)
        {
            try {
                socket = new DatagramSocket(portCounter.get() , InetAddress.getLocalHost());
                break;
            } catch (SocketException e) {
                portCounter.getAndIncrement();
                continue;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
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
        Respond respond;

        switch (request.getRequestType()) {
            case CONNECT_TO_SERVER:
                Player p = new Gson().fromJson(request.getBody(), Player.class);
                if (playerId.get() < 4) {
                    p.setId(playerId.getAndIncrement());
                    players.add(p);
                    System.out.println(players);
                    respond = new Respond(p.getId(),p.getPort(),request.getSendersIp(),RespondType.PLAYER_CREATED, new Gson().toJson(map));
                    responds.add(respond);
                    break;
                } else {
                    respond = new Respond(p.getPort(),request.getSendersIp(),RespondType.MAX_PLAYERS_REACHED , "Max Players reached");
                    responds.add(respond);
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

    public String getIp() throws UnknownHostException {
        return socket.getInetAddress().getLocalHost().getHostAddress() + ":" + port;
    }

    public static void main(String[] args) throws SocketException {
        Server server = new Server();
        Scanner scanner = new Scanner(System.in);
        String command;
        while (!(command = scanner.nextLine()).equals("stop")) {
            switch (command) {
                case "players":
                    System.out.println(server.getPlayers());
                    break;
                case "ip":
                    try {
                        System.out.println(server.getIp());
                    }
                    catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }

    public BlockingQueue<Request> getRequests() {
        return requests;
    }


    public int getPort() {
        return port;
    }
}
