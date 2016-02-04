package it.unipd.math.pcd.actors.develop;

import it.unipd.math.pcd.actors.ActorRef;
import it.unipd.math.pcd.actors.Message;

import java.util.LinkedList;

/**
 * Created by Nicola Carraro.
 */
public class MailBox<T extends Message> {

    private LinkedList<Mail<T>> mailbox = new LinkedList<>();

    /**
     * Build MailBox object
     */
    public MailBox() {
    }

    /**
     * Add Mail as last element
     *
     * @param m message
     * @param s sender
     */
    public void add(T m, ActorRef<T> s) {
        mailbox.addLast(new Mail<>(m, s));
    }

    /**
     * Remove Mail as first element
     *
     * @return Mail removed
     */
    public Mail<T> remove() throws InterruptedException {
        return mailbox.removeFirst();
    }

    /**
     * Empty mail box
     */
    public void clear() {
        mailbox.clear();
    }

    /**
     * Check if mailbox is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return mailbox.size() == 0;
    }

}
