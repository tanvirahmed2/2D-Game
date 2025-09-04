package tile;

/**
 * Represents a trap tile in the game. This class extends the {@code Tile} class.
 * A trap can be active or inactive and is identified by a unique number.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.6
 */
public class Trap extends Tile {

    // Unique identifier for the trap.
    private int number;
    // Indicates whether the trap is active.
    private boolean isActive;

    /**
     * Constructs a new Trap instance with specific characteristics.
     *
     * @param number The unique number of the trap.
     * @param x      The x-coordinate of the trap on the tile grid.
     * @param y      The y-coordinate of the trap on the tile grid.
     * @param key    The unique identifier for the trap tile.
     */
    public Trap(int number, int x, int y, String key) {
        super(x, y, key);
        this.number = number;
        this.isActive = false; // Initially, the trap is inactive.
    }

    /**
     * Constructs a new Trap instance with a specified number.
     * Sets the key based on the number and initializes the trap as inactive.
     *
     * @param number The unique number of the trap.
     */
    public Trap(int number) {
        this.number = number;
        this.isActive = false;
        this.key = "T" + number;
    }

    /**
     * Toggles the activation state of the trap.
     * Switches the trap between active and inactive states.
     */
    public void toggle() {
        isActive = !isActive;
    }

    /**
     * Returns whether the trap is active.
     *
     * @return true if the trap is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the activation state of the trap.
     *
     * @param active true to activate the trap, false to deactivate it.
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Gets the number associated with the trap.
     *
     * @return The unique number of the trap.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the number associated with the trap.
     *
     * @param number The new unique number for the trap.
     */
    public void setNumber(int number) {
        this.number = number;
    }


}
