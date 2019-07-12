package game.network.communications.message;

import java.util.Arrays;
import java.util.Optional;

public enum  RespondType {
    //this respond is sent to the player when the player is successfully connected.
    PLAYER_CREATED(0),
    //this respond is sent to the player when he requests for the available serves.
    SEND_SERVER_LIST(1),
    //this respond is sent when a players wants to connect to this server but its full.
    MAX_PLAYERS_REACHED(2);
    private final int resNum;

    RespondType(int resNum) { this.resNum = resNum; }
    public static Optional<RespondType> valueOf(int respond)
    {
        return Arrays.stream(values()).filter(res -> res.resNum == respond).findFirst();
    }
}
