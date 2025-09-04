package tile;

/**
 * Represents a water tile in the game. Water tiles are impassable and cause the player to lose the level
 * if an attempt is made to cross them. This class extends the {@code Tile} class.
 *
 * @author Tanvir Ahmed, Max Holloway
 * @version 1.7
 */
public class Water extends Tile {


    /**
     * Constructs a new Water instance.
     * Initializes the water tile with a specific key identifier.
     */
    public Water() {
        this.key = "WT";
    }

    /**
     * Determines if the water tile is passable.
     *
     * @return Always false, as water tiles are not passable.
     */
    @Override
    public boolean isPassable() {
        return false;
    }

    /**
     * Converts the water tile into a path tile.
     * This method can be used to change the nature of the tile from impassable to passable,
     * typically after certain conditions in the game are met.
     *
     * @return A new Path object representing the converted path tile.
     */
    public Path convertToPath() {
        Path path = new Path();
        path.setPos(getxPos(), getyPos());
        return path;
    }


}
