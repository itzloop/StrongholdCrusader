package game.network.console;

import java.util.Scanner;
import java.util.function.Function;

public class Console implements Runnable{

    private final Function<String ,Void>    behavior;
    private final Scanner                   scanner;

    public Console(Function<String ,Void> behavior )
    {
        this.behavior = behavior;
        scanner = new Scanner(System.in);

    }

    @Override
    public void run() {
        System.out.println("console is ready...");
        String s;
        while (!(s = scanner.nextLine()).equals("console.close"))
        {
            behavior.apply(s);
        }
        System.out.println("console is closed restart the server to open it again");
    }
}
