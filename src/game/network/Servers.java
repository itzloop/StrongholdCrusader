package game.network;

import com.google.gson.Gson;
import game.GV;
import game.player.Request;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Servers {
    private List<Integer> ports;
    private DatagramSocket socket;
    private DatagramPacket packet;

    public Servers() throws SocketException {
        ports = new ArrayList<>();
        socket = new DatagramSocket(GV.serverHolderPort);
    }

    public void addServer(int port)
    {
        ports.add(port);
    }

    public List<Integer> getPorts() {
        return ports;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    public void setPacket(DatagramPacket packet) {
        this.packet = packet;
    }

    public static void main(String[] args) {
        try {
            Servers servers = new Servers();
            byte[] buffer= new byte[GV.packetSize];
            while (true)
            {
                servers.packet = new DatagramPacket(buffer , buffer.length);
                servers.socket.receive(servers.packet);
                Gson gson = new Gson();
                try {
                    Request request = gson.fromJson(new String(servers.packet.getData()) , Request.class);
                    switch (request.getRequestType())
                    {
                        case GET_SERVERS:
                            byte[] svList = gson.toJson(servers.getPorts()).getBytes();
                            servers.socket.send(new DatagramPacket(svList , svList.length , InetAddress.getLocalHost() , servers.packet.getPort()));
                            break;
                    }
                }
                catch (Exception e)
                {
                    servers.ports.add(servers.packet.getPort());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
