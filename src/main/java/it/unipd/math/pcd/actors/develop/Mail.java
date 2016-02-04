package it.unipd.math.pcd.actors.develop;

import it.unipd.math.pcd.actors.ActorRef;
import it.unipd.math.pcd.actors.Message;

/**
 * Created by Nicola Carraro.
 */
public class Mail<T extends Message> {

    private final T message;
    private final ActorRef<T> sender;

    /**
     * Build Mail object
     *
     * @param m Mail message
     * @param s Mail sender
     */
    public Mail(T m, ActorRef<T> s) {
        this.message    = m;
        this.sender     = s;
    }

    /**
     * Getter message
     *
     * @return message
     */
    public T getMessage() {
        return this.message;
    }

    /**
     * Getter sender
     *
     * @return sender
     */
    public ActorRef<T> getSender() {
        return this.sender;
    }
}
