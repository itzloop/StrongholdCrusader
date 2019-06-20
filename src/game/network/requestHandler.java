package game.network;

import game.comunication.Request;
import game.comunication.Respond;

import java.net.DatagramPacket;

public interface requestHandler {
    void handleRespond(Respond respond);
    void handleRequest(Request request);
    default void parseRequest(DatagramPacket packet){}

}
