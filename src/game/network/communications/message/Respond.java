package game.network.communications.message;


import java.net.InetAddress;

public class Respond extends Message{

    private RespondType respondType;
    public Respond(int receiverId, int receiverPort , InetAddress destIp, RespondType respondType, String message) {
        super(receiverId , receiverPort, destIp, message);
        this.respondType    = respondType;
    }

    public Respond(int receiverPort, InetAddress destIp, RespondType respondType, String message) {
        this(-1,receiverPort, destIp,respondType , message);
    }

    public RespondType getRespondType() {
        return respondType;
    }


    public void setRespondType(RespondType respondType) { this.respondType = respondType; }


    @Override
    public String toString() {
        return getDestIp().getHostName()+"/" + getDestIp().getHostAddress() + "  "+respondType +" " + getMessage();
    }
}
