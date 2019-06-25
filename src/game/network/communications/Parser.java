package game.network.communications;

import java.net.DatagramPacket;

public interface Parser {
    void parse(DatagramPacket packet);
}
