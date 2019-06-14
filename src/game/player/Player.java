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
import game.ui.Menu;
import javafx.scene.Scene;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
public class Player implements requestHandler
{


    private int id;
    private int port;
    private final static AtomicInteger idCounter= new AtomicInteger(0);
    private final static AtomicInteger portCounter = new AtomicInteger(15152);
    private String name;
    private transient Map map;
    private transient DatagramSocket socket;
    private transient BlockingQueue<Respond>  responds;
    private transient Optional<List<Integer>> servers;
    private final transient Thread respondListener = new Thread(() -> {
       while (true)
       {
           if(!responds.isEmpty())
           {
               handleRespond(responds.poll());
           }
       }
    });
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


    private Player(String name) {
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
        servers = Optional.empty();
        responds = new ArrayBlockingQueue<>(1000);
        respondListener.start();
        respondHandler.start();
    }

    private Player(String name , int id , int port)
    {
        this.port = port;
        this.name = name;
        this.id = id;
    }

    //TODO fix this so we can have more than one game at a time i think im wrong but anyways :)
    public static Optional<Player> createPlayer(String name)  {
        if(idCounter.get() < GV.maxPlayers)
            return Optional.ofNullable(new Player(name));
        return Optional.empty();
    }



    @Override
    public void handleRespond(Respond respond)
    {
        switch (respond.getRespondType())
        {
            case SERVER_LIST_SENT:
                Type type = new TypeToken<List<Integer>>(){}.getType();
                List<Integer> ports = new Gson().fromJson(respond.getBody() , type);
                servers = Optional.ofNullable(ports);
                break;
            case PLAYER_CREATED:
                map = new Gson().fromJson(respond.getBody() , Map.class);
                //map.initializeTiles(map.getTilesNumber());
                break;
        }
    }

    @Override
    public void handleRequest(Request request) {
        try {
            byte[] requestByte;
            switch (request.getRequestType())
            {
                case ESTABLISHING_CONNECTION:
                    Request req = new Request(RequestType.ESTABLISHING_CONNECTION , new Gson().toJson(this));
                    requestByte =new Gson().toJson(req).getBytes();
                    socket.send(new DatagramPacket(requestByte , requestByte.length , InetAddress.getLocalHost() ,Integer.parseInt(request.getBody() )));
                    break;
                case GET_SERVERS:
                    requestByte = new Gson().toJson(request).getBytes();
                    socket.send(new DatagramPacket(requestByte , requestByte.length , InetAddress.getLocalHost() , GV.serverHolderPort));
                    break;
            }
        }catch (Exception e)
        {

        }
    }

    public Optional<List<Integer>> serverList()
    {
        System.out.println("serverList: "+ servers);
        if(servers.isPresent())
        {
            return servers;
        }
        else
            return Optional.empty();
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
