package game;

import javafx.stage.Screen;

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
    public static final String       ipFormat            = "(0[0-9]{2}|1[0-9]{2}|2[0-5]{2})\\.(0[0-9]{2}|1[0-9]{2}|2[0-5]{2})\\.(0[0-9]{2}|1[0-9]{2}|2[0-5]{2})\\.(0[0-9]{2}|1[0-9]{2}|2[0-5]{2})";
    public static final int          tileWidth           = 60;
    public static final int          tileHeight          = 60;
    public static final double       scrollOffset        = 0.03;
    public static       InetAddress  Ip;

    static {
        try {
            Ip = InetAddress.getByName("192.168.1.178");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }





}
