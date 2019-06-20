package game.comunication;


import java.net.InetAddress;

public class Request
{
    private int         sendersId;
    private int         sendersPort;
    private InetAddress sendersIp;
    private RequestType requestType;
    private String      body;

    public Request(int sendersId , int sendersPort, InetAddress sendersIp, RequestType requestType, String body ) {
        this.sendersId      = sendersId;
        this.sendersPort    = sendersPort;
        this.sendersIp = sendersIp;
        this.requestType    = requestType;
        this.body           = body;
    }

    public Request(int sendersPort, InetAddress sendersIp, RequestType requestType, String body ){
        this(-1 , sendersPort , sendersIp, requestType , body);
    }


    public RequestType getRequestType() {
        return requestType;
    }

    public String getBody() {
        return body;
    }

    public int getSendersId() { return sendersId; }

    public void setSendersId(int sendersId) { this.sendersId = sendersId; }

    public int getSendersPort() { return sendersPort; }

    public void setSendersPort(int sendersPort) { this.sendersPort = sendersPort; }

    public void setRequestType(RequestType requestType) { this.requestType = requestType; }

    public void setBody(String body) { this.body = body; }

    public InetAddress getSendersIp() { return sendersIp; }

    public void setSendersIp(InetAddress sendersIp) { this.sendersIp = sendersIp; }

    @Override
    public String toString() {
        return requestType +" " + body;
    }
}
