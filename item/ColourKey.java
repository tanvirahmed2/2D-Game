package item;

import java.awt.*;

/**
 * The {@code ColourKey} class represents a colored key item in a game.
 * This class extends the {@code Item} class and includes a specific color attribute,
 * making it distinct and potentially corresponding to specific doors or obstacles.
 *
 * @author Max Holloway, Tanvir Ahmed, Carl Antill, Tomas Williams
 * @version 1.5
 */
public class ColourKey extends Item {

    // The color of the key.
    private Color colour;

    /**
     * Constructs a new ColourKey with specified parameters.
     *
     * @param key      The key representing the state and type of the color key.
     * @param x        The x-coordinate of the color key.
     * @param y        The y-coordinate of the color key.
     * @param pickedUp Indicates whether the color key has been picked up.
     * @param colour   The color of the key.
     */
    public ColourKey(String key, int x, int y, Boolean pickedUp, Color colour) {
        super(pickedUp);
        this.colour = colour;
        setPos(x, y);

        // Assigning the key for the color key
        this.key = key;

    }

    /**
     * Constructs a new {@code ColourKey} with a default key based on its picked up state and color.
     *
     * @param pickedUp Indicates whether the color key has been picked up.
     * @param colour   The color of the key.
     */
    public ColourKey(Boolean pickedUp, Color colour) {
        super(pickedUp);
        this.key = createKey(pickedUp, colour);
    }

    /**
     * Marks the color key as picked up and updates its key.
     * This method changes the key's state to reflect that it has been collected.
     */
    @Override
    public void pickUp() {
        this.pickedUp = true;
        this.key = createKey(true, colour);
    }

    /**
     * Retrieves the color of the key.
     * This method is used to get the specific color associated with this key,
     * which can be indicative of its purpose or the specific lock it opens.
     *
     * @return The Color of the key.
     */
    public Color getColour() {
        return colour;
    }

    /**
     * Sets the color of the key.
     * This method allows changing the color of the key, potentially altering its
     * corresponding lock or usage within the game.
     *
     * @param colour The new Color to be set for the key.
     */
    public void setColour(Color colour) {
        this.colour = colour;
    }
}
