package game.player;

import java.util.Arrays;
import java.util.Optional;

public enum Respond {
    PLAYER_CREATED(0);
    private final int resNum;

    Respond(int resNum)
    {
        this.resNum = resNum;
    }
    public static Optional<Respond> valueOf(int respond)
    {
        return Arrays.stream(values()).filter(res -> res.resNum == respond).findFirst();
    }

}
