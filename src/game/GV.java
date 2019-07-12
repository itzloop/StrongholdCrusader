package game;

import game.map.Vector2D;
import game.network.IP;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GV {
//
//    public static final double       screenWidth         = Screen.getPrimary().getBounds().getWidth();
//    public static final double       screenHeight        = Screen.getPrimary().getBounds().getHeight();
    public static final int          port                = 15152;
    public static final int          serverHolderPort    = 15151;
    public static final int          packetSize          = 65535;
    public static final int          maxPlayers          = 10000;
    public static final Vector2D     tileSize = new Vector2D(AssetManager.assets.get("dust").getWidth(),AssetManager.assets.get("dust").getHeight());
    public static final int          requestListSize     = 100;
    public static final int          respondListSize     = 100;
    public static final double       scrollOffset        = 0.003;
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
