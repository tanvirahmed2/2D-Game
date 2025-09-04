package actor;

/**
 * Represents an empty actor in the game.
 * This class extends the {@code Actor} class and is used to represent a lack of an actor in a specific location.
 *
 * @author Carl Antill, Tomas Williams
 * @version 1.2
 */
public class EmptyActor extends Actor{

    // Key representing the empty actor.
    private String key = "##";

    /**
     * Constructs a new instance of {@code EmptyActor}.
     * Initializes the empty actor with a default key.
     */
    public EmptyActor(){

    }

    /**
     * Sets a new key for the empty actor.
     * This method overrides the setKey method in the Actor class.
     *
     * @param key The new key to be set for the empty actor.
     */
    @Override
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Retrieves the key of the empty actor.
     *
     * @return The key representing the empty actor.
     */
    public String getKey(){
        return this.key;
    }


}
