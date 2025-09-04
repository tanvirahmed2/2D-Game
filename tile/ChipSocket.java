package tile;


/**
 * Represents a chip socket tile in the game that requires a certain number of chips to be unlocked.
 * When the required number of chips are collected, the tile becomes passable. This class extends
 * the {@code Tile} class.
 *
 * @author Tanvir Ahmed, Carl Antill, Max Holloway
 * @version 1.6
 */
public class ChipSocket extends Tile {

    // Current number of chips collected and placed in the socket
    private int chipCount;
    // Number of chips required to pass the socket
    private int requiredChips;
    // Indicates whether the chip socket is open.
    private boolean isOpen = false;


    /**
     * Constructs a ChipSocket object with the specified number of chips
     * required to unlock.
     *
     * @param requiredChips The number of chips required to unlock this socket.
     */
    public ChipSocket(int requiredChips) {
        this.key = "C" + requiredChips;
    }

    /**
     * Checks if the player has enough chips to open the socket.
     *
     * @param playerChips The number of chips the player currently has.
     * @return true if the player has enough chips, false otherwise.
     */
    public boolean isEnough(int playerChips) {
        if (playerChips >= requiredChips) {
            // Player has enough chips, open the socket
            // Perform any additional actions here
            return true;
        } else {
            // Player doesn't have enough chips
            return false;
        }
    }

    /**
     * Opens the chip socket, making it passable.
     */
    public void openSocket() {
        isOpen = true;
    }


    /**
     * Determines if the tile is passable.
     *
     * @return true if the current chip count is equal to or exceeds the required chips, false otherwise.
     */
    @Override
    public boolean isPassable() {
        return this.chipCount >= this.requiredChips;
    }

    /**
     * Gets the current chip count.
     *
     * @return The current number of chips in this socket.
     */
    public int getChipCount() {
        return this.chipCount;
    }

    /**
     * Sets the current chip count.
     *
     * @param count The number of chips to set.
     */
    public void setChipCount(int count) {
        this.chipCount = count;
    }

    /**
     * Gets the number of chips required to open this socket.
     *
     * @return The number of required chips.
     */
    public int getRequiredChips() {
        return requiredChips;
    }

    /**
     * Sets the number of chips required to open this socket.
     *
     * @param requiredChips The number of chips required to open the socket.
     */
    public void setRequiredChips(int requiredChips) {
        this.requiredChips = requiredChips;
    }

    /**
     * Checks if the socket is open.
     *
     * @return true if the socket is open, false otherwise.
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Sets the state of the socket as open or closed.
     *
     * @param open The state to set the socket to.
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }
}
