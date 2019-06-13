package game.network;

import com.google.gson.Gson;
import game.GV;
import game.comunication.Request;
import game.comunication.Respond;
import game.comunication.RespondType;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Servers implements requestHandler {
    private List<Integer> ports;
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
        }
    });


    public Servers() throws SocketException {
        buffer = new byte[GV.packetSize];
        ports = new ArrayList<>();
        socket = new DatagramSocket(GV.serverHolderPort);
        responds = new ArrayBlockingQueue<>(1000);
        requests = new ArrayBlockingQueue<>(1000);
        server.start();
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
            case SERVER_LIST_SENT:
                byte[] data = new Gson().toJson(respond).getBytes();
                try {
                    socket.send(new DatagramPacket(data , data.length , InetAddress.getLocalHost() ,respond.getReceiverPort()));
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
                    ports.add(Integer.parseInt(request.getBody()));
                    break;
                case GET_SERVERS:
                    byte[] portList = new Gson().toJson(ports).getBytes();
                    int destPort = Integer.parseInt(request.getBody());
                    Respond respond = new Respond(RespondType.SERVER_LIST_SENT , new String(portList),destPort);
                    responds.add(respond);
                    break;
            }
        }catch (Exception e)
        {

        }
        System.out.println(ports);
    }



    public static void main(String[] args) {

        try {
            Servers servers = new Servers();
        } catch (SocketException e) {
            e.printStackTrace();
        }


    }
}
