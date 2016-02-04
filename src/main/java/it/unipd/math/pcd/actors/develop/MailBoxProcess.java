package it.unipd.math.pcd.actors.develop;

import it.unipd.math.pcd.actors.AbsActor;
import it.unipd.math.pcd.actors.Message;

/**
 * Created by Nicola Carraro.
 */
public class MailBoxProcess<T extends Message> implements Runnable {

    private AbsActor<T> actor;
    private MailBox<T> mailBox;

    private Thread process;
    private boolean processing = true;

    /**
     * Build MailBoxProcess object
     * @param a Actor's mailbox
     */
    public MailBoxProcess(AbsActor<T> a) {
        actor   = a;
        mailBox = a.getMailBox();
    }

    /**
     * Stop mailbox processing
     */
    public void stop() {
        processing = false;

        if (process != null) {
            process.interrupt();
        }
    }

    /**
     * Return processing state
     * @return true if processing
     */
    public boolean condition() {
        return (processing || !mailBox.isEmpty());
    }

    /**
     * Function that process the mailbox
     */
    public void loop() {
        try {
            Mail<T> mail = mailBox.remove();

            actor.setSender(mail.getSender());
            actor.receive(mail.getMessage());
        } catch (InterruptedException e) {
        }
    }

    /**
     * Function for default
     */
    public void forward() {
    }

    /**
     * Get the current thread
     */
    public void before() {
        process = Thread.currentThread();
    }

    /**
     * Start mailbox process
     */
    public void run() {
        before();
        while (condition()) {
            loop();
        }
        forward();
    }
}
