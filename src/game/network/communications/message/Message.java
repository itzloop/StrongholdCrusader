package game.network.communications.message;

import java.net.InetAddress;

public class Message {
    private int         id;
    private int port;
    private InetAddress destIp;
    private String      message;

    public Message(int id, int port, InetAddress destIp, String message) {
        this.id = id;
        this.port = port;
        this.destIp = destIp;
        this.message = message;
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

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getDestIp() {
        return destIp;
    }

    public void setDestIp(InetAddress destIp) {
        this.destIp = destIp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
