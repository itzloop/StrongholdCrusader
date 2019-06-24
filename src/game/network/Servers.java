package game.network;

import com.google.gson.Gson;
import game.GV;
import game.comunication.Request;
import game.comunication.Respond;
import game.comunication.RespondType;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Servers implements requestHandler {
    private CopyOnWriteArrayList<Integer> ports;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private BlockingQueue<Respond> responds;
    private BlockingQueue<Request> requests;
    private byte[] buffer;

    Thread requestListener = new Thread(() -> {
        System.out.println("request listener has started...");
       while (true)
       {
           if(!requests.isEmpty())
           {
               handleRequest(requests.poll());
           }
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    });

    Thread respondListener = new Thread(() -> {
        System.out.println("respond listener has started...");
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


    Thread server = new Thread(() -> {
        System.out.println("server has Started...");
        while (true)
        {
            try {
                packet = new DatagramPacket(buffer , buffer.length);
                socket.receive(packet);
                parseRequest(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });


    public Servers() throws SocketException, UnknownHostException {
        buffer = new byte[GV.packetSize];
        ports = new CopyOnWriteArrayList<>();
        socket = new DatagramSocket(GV.serverHolderPort , GV.Ip);
        responds = new ArrayBlockingQueue<>(1000);
        requests = new ArrayBlockingQueue<>(1000);
        server.start();
        serverChecker.start();
        requestListener.start();
        respondListener.start();
    }

    public void parseRequest(DatagramPacket packet)
    {
        byte[] data = new byte[packet.getLength()];
        System.arraycopy(packet.getData() , 0 , data , 0 , packet.getLength());
        Request request = new Gson().fromJson(new String(data) , Request.class);
        requests.add(request);
    }

    public void handleRespond(Respond respond)
    {
        switch (respond.getRespondType())
        {
            case SEND_SERVER_LIST:
                byte[] data = new Gson().toJson(respond).getBytes();
                try {
                    socket.send(new DatagramPacket(data , data.length , respond.getDestIp() ,respond.getReceiverPort()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void handleRequest(Request request )
    {

        try {
            switch (request.getRequestType())
            {
                case ADD_SERVER:
                    ports.add(request.getSendersPort());
                    System.out.println("new Server Connected...");
                    break;
                case GET_SERVERS:
                    byte[] portList = new Gson().toJson(ports).getBytes();
                    Respond respond = new Respond(request.getSendersPort(),request.getDestIp(),RespondType.SEND_SERVER_LIST , new String(portList));
                    responds.add(respond);
                    break;
            }
        }catch (Exception e)
        {

        }
    }

    public List<Integer> getPorts() {
        return ports;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            Servers servers = new Servers();
            String command ;
            while (!(command = scanner.nextLine()).equals("stop"))
            {
                switch (command)
                {
                    case "list":
                        System.out.println(servers.getPorts());
                        break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}
