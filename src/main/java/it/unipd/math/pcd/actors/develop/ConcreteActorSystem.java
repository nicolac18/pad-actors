package it.unipd.math.pcd.actors.develop;

import it.unipd.math.pcd.actors.*;
import it.unipd.math.pcd.actors.exceptions.NoSuchActorException;

import java.util.Map;

/**
 * Created by Nicola Carraro.
 */
public class ConcreteActorSystem extends AbsActorSystem {

    private ActorProcess executor = new ActorProcess();
    private Map<ActorRef<? extends Message>, Actor<? extends Message>> actors = this.getActors();

    /**
     *
     * @param actor Actor
     * @param mode Connection mode
     * @return Actor reference
     */
    @Override
    public ActorRef<? extends Message> actorOf(Class<? extends Actor> actor, ActorMode mode) {
        ActorRef ref = super.actorOf(actor, mode);

        executor.execute((AbsActor) actors.get(ref));

        return ref;
    }

    /**
     *
     */
    @Override
    public void stop() {
        for (Map.Entry<ActorRef<? extends Message>, Actor<? extends Message>> entry : actors.entrySet()) {
            actors.remove(entry.getKey());
            executor.stop((AbsActor) entry.getValue());
        }
    }

    /**
     *
     * @param act Actor reference
     */
    @Override
    public void stop(ActorRef<?> act) {
        if (act != null) {
            AbsActor absActor = (AbsActor) actors.remove(act);

            if (absActor == null) {
                throw new NoSuchActorException();
            }

            executor.stop(absActor);
        }
    }

    /**
     *
     * @param mode Connection mode
     * @return Actor reference
     */
    @Override
    protected ActorRef createActorReference(ActorMode mode) {
        if (mode == ActorMode.LOCAL) {
            return new ConcreteActorRefLocal(this);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
