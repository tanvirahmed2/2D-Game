package tile;

import java.awt.*;

/**
 * Represents a door tile with a specific color and state.
 * A door can be open or closed.
 * This class extends the {@code Tile} class.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.5
 */
public class Door extends Tile {

    // Color of the door.
    private Color colour;
    // State of the door; true if the door is open, false otherwise.
    private Boolean isOpen;

    /**
     * Constructs a new Door instance with specified parameters.
     *
     * @param colour The color of the door.
     * @param isOpen The initial state of the door (true for open, false for closed).
     * @param x      The x-coordinate of the door on the tile grid.
     * @param y      The y-coordinate of the door on the tile grid.
     * @param key    The unique identifier for the door tile.
     */
    public Door(Color colour, Boolean isOpen, int x, int y, String key) {
        super(x, y, key);
        this.colour = colour;
        this.isOpen = isOpen;
    }

    /**
     * Constructs a new Door object with specified color and open status.
     *
     * @param colour The color of the door.
     * @param isOpen The open status of the door (true for open, false for closed).
     */
    public Door(Color colour, Boolean isOpen) {
        this.colour = colour;
        this.isOpen = isOpen;
        this.key = getKeyFromOpenAndColor(isOpen, colour);
    }

    /**
     * Generates a unique key for a Door based on its open state and color.
     * This method is useful for identifying doors in a consistent way.
     *
     * @param isOpen The open state of the door. True if open, false if locked.
     * @param color  The color of the door. Should be one of Color.RED, Color.GREEN, Color.BLUE, or Color.YELLOW.
     * @return A string representing the key for the door, combining the color and open state.
     * The format is "{color code}{open code}", where color code is "R", "G", "B", or "Y",
     * and open code is "U" for open or "L" for locked.
     */
    private String getKeyFromOpenAndColor(boolean isOpen, Color color) {
        String colorCode;
        if (color == Color.RED) {
            colorCode = "R";
        } else if (color == Color.GREEN) {
            colorCode = "G";
        } else if (color == Color.BLUE) {
            colorCode = "B";
        } else if (color == Color.YELLOW) {
            colorCode = "Y";
        } else {
            colorCode = "";
        }

        String openCode = isOpen ? "U" : "L";
        return colorCode + openCode;
    }

    /**
     * Attempts to open the door using a key of a given color.
     * The door will open only if it is not already open and the key color matches the door color.
     *
     * @param keyColor The color of the key attempting to open the door.
     * @return true if the door was successfully opened, false otherwise.
     */
    public boolean tryOpen(Color keyColor) {
        if (!isOpen && this.colour.equals(keyColor)) {
            this.isOpen = true;
            return true;
        }
        return false;
    }

    /**
     * Determines if the door is passable.
     * A door is passable only if it is open.
     *
     * @return true if the door is open, false otherwise.
     */
    @Override
    public boolean isPassable() {
        return isOpen;
    }


    /**
     * Gets the color of the door.
     *
     * @return The color of the door.
     */
    public Color getColour() {
        return colour;
    }

    /**
     * Sets the color of the door.
     *
     * @param colour The new color of the door.
     */
    public void setColour(Color colour) {
        this.colour = colour;
    }

    /**
     * Gets the current open/closed state of the door.
     *
     * @return true if the door is open, false otherwise.
     */
    public Boolean getOpen() {
        return isOpen;
    }

    /**
     * Sets the open/closed state of the door.
     *
     * @param open true to open the door, false to close it.
     */
    public void setOpen(Boolean open) {
        isOpen = open;
    }


}
