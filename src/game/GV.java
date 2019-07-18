package game;

import game.map.Tile;
import game.map.Vector2D;
import game.network.IP;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class GV {
    public static final int[][] castlePos= {
        {46,6}, //Top left
        {30,69},//top right
        {65,8}, //bottom left
        {55,89} //bottom right
    };
    public static       boolean      placed              =false;
    public static final int          port                = 15152;
    public static final int          initialSPCapacity   = 500;
    public static final int          initialGold         = 500;
    public static final int          initialFood         = 500;
    public static final int          initialPopulation   = 10;
    public static final int          initialPopularity   = 100;
    public static final int          stockPileCapacity   = 1000;
    public static final int          GranaryCapacity     = 1000;
    public static final int          HovelCapacity       = 5;
    public static final int          armoryCapacity      = 1000;
    public static final int          serverHolderPort    = 15151;
    public static final int          packetSize          = 65535;
    public static final int          maxPlayers          = 10000;
    public static final Vector2D     tileSize = new Vector2D(AssetManager.images.get("dust").getWidth(),AssetManager.images.get("dust").getHeight());
    public static final Vector2D     tileSizeMinimap = new Vector2D(0.7,2);
    public static       Vector2D     mousePosition = new Vector2D();
    public static       Vector2D     mapPos = new Vector2D();
    public static final int          requestListSize     = 100;
    public static final int          respondListSize     = 100;
    public static final double       scrollOffset        = 0.0035;
    public static final String       ipFormat            = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";

    public static       InetAddress  Ip;


    static {
        try {
            Ip = InetAddress.getByName(IP.getIP());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }





}
