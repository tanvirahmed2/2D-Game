package level;

import actor.*;

import static level.KeyFactory.getDirectionFromKey;

/**
 * The {@code ActorFactory} class provides a method for creating actor instances based on a given key.
 * This factory pattern is used to generate different types of actors, like players, blocks, and various
 * creatures, for use in a game level. Each key corresponds to a specific type of actor,
 * and the factory method returns the appropriate actor instance.
 * <p>
 * This class utilizes a static method to create actors, ensuring that actor creation can be managed
 * centrally and consistently across the game.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.4
 */
public class ActorFactory {

    /**
     * Creates an actor based on a specified key.
     * The key determines the type of actor to be created. The method supports various actor types,
     * including Player, Block, PinkBall, Bug, Frog, and EmptyActor. Some actor types require additional
     * parameters such as direction, which are derived from the key.
     *
     * @param key The key representing the type of actor to be created.
     * @return An instance of the specified actor type, or {@code null} if the key does not match any actor type.
     */
    static Actor actorKey(String key) {
        return switch (key) {
            case "PY" -> new Player();
            case "BC" -> new Block();
            case "PU", "PD", "PL", "PR" -> new PinkBall(getDirectionFromKey(key));
            case "BW", "BE" -> new Bug(getDirectionFromKey(key));
            case "FG" -> new Frog();
            case "##" -> new EmptyActor();
            default -> null;
        };
    }
}
