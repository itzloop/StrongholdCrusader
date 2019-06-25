package game.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IP {
    private static String ip;
    private static String gateway;
    private static StringBuilder IPV4_LIST = new StringBuilder();





    public static String getIP()
    {
        if(System.getProperties().getProperty("os.name").toLowerCase().contains("windows"))
            parseWindows();
        else
            parseLinux();
        return ip;
    }



    private static void parseLinux() {
        //TODO implemet this later
    }

    private static void parseWindows()
    {
        try
        {
            Process pro = Runtime.getRuntime().exec("cmd.exe /c route print -4");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line;

            boolean start = false;
            while((line = bufferedReader.readLine())!=null)
            {
                IPV4_LIST.append(line+"\n");
            }
            Scanner scanner = new Scanner(IPV4_LIST.toString());
            while (scanner.hasNext())
            {
                if(scanner.next().equals("0.0.0.0"))
                {
                    if(scanner.next().equals("0.0.0.0"))
                    {
                        gateway = scanner.next();
                        ip=scanner.next();
                        break;
                    }
                }
            }
            pro.destroy();
        }
        catch(IOException e)
        {
            System.err.println(e);
            e.printStackTrace();
        }
    }

}



