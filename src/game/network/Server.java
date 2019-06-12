package game.network;

import game.GV;
import game.player.Player;
import game.player.Request;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Server
{
    Queue<Request> requests;
    private List<Player> players;
    private final int port;
    DatagramSocket server;
    DatagramPacket packet = null;
    byte[] buffer = null;
    public Server(int port) throws SocketException
    {
        players = new LinkedList<>();
        server = new DatagramSocket(port);
        buffer = new byte[GV.packetSize];
        this.port = port;
    }

    //TODO az shabke samte client req begir handle kon javabo bargardoon
    public void handle(DatagramPacket packet)  {
        new Thread(() -> {
            String string = new String(packet.getData());
            System.out.println(string);
            String[] reqPart = string.split(":");
//            switch (Request.valueOf(Integer.parseInt(reqPart[0])))
//            {
//                    break;
//            }
        }).start();
    }

    public void listen() {
        new Thread(() -> {
            System.out.println("server started on port: " + port);
           while (true)
           {
               packet = new DatagramPacket(buffer , buffer.length);
               try {
                   server.receive(packet);
                   System.out.println("packet received");
                    handle(packet);
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
        }).start();
    }

    public static void main(String[] args) throws SocketException {
        Server server = new Server(GV.port);
        server.listen();

    }
    public int getPort() {
        return port;
    }
}
