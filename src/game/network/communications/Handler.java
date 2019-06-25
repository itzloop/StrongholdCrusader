package game.network.communications;

import game.network.communications.message.Message;

public interface Handler<T extends Message> {
    T handle(T t);
}
