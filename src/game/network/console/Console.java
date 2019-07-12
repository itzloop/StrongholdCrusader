package game.network.console;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

public class Console implements Runnable{

    private final Function<String ,Void>    behavior;
    private final Scanner                   scanner;
    private static boolean                  connected;

    public Console(Function<String ,Void> behavior )
    {
        connected = true;
        this.behavior = behavior;
        scanner = new Scanner(System.in);

    }

    @Override
    public void run() {
        System.out.println("console is ready...");
        String s;
        while ( isConnected())
        {
            s = scanner.nextLine();
            behavior.apply(s);
        }
        System.out.println("console is closed");
    }

    public static boolean isConnected() {
        return connected;
    }

    public static void setConnected(boolean connected) {
        Console.connected = connected;
    }
}
