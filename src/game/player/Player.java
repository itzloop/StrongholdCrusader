package game.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import game.GV;
import game.comunication.Request;
import game.comunication.RequestType;
import game.comunication.Respond;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.*;
import game.network.requestHandler;
import game.map.Map;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
public class Player implements requestHandler
{


    private int id;
    private int port;
    private final static AtomicInteger portCounter = new AtomicInteger(15152);
    private String name;
    private transient Map map;
    private transient DatagramSocket socket;
    private transient BlockingQueue<Respond>  responds;
    private transient Optional<List<Integer>> availableServers;
    private transient boolean hasMap = false;
    private transient InetAddress localHost;

    private final transient Thread respondListener = new Thread(() -> {
       while (true)
       {
           if(!responds.isEmpty())
           {
               handleRespond(responds.poll());
           }
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    });

    //this will receive all the responds and add them for handling
    private final transient Thread respondHandler = new Thread(() -> {
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
               responds.add(respond);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    });


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
        responds = new ArrayBlockingQueue<>(1000);
        respondListener.start();
        respondHandler.start();
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





    @Override
    public void handleRespond(Respond respond)
    {
        switch (respond.getRespondType())
        {
            case SEND_SERVER_LIST:
                Type type = new TypeToken<List<Integer>>(){}.getType();
                List<Integer> ports = new Gson().fromJson(respond.getBody() , type);
                availableServers = Optional.ofNullable(ports);
                break;
            case PLAYER_CREATED:
                this.setId(respond.getReceiverId());
                System.out.println(this);
                Gson gson = new Gson();
                map = gson.fromJson(respond.getBody() , Map.class);
                map.loadMap(map.getTilesNumber());
                System.out.println(name + "  "+this.getMap());
                hasMap = true;
                break;
            case MAX_PLAYERS_REACHED:
                System.out.println("Max Players Reached");
                break;
        }
    }

    @Override
    public void handleRequest(Request request) {
        try {
            byte[] requestByte;
            switch (request.getRequestType())
            {
                case CONNECT_TO_SERVER:
                    Request req = new Request(getId() , getPort(),localHost,RequestType.CONNECT_TO_SERVER, new Gson().toJson(this));
                    requestByte =new Gson().toJson(req).getBytes();
                    socket.send(new DatagramPacket(requestByte , requestByte.length , GV.Ip ,Integer.parseInt(request.getBody() )));
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
    }

    public Optional<List<Integer>> serverList()
    {
        System.out.println("serverList: "+ availableServers);
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

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " port: " + port;
    }

}
