package game.network.communications;

import com.google.gson.Gson;
import game.network.communications.message.Message;
import game.network.communications.message.Request;
import game.network.communications.message.Respond;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Communication {
    private Thread                      requestListener;
    private Thread                      respondListener;
    private Parser                      parser;
    private Handler                     requestHandler;
    private Handler                     respondHandler;
    private BlockingQueue<Request>      requests;
    private BlockingQueue<Respond>      responds;


    public Communication(Handler requestHandler , Handler respondHandler){

        System.out.println("Communication has started...");

        //initializing request and respond lists
        requests = new ArrayBlockingQueue<>(1000);
        responds = new ArrayBlockingQueue<>(1000);

        //initializing the request and respond parser
        parser = (packet) -> {
            byte[] data = new byte[packet.getLength()];
            System.arraycopy(packet.getData() , 0 , data , 0 , packet.getLength());
            String incomingMessage = new String(data);
            if(incomingMessage.contains("request"))
            {
                Request request = new Gson().fromJson(incomingMessage , Request.class);
                request.setDestIp(packet.getAddress());
                requests.add(request);
            }
            else
            {
                Respond respond =  new Gson().fromJson(incomingMessage , Respond.class);
                respond.setDestIp(packet.getAddress());
                responds.add(respond);
            }
        };

        //initializing the request and respond handler
        this.requestHandler = requestHandler;
        this.respondHandler = respondHandler;

        //initializing the request and respond listeners
        requestListener = new Thread(() -> {
            System.out.println("request listener has started...");
            while (true)
            { if(!requests.isEmpty())
            {
                Message message = requestHandler.handle(requests.poll());
                if(message instanceof Respond)
                    responds.add((Respond) message);

            }
            else {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        });
        respondListener = new Thread(() -> {
            System.out.println("respond listener has started...");
            while (true) {
                if(!responds.isEmpty())
                    respondHandler.handle(responds.poll());
                else {
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        requestListener.start();
        respondListener.start();


    }


    public Parser getParser() {
        return parser;
    }

    public void communicate(Message m )
    {
        if(m instanceof Request)
            requestHandler.handle(m);
        else
            respondHandler.handle(m);
    }
}
