package game.player;

import java.util.Arrays;
import java.util.Optional;

public enum Request
{
    ESTABLISHING_CONNECTION(0) , MOVE_UNIT(1);
    private final int reqNum;

    Request(int reqNum)
    {
        this.reqNum = reqNum;
    }
    public static Optional<Request> valueOf(int request){
        return Arrays.stream(values()).filter(req -> req.reqNum == request).findFirst();
    }
}
