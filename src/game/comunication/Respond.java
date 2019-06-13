package game.comunication;


public class Respond {

    private RespondType respondType;
    private String body;
    private int receiverPort;
    public Respond(RespondType respondType, String body , int receiverPort) {
        this.respondType = respondType;
        this.body = body;
        this.receiverPort = receiverPort;
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

    @Override
    public String toString() {
        return respondType +" " + body;
    }
}
