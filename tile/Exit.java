package tile;

/**
 * Represents an exit tile in the game. The exit tile is the goal for the player to reach
 * to complete a level, and it becomes passable only when unlocked. This class extends the
 * {@code Tile} class.
 *
 * @author Tanvir Ahmed, Max Holloway
 * @version 1.4
 */
public class Exit extends Tile {

    // Indicates whether the exit is unlocked and thus passable.
    private boolean isUnlocked;

    /**
     * Constructs a new Exit instance.
     * Initially, the exit is locked and not passable.
     */
    public Exit() {
        this.key = "EX";
        this.isUnlocked = false; // Initially locked
    }

    /**
     * Determines if the exit tile is passable.
     * The exit tile is passable only when it is unlocked.
     *
     * @return true if the exit is unlocked and passable, false otherwise.
     */
    @Override
    public boolean isPassable() {
        return isUnlocked;
    }

    /**
     * Unlocks the exit, making it passable.
     * This method changes the state of the exit to unlocked.
     */
    public void unlock() {
        isUnlocked = true;
    }

    /**
     * Checks if the exit tile is unlocked.
     * This method allows querying the current state of the exit.
     *
     * @return true if the exit is unlocked, false otherwise.
     */
    public boolean isUnlocked() {
        return isUnlocked;
    }

    /**
     * Sets the unlocked state of the exit tile.
     * This method allows changing the state of the exit.
     *
     * @param unlocked The new unlocked state to set.
     */
    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }
}
