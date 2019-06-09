package game.player;

import game.GV;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
    Player player;
    DatagramSocket socket;
    Client(String name)
    {
        try {
            socket = new DatagramSocket();
            player = Player.createPlayer(name);

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
    //TODO inja req o mifresti be server
    public void sendRequest(Request request) throws IOException {
        String reqStr;
        byte[] req;
        switch (request)
        {
            case ESTABLISHING_CONNECTION:
                reqStr = request.ordinal()+":"+player.getId()+":"+player.getName();
                req = reqStr.getBytes();
                socket.send(new DatagramPacket(req , req.length , InetAddress.getLocalHost() , GV.port));
                break;
        }
    }

    public static void main(String[] args) {
        Client client = new Client("jkhfkhs");
        try {
            client.sendRequest(Request.ESTABLISHING_CONNECTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
