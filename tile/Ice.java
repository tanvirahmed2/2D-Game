package tile;

import actor.Actor;

/**
 * The "Ice" class represents ice tiles in the game.
 * Ice tiles have the ability to slide actors in certain directions.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.7
 */
public class Ice extends Tile {

    // Constants for the different ice behaviors
    public static final String STRAIGHT = "IC";
    public static final String SOUTHEAST = "SE";
    public static final String SOUTHWEST = "SW";
    public static final String NORTHEAST = "NE";
    public static final String NORTHWEST = "NW";

    protected final String[] passableTiles =
            {"PA", "Bx", "Tx", STRAIGHT, SOUTHEAST, SOUTHWEST, NORTHEAST, NORTHWEST, "DI", "EX", "RU", "GU", "YU", "BU"};


    /**
     * Creates a new Ice object with the specified behavior.
     *
     * @param behavior The behavior of the ice.
     */
    public Ice(String behavior) {
        this.key = behavior;
    }

    /**
     * Handles the sliding movement of an actor over the ice.
     *
     * @param direction The initial direction of the actor's movement.
     */
    public Actor.Direction slide(Actor.Direction direction) {
        return getSlideDirection(direction);

        /*
        Actor.Direction newDirection = getSlideDirection(direction);
        int newX = actor.getXPos() + newDirection.getXOffset(newDirection);
        int newY = actor.getYPos() + newDirection.getYOffset(newDirection);
        actor.move(newDirection);
        */

    }

    /**
     * Determines the new direction for sliding based on ice behavior.
     *
     * @param direction The current direction of the actor.
     * @return The new direction after sliding.
     */
    public Actor.Direction getSlideDirection(Actor.Direction direction) {
        return switch (key) {
            case SOUTHEAST, SOUTHWEST, NORTHEAST, NORTHWEST -> determineCornerSlide(direction);
            default -> direction; // Straight ice does not change direction
        };
    }

    /**
     * Determines the new direction when encountering a corner.
     *
     * @param direction The current direction of the actor.
     * @return The new direction after encountering a corner.
     */

    private Actor.Direction determineCornerSlide(Actor.Direction direction) {
        return switch (key) {
            case SOUTHEAST ->
                    (direction == Actor.Direction.DOWN || direction == Actor.Direction.RIGHT) ? Actor.Direction.DOWN : Actor.Direction.RIGHT;
            case SOUTHWEST ->
                    (direction == Actor.Direction.DOWN || direction == Actor.Direction.LEFT) ? Actor.Direction.DOWN : Actor.Direction.LEFT;
            case NORTHEAST ->
                    (direction == Actor.Direction.UP || direction == Actor.Direction.RIGHT) ? Actor.Direction.UP : Actor.Direction.RIGHT;
            case NORTHWEST ->
                    (direction == Actor.Direction.UP || direction == Actor.Direction.LEFT) ? Actor.Direction.UP : Actor.Direction.LEFT;
            default -> direction;
        };
    }

    /**
     * Gets the array of passable tiles.
     *
     * @return An array of keys representing passable tiles.
     */
    public String[] getPassableTiles() {
        return passableTiles;
    }
}