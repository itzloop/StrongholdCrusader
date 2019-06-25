package game.network.communications.message;


import java.net.InetAddress;

public class Request extends Message
{
    private RequestType requestType;

    public Request(int sendersId , int sendersPort, InetAddress destIp, RequestType requestType, String body ) {

        super(sendersId , sendersPort, destIp, body);
        this.requestType    = requestType;
    }

    public Request(int sendersPort, InetAddress destIp, RequestType requestType, String body ){
        this(-1 , sendersPort , destIp, requestType , body);
    }


    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) { this.requestType = requestType; }


    @Override
    public String toString() {
        return getDestIp().getHostName()+"/" + getDestIp().getHostAddress() + "  "+requestType +" " + getMessage();
    }
}
