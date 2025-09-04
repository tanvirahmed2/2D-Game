package item;

import java.awt.*;

/**
 * The {@code Chip} class represents a collectible chip item in a game.
 * This class extends the {@code Item} class, adding specific behavior for chips,
 * such as their pickup functionality.
 *
 * @author Max Holloway, Tanvir Ahmed, Carl Antill, Tomas Williams
 * @version 1.3
 */
public class Chip extends Item {

    /**
     * Constructs a new Chip with specified parameters.
     *
     * @param key      The key representing the chip's state and type.
     * @param x        The x-coordinate of the chip.
     * @param y        The y-coordinate of the chip.
     * @param pickedUp Indicates whether the chip has been picked up.
     */
    public Chip(String key, int x, int y, Boolean pickedUp) {
        super(pickedUp);
        setPos(x, y);
        this.key = key; // Assigning the key for the chip
    }

    /**
     * Constructs a new {@code Chip} with a default key based on its picked up state.
     *
     * @param pickedUp Indicates whether the chip has been picked up.
     */
    public Chip(boolean pickedUp) {
        super(pickedUp);
        this.key = createKey(pickedUp, Color.BLACK);

    }

    /**
     * Marks the chip as picked up and updates its key.
     * This method changes the chip's state to reflect that it has been collected.
     */
    @Override
    public void pickUp() {
        // Marking the chip as picked up
        this.pickedUp = true;
        // Updating the key after pickup
        this.key = createKey(true, Color.BLACK);
    }


}
