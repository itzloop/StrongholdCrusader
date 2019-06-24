package game.comunication;


import java.net.InetAddress;

public class Respond {

    private int         receiverId;
    private int         receiverPort;
    private InetAddress destIp;
    private RespondType respondType;
    private String      body;
    public Respond(int receiverId, int receiverPort , InetAddress destIp, RespondType respondType, String body) {
        this.receiverId     = receiverId;
        this.receiverPort   = receiverPort;
        this.destIp = destIp;
        this.respondType    = respondType;
        this.body           = body;
    }

    public Respond(int receiverPort, InetAddress destIp, RespondType respondType, String body) {
        this(-1,receiverPort, destIp,respondType , body);
    }

    public RespondType getRespondType() {
        return respondType;
    }

    public String getBody() {
        return body;
    }

    public int getReceiverPort() {
        return receiverPort;
    }

    public int getReceiverId() { return receiverId; }

    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }

    public void setReceiverPort(int receiverPort) { this.receiverPort = receiverPort; }

    public void setRespondType(RespondType respondType) { this.respondType = respondType; }

    public void setBody(String body) { this.body = body; }

    public InetAddress getDestIp() { return destIp; }

    public void setDestIp(InetAddress destIp) { this.destIp = destIp; }

    @Override
    public String toString() {
        return respondType +" " + body;
    }
}
