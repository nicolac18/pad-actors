package it.unipd.math.pcd.actors.develop;

import it.unipd.math.pcd.actors.AbsActor;
import it.unipd.math.pcd.actors.Actor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Nicola Carraro.
 */
public class ActorProcess {

    private Map<Actor<?>, MailBoxProcess<?>> mailBoxes  = new HashMap<>();
    private Map<Actor<?>, Future> processes             = new HashMap<>();
    private ExecutorService executors                   = Executors.newCachedThreadPool();

    /**
     * Build ActorProcess object
     */
    public ActorProcess() {
    }

    /**
     * Start processing
     *
     * @param actor Actor reference
     */
    public void execute(AbsActor actor) {

        MailBoxProcess<?> mailBoxProcess = new MailBoxProcess<>(actor);

        mailBoxes.put(actor, mailBoxProcess);
        processes.put(actor, executors.submit(mailBoxProcess));
    }

    /**
     * Stop processing
     *
     * @param actor Actor reference
     */
    public void stop(AbsActor actor) {
        mailBoxes.get(actor).stop();

        try {
            processes.get(actor).get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
