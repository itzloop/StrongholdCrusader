package game.network;

import game.GV;
import game.player.Request;
import game.player.Respond;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Queue;

public class Server
{
    Queue<Request> requests;

    private final int port;
    DatagramSocket server;
    DatagramPacket packet = null;
    byte[] buffer = null;
    public Server(int port) throws SocketException
    {
        server = new DatagramSocket(port);
        buffer = new byte[GV.packetSize];
        this.port = port;
    }

    //TODO az shabke samte client req begir handle kon javabo bargardoon
    public void handle(DatagramPacket packet) throws InterruptedException {
        String string = new String(packet.getData());
        System.out.println(string);
    }

    public void listen() {
        new Thread(() -> {
            System.out.println("server started...");
           while (true)
           {
               packet = new DatagramPacket(buffer , buffer.length);
               try {
                   this.handle(packet);
               } catch (InterruptedException e) {
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
