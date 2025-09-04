package tile;

/**
 * Represents a dirt tile in the game. Dirt tiles turn into path tiles when walked on by the player.
 * This class extends the {@code Tile} class.
 *
 * @author Tanvir Ahmed, Max Holloway
 * @version 1.4
 */
public class Dirt extends Tile {

    // Indicates whether the dirt tile has been compacted into a path tile.
    private boolean isCompacted;

    /**
     * Constructs a {@code Dirt} object. Initially, the dirt tile is not compacted.
     */
    public Dirt() {
        this.key = "DI";
        this.isCompacted = false; // Initially, the dirt is not compacted.
    }

    /**
     * Compacts the dirt tile, turning it into a path tile.
     */
    public void compact() {
        this.isCompacted = true;
    }

    /**
     * Determines if the dirt tile is passable.
     * A dirt tile is passable only if it has been compacted.
     *
     * @return {@code true} if the tile is compacted, {@code false} otherwise.
     */
    @Override
    public boolean isPassable() {
        return isCompacted;
    }

    /**
     * Checks if the dirt tile has been compacted into a path.
     * This method allows checking the current state of the tile.
     *
     * @return true if the tile has been compacted, false otherwise.
     */
    public boolean isCompacted() {
        return isCompacted;
    }

    /**
     * Sets the compacted state of the dirt tile.
     * This method allows changing the state of the tile.
     *
     * @param compacted The new compacted state of the tile.
     */
    public void setCompacted(boolean compacted) {
        isCompacted = compacted;
    }

}
