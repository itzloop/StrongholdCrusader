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
    private transient DatagramSocket                server;
    private transient Optional<List<Integer>>       availableServers;
    private transient boolean                       hasMap              = false;
    private transient Communication                 communication;
    private transient final static AtomicInteger    portCounter         = new AtomicInteger(15152);
    private transient InetAddress                   serverIp;
    private transient Console                       console;
    private transient Thread                        connect;



    public Player(String name , int id , int port)
    {
        if(port == -1)
            this.port = getAvailablePorts();
        else
            this.port = port;
        this.name = name;
        this.id = id;
        map = new Map();
        availableServers = Optional.empty();
        //initialize and start respond and request communicationListener
        Handler requestHandler = (message) -> {
            Request request = (Request)message;
            try {
                byte[] requestByte;
                switch (request.getRequestType())
                {
                    case CONNECT_TO_SERVER:
                        Request req = new Request(getId() , getPort(),serverIp,RequestType.CONNECT_TO_SERVER, new Gson().toJson(this));
                        requestByte =new Gson().toJson(req).getBytes();
                        server.send(new DatagramPacket(requestByte , requestByte.length , serverIp ,Integer.parseInt(request.getMessage() )));
                        break;
                    case GET_SERVERS:
                        requestByte = new Gson().toJson(request).getBytes();
                        server.send(new DatagramPacket(requestByte , requestByte.length ,serverIp, GV.serverHolderPort));
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
                    console.log(map.toString());
                    Gson gson = new Gson();
                    map = gson.fromJson(respond.getMessage() , Map.class);
                    map.loadMap(map.getTilesNumber());
                    hasMap = true;
                    break;
                case MAX_PLAYERS_REACHED:
                    console.log("Server is full");
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
                    System.out.println(serverIp.getHostAddress());
                    System.out.println(serverIp.getHostName());
                    break;
            }
            return null;
        };
        console = new Console(consoleBehavior);
        new Thread(console).start();

        //start listening for responds
        connect = new Thread(() -> {
            DatagramPacket packet;
            byte[] get = new byte[GV.packetSize];
            while (true)
            {
                packet = new DatagramPacket(get, get.length);
                try {
                    server.receive(packet);
                    byte[] data = new byte[packet.getLength()];
                    System.arraycopy(packet.getData() , 0, data , 0 , packet.getLength());
                    Respond respond = new Gson().fromJson(new String(data) , Respond.class);
                    Communication().communicate(respond);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        connect.start();
    }


    public Player(String name) {
        this(name , -1 , -1);
    }



    private int getAvailablePorts()
    {
        while (true) {
            try {
                this.server = new DatagramSocket(portCounter.get() , serverIp);

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


    public Communication Communication() {
        return communication;
    }

    public InetAddress getServerIp() {
        return serverIp;
    }

    public void setServerIp(InetAddress serverIp) {
        this.serverIp = serverIp;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " port: " + port;
    }

}
