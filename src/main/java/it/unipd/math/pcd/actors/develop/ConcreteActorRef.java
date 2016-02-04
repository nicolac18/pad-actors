package it.unipd.math.pcd.actors.develop;

import it.unipd.math.pcd.actors.AbsActorSystem;
import it.unipd.math.pcd.actors.ActorRef;
import it.unipd.math.pcd.actors.Message;

/**
 * Created by Nicola Carraro.
 */
public abstract class ConcreteActorRef<T extends Message> implements ActorRef<T> {

    protected AbsActorSystem aaSystem;

    /**
     * Build ConcreteActorRef object
     *
     * @param a AvsActorSystem reference
     */
    public ConcreteActorRef(AbsActorSystem a) {
        this.aaSystem = a;
    }

    /**
     * Check if ActorRef is the same
     *
     * @param ar ActorRef
     * @return 0 = true, -1 = false
     */
    public int compareTo(ActorRef ar) {
        return this == ar ? 0 : -1;
    }

}
