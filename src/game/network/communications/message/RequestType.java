package game.network.communications.message;

import java.util.Arrays;
import java.util.Optional;

public enum  RequestType {
    //request for adding server to server list in servers class.
    ADD_SERVER(0),
    //request for getting all the servers available.
    GET_SERVERS(1),
    //a player have to sent this type of request if he wishes to connect to servers.
    CONNECT_TO_SERVER(2);
    private final int reqNum;

    RequestType(int reqNum)
    {
        this.reqNum = reqNum;
    }
    public static Optional<RequestType> valueOf(int request){
        return Arrays.stream(values()).filter(req -> req.reqNum == request).findFirst();
    }
}

