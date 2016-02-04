package it.unipd.math.pcd.actors.develop;

import it.unipd.math.pcd.actors.ActorRef;
import it.unipd.math.pcd.actors.Message;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Nicola Carraro.
 */
public class MailBoxSync<T extends Message> extends MailBox<T> {

    private Lock _lock = new ReentrantLock();
    private Condition _inProcess;

    /**
     * Build MailBoxSync object
     */
    public MailBoxSync() {
        _inProcess = _lock.newCondition();
    }

    /**
     * Check if mailbox is empty
     *
     * @return true if is empty
     */
    @Override
    public boolean isEmpty() {
        boolean empty;
        _lock.lock();

        empty = super.isEmpty();

        _lock.unlock();

        return empty;
    }

    /**
     * Add mail to mailbox
     *
     * @param message Message object
     * @param sender Sender of message
     */
    @Override
    public void add(T message, ActorRef<T> sender) {
        _lock.lock();

        super.add(message, sender);

        _inProcess.signal();
        _lock.unlock();
    }

    /**
     * Remove first Mail from mailbox
     *
     * @return Mail removed
     * @throws InterruptedException
     */
    @Override
    public Mail<T> remove() throws InterruptedException {
        _lock.lock();

        if (super.isEmpty()) {
            _inProcess.await();
        }
        Mail<T> mail = super.remove();

        _lock.unlock();

        return mail;
    }
}
