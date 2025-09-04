package tile;

/**
 * Represents a wall tile in the game. Walls are non-passable tiles.
 * This class extends the {@code Tile} class.
 *
 * @author Tanvir Ahmed, Max Holloway
 * @version 1.3
 */
public class Wall extends Tile {

    /**
     * Constructs a new  Wall instance.
     * Initializes the wall tile with a specific key identifier.
     */
    public Wall() {
        this.key = "WA";
    }

    /**
     * Determines if the wall tile is passable.
     *
     * @return Always false, as wall tiles are never passable.
     */
    @Override
    public boolean isPassable() {
        return false;
    }

}
