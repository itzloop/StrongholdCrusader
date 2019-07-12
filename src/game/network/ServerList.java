package game.network;

import com.google.gson.Gson;
import game.GV;
import game.network.communications.Communication;
import game.network.communications.Handler;
import game.network.communications.message.Request;
import game.network.communications.message.Respond;
import game.network.communications.message.RespondType;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class ServerList  {
    private CopyOnWriteArrayList<Integer>   ports;
    private DatagramSocket                  socket;
    private DatagramPacket                  packet;
    private Communication                   communication;
    private byte[]                          buffer;




    Thread serverChecker = new Thread(() -> {
        System.out.println("serverChecker started...");
        List<Integer> portsTobeRemoved = new ArrayList<>();
        while (true)
        {
            for (int port : ports)
            {
                try {
                    DatagramSocket s = new DatagramSocket(port);
                    portsTobeRemoved.add(port);
                    System.out.println("Sever on port " + port + " disconnected");
                    s.close();
                } catch (SocketException e) {
                }
            }
            ports.removeAll(portsTobeRemoved);
            portsTobeRemoved = new ArrayList<>();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });




    public ServerList() throws SocketException {
        buffer = new byte[GV.packetSize];
        ports = new CopyOnWriteArrayList<>();
        socket = new DatagramSocket(GV.serverHolderPort , GV.Ip);


        //initialize and start respond and request comunicationListener
        Handler requestHandler = (message) -> {
            Request request = (Request)message;
            switch (request.getRequestType())
            {
                case ADD_SERVER:
                    ports.add(request.getPort());
                    System.out.println("new Server started on port " + request.getPort());
                    break;
                case GET_SERVERS:
                    byte[] portList = new Gson().toJson(ports).getBytes();
                    Respond respond = new Respond(request.getPort(),request.getDestIp(),RespondType.SEND_SERVER_LIST , new String(portList));
                    return respond;
                case DISCONNECT_ME:
                    List<Integer> portsTobeRemoved = new ArrayList<>();
                    for (int port : ports)
                    {
                        if(port == request.getPort())
                            portsTobeRemoved.add(port);
                        System.out.println("Sever on port " + port + " disconnected");
                    }
                    ports.removeAll(portsTobeRemoved);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return null;
        };
        Handler respondHandler = (message) -> {
            Respond respond = (Respond)message;
            switch (respond.getRespondType())
            {
                case SEND_SERVER_LIST:
                    byte[] data = new Gson().toJson(respond).getBytes();
                    try {
                        socket.send(new DatagramPacket(data , data.length , respond.getDestIp() ,respond.getPort()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return null;
        };
        communication = new Communication(requestHandler , respondHandler);


        //initializing the Console for this server
        Function<String , Void> consoleBehavior = (command) -> {
            switch (command)
            {
                case "servers":
                    System.out.println(this.getPorts());
                    break;
            }
            return null;
        };
        new Thread(new game.network.console.Console(consoleBehavior)).start();

        //start servers status checker
        serverChecker.start();

        //Start the server
        start();

    }

    private void start() {
        System.out.println("serverListener has Started...");
        while (true)
        {
            try {
                packet = new DatagramPacket(buffer , buffer.length);
                socket.receive(packet);
                communication.getParser().parse(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public List<Integer> getPorts() {
        return ports;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            ServerList serverList = new ServerList();
            String command ;
            while (!(command = scanner.nextLine()).equals("stop"))
            {
                switch (command)
                {
                    case "servers":
                        System.out.println(serverList.getPorts());
                        break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }


    }
}
