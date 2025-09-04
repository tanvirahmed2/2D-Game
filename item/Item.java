package item;

import java.awt.*;

import static java.awt.Color.*;

/**
 * Represents a generic item within a game.
 * This class provides basic properties and methods that are common to all items,
 * such as picked-up status, position, and a key for identification.
 *
 * @author Max Holloway, Tanvir Ahmed, Carl Antill, Tomas Williams
 * @version 1.4
 */
public class Item {

    // Indicates whether the item has been picked up.
    protected Boolean pickedUp;
    // A unique identifier or code for the item.
    protected String key;
    // The x-coordinate of the item's position.
    protected int xPos;
    // The y-coordinate of the item's position
    protected int yPos;

    /**
     * Default constructor for creating an item.
     */
    public Item() {
    }

    /**
     * Constructs an item with a specified picked-up status.
     *
     * @param pickedUp The picked-up status of the item.
     */
    public Item(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    /**
     * Retrieves the picked-up status of the item.
     *
     * @return The picked-up status of the item.
     */
    public Boolean getPickedUp() {
        return pickedUp;
    }

    /**
     * Retrieves the key of the item.
     *
     * @return The key identifying the item.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key for the item.
     *
     * @param key The new key to identify the item.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Sets the position of the item.
     *
     * @param x The x-coordinate of the item's position.
     * @param y The y-coordinate of the item's position.
     */
    public void setPos(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    /**
     * Creates a key for the item based on its picked-up status and color.
     * The method generates a unique key representing the state and type of the item.
     *
     * @param pickedUp Indicates whether the item has been picked up.
     * @param color    The color associated with the item.
     * @return A string key representing the item's state and type.
     */
    public String createKey(boolean pickedUp, Color color) {
        String code;
        if (RED.equals(color)) {
            code = "R";
        } else if (GREEN.equals(color)) {
            code = "G";
        } else if (BLUE.equals(color)) {
            code = "B";
        } else if (YELLOW.equals(color)) {
            code = "Y";
        } else {
            code = "C";
        }

        if (pickedUp) {
            return code + "P";
        }
        return code + "N";
    }

    /**
     * Sets the picked-up status of the item.
     * This method is used to change the state of the item to indicate whether it has been picked up.
     *
     * @param pickedUp The new picked-up status to set for the item.
     */
    public void setPickedUp(Boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    /**
     * Retrieves the x-coordinate of the item's position.
     * This coordinate represents the item's horizontal position in the game world.
     *
     * @return The x-coordinate of the item's position.
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Sets the x-coordinate of the item's position.
     * This method allows for changing the item's horizontal position in the game world.
     *
     * @param xPos The new x-coordinate to set for the item's position.
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Retrieves the y-coordinate of the item's position.
     * This coordinate represents the item's vertical position in the game world.
     *
     * @return The y-coordinate of the item's position.
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Sets the y-coordinate of the item's position.
     * This method allows for changing the item's vertical position in the game world.
     *
     * @param yPos The new y-coordinate to set for the item's position.
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Marks the item as picked up.
     * This method can be overridden by subclasses to implement specific pickup behaviors.
     */
    public void pickUp() {
        this.key = null;
    }


}


