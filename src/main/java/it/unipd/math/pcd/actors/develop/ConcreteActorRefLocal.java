package it.unipd.math.pcd.actors.develop;

import it.unipd.math.pcd.actors.AbsActor;
import it.unipd.math.pcd.actors.AbsActorSystem;
import it.unipd.math.pcd.actors.ActorRef;
import it.unipd.math.pcd.actors.Message;

/**
 * Created by Nicola Carraro.
 */
public class ConcreteActorRefLocal<T extends Message> extends ConcreteActorRef<T> {

    /**
     * Build ConcreteActorRefLocal object
     *
     * @param sys ActorSystem reference
     */
    public ConcreteActorRefLocal(AbsActorSystem sys) {
        super(sys);
    }

    /**
     * Send Mail
     *
     * @param message The message to send
     * @param to The actor to which sending the message
     */
    @Override
    public void send(T message, ActorRef to) {
        AbsActor actor = (AbsActor) aaSystem.getActorRef(to);
        actor.post(message, this);
    }

}
