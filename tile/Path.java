package tile;

/**
 * Represents a basic, walkable path tile in the game.
 * This class extends the {@code Tile} class.
 *
 * @author Tanvir Ahmed
 * @version 1.0
 */
public class Path extends Tile {

    /**
     * Constructs a new Path instance.
     * Initializes the tile with a key that represents a path tile.
     */
    public Path() {
        this.key = "PA";
    }

    /**
     * Determines if the path tile is passable.
     *
     * @return Always true, as path tiles are always passable.
     */
    @Override
    public boolean isPassable() {
        return true;
    }


}

