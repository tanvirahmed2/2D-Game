package tile;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code GameButton} class represents a lever tile within the game.
 * A lever is linked to one or more traps and can control their state.
 * This class extends the {@code Tile} class, inheriting its basic tile characteristics.
 * It includes methods for managing the associated traps and the lever's unique identifier.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.6
 */
public class GameButton extends Tile {

    // Unique identifier for the gameButton.
    private int number;
    // List of traps linked to this gameButton.
    private List<Trap> associatedTraps;

    /**
     * Constructs a new GameButton instance with specified parameters.
     * Initializes an empty list for associated traps.
     *
     * @param number The unique number of the gameButton.
     * @param x      The x-coordinate of the gameButton on the tile grid.
     * @param y      The y-coordinate of the gameButton on the tile grid.
     * @param key    The unique identifier for the gameButton tile.
     */
    public GameButton(int number, int x, int y, String key) {
        super(x, y, key);
        this.number = number;
        this.associatedTraps = new ArrayList<>();
    }

    /**
     * Constructs a new GameButton instance with a specified number.
     * Initializes an empty list for associated traps and sets the key based on the number.
     *
     * @param number The unique number of the gameButton.
     */
    public GameButton(int number) {
        this.associatedTraps = new ArrayList<>();
        this.key = "B" + number;
    }


    /**
     * Toggles the state of all traps associated with this gameButton.
     * Activate traps that are inactive and deactivates those that are active.
     */
    public void toggleTraps() {
        for (Trap trap : associatedTraps) {
            trap.toggle();
        }
    }

    /**
     * Returns a list of traps associated with this gameButton.
     *
     * @return A list of associated traps.
     */
    public List<Trap> getAssociatedTraps() {
        return associatedTraps;
    }

    /**
     * Sets the list of traps associated with this gameButton.
     *
     * @param trap The list of traps to be associated with the gameButton.
     */
    public void addAssociatedTrap(Trap trap) {
        this.associatedTraps.add(trap);
    }

    /**
     * Sets the list of traps associated with this gameButton.
     *
     * @param associatedTraps The list of traps to be associated with the gameButton.
     */
    public void setAssociatedTraps(List<Trap> associatedTraps) {
        this.associatedTraps = associatedTraps;
    }

    /**
     * Gets the unique number of the gameButton.
     *
     * @return The unique number of the gameButton.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the unique number of the gameButton.
     *
     * @param number The new unique number for the gameButton.
     */
    public void setNumber(int number) {
        this.number = number;
    }

}

