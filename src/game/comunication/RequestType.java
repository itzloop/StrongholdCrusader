package game.comunication;

import java.util.Arrays;
import java.util.Optional;

public enum  RequestType {
    ADD_SERVER(0),GET_SERVERS(1),ESTABLISHING_CONNECTION(2) , MOVE_UNIT(3);
    private final int reqNum;

    RequestType(int reqNum)
    {
        this.reqNum = reqNum;
    }
    public static Optional<RequestType> valueOf(int request){
        return Arrays.stream(values()).filter(req -> req.reqNum == request).findFirst();
    }
}

