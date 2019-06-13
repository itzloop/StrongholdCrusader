package game.comunication;

import java.util.Arrays;
import java.util.Optional;

public enum  RespondType {
    PLAYER_CREATED(0),SERVER_LIST_SENT(1);
    private final int resNum;

    RespondType(int resNum)
    {
        this.resNum = resNum;
    }
    public static Optional<RespondType> valueOf(int respond)
    {
        return Arrays.stream(values()).filter(res -> res.resNum == respond).findFirst();
    }
}
