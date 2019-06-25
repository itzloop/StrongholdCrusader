package game.network.console;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

public class Console implements Runnable{

    private final Function<String ,Void>    behavior;
    private final Scanner                   scanner;
    private final BlockingQueue<String>     messages;

    public Console(Function<String ,Void> behavior )
    {
        this.behavior = behavior;
        scanner = new Scanner(System.in);
        messages = new ArrayBlockingQueue<>(100);

    }

    @Override
    public void run() {
        System.out.println("console is ready...");
        String s;
        while (!(s = scanner.nextLine()).equals("console.close"))
        {
            behavior.apply(s);
            if(!messages.isEmpty())
                System.out.println(messages.poll());

        }
        System.out.println("console is closed restart the server to open it again");
    }

    public void log(String message)
    {
        messages.add(message);
    }

}
