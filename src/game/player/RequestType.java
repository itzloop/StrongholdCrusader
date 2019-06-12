package game.player;

import java.util.Arrays;
import java.util.Optional;

public enum  RequestType {
    GET_SERVERS(0),ESTABLISHING_CONNECTION(1) , MOVE_UNIT(2);
    private final int reqNum;

    RequestType(int reqNum)
    {
        this.reqNum = reqNum;
    }
    public static Optional<RequestType> valueOf(int request){
        return Arrays.stream(values()).filter(req -> req.reqNum == request).findFirst();
    }
}

