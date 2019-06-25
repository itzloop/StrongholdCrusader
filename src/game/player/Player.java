package game.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import game.GV;
import game.network.communications.Communication;
import game.network.communications.Handler;
import game.network.communications.message.Request;
import game.network.communications.message.RequestType;
import game.network.communications.message.Respond;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.*;
import game.network.console.Console;
import game.map.Map;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class Player
{


    private int                                     id;
    private int                                     port;
    private String                                  name;
    private transient Map                           map;
    private transient DatagramSocket                socket;
    private transient Optional<List<Integer>>       availableServers;
    private transient boolean                       hasMap              = false;
    private transient InetAddress                   localHost;
    private transient Communication                 communication;
    private transient final static AtomicInteger    portCounter         = new AtomicInteger(15152);




    public Player(String name , int id , int port)
    {
        if(port == -1)
            this.port = getAvailablePorts();
        else
            this.port = port;
        this.name = name;
        this.id = id;
        map = new Map();
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        availableServers = Optional.empty();
        //initialize and start respond and request comunicationListener
        Handler requestHandler = (message) -> {
            Request request = (Request)message;
            try {
                byte[] requestByte;
                switch (request.getRequestType())
                {
                    case CONNECT_TO_SERVER:
                        Request req = new Request(getId() , getPort(),localHost,RequestType.CONNECT_TO_SERVER, new Gson().toJson(this));
                        requestByte =new Gson().toJson(req).getBytes();
                        socket.send(new DatagramPacket(requestByte , requestByte.length , GV.Ip ,Integer.parseInt(request.getMessage() )));
                        break;
                    case GET_SERVERS:
                        requestByte = new Gson().toJson(request).getBytes();
                        socket.send(new DatagramPacket(requestByte , requestByte.length , GV.Ip , GV.serverHolderPort));
                        break;
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        };
        Handler respondHandler = (message) -> {
            Respond respond = (Respond)message;
            switch (respond.getRespondType())
            {
                case SEND_SERVER_LIST:
                    Type type = new TypeToken<List<Integer>>(){}.getType();
                    List<Integer> ports = new Gson().fromJson(respond.getMessage() , type);
                    availableServers = Optional.ofNullable(ports);
                    break;
                case PLAYER_CREATED:
                    this.setId(respond.getId());
                    System.out.println(this);
                    Gson gson = new Gson();
                    map = gson.fromJson(respond.getMessage() , Map.class);
                    map.loadMap(map.getTilesNumber());
                    hasMap = true;
                    break;
                case MAX_PLAYERS_REACHED:
                    System.out.println("Max Players Reached");
                    break;
            }
            return null;
        };
        communication = new Communication(requestHandler , respondHandler);


        //initializing the Console for this server
        Function<String , Void> consoleBehavior = (command) -> {
            switch (command)
            {
                case "ip":
                    System.out.println(localHost.getHostAddress());
                    System.out.println(localHost.getHostName());
                    break;
            }
            return null;
        };
        new Thread(new Console(consoleBehavior)).start();


        //TODO think of a better way to implement this
        //start listening for responds
        new Thread(() -> start()).start();

    }

    private void start() {
        DatagramPacket packet;
        byte[] get = new byte[GV.packetSize];
        while (true)
        {
            packet = new DatagramPacket(get, get.length);
            try {
                socket.receive(packet);
                byte[] data = new byte[packet.getLength()];
                System.arraycopy(packet.getData() , 0, data , 0 , packet.getLength());
                Respond respond = new Gson().fromJson(new String(data) , Respond.class);
                getCommunication().communicate(respond);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Player(String name) {
        this(name , -1 , -1);
    }



    private int getAvailablePorts()
    {
        while (true) {
            try {
                this.socket = new DatagramSocket(portCounter.get() , localHost);

                return portCounter.getAndIncrement();
            } catch (SocketException e) {
                portCounter.getAndIncrement();
                continue;
            }
        }
    }




    public Optional<List<Integer>> serverList()
    {
        if(availableServers.isPresent())
        {
            return availableServers;
        }
        else
            return Optional.empty();
    }


    public boolean hasMap() {
        return hasMap;
    }

    public void setHasMap(boolean hasMap) {
        this.hasMap = hasMap;
    }

    private String sendPlayer()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    public Map getMap() {
        return map;
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

    public int getPort() {
        return port;
    }

    public InetAddress getLocalHost() {
        return localHost;
    }

    public void setLocalHost(InetAddress localHost) {
        this.localHost = localHost;
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " port: " + port;
    }

}
