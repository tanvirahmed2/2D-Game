package tile;

import level.Level;

/**
 * This class represents all tile entities, and all positions
 * using coordinate pair (x, y).
 *
 * @author Max Holloway, Tanvir Ahmed, Carl Antill
 * @version 1.6
 */
public class Tile {
    // X coordinate of the tile on the layer.
    protected int xPos;
    // Y coordinate of the tile on the grid.
    protected int yPos;
    // Unique identifier for the tile.
    protected String key;
    // Level to which this tile belongs.
    protected Level level;

    /**
     * Constructs a tile at specific coordinates with a certain identifier.
     *
     * @param x   The x coordinate.
     * @param y   The y coordinate.
     * @param key The identifier of the tile.
     */
    public Tile(int x, int y, String key) {
        this.xPos = x;
        this.yPos = y;
        this.key = key;
    }

    /**
     * Default constructor for the Tile class.
     * Creates a tile without specific position or identifier.
     */
    public Tile() {

    }

    /**
     * Sets the position of the tile on the grid.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public void setPos(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    /**
     * Determines if the tile is passable.
     *
     * @return boolean indicating if the tile can be passed through.
     * This method can be overridden by subclasses.
     */
    public boolean isPassable() {
        // Most tiles are passable, except for specific types like walls, doors, etc.
        return true;
    }

    /**
     * Get the x coordinate.
     *
     * @return The x coordinate.
     */
    public int getxPos() {
        return this.xPos;
    }

    /**
     * Get the y coordinate.
     *
     * @return the y coordinate.
     */
    public int getyPos() {
        return this.yPos;
    }

    /**
     * Gets the position of the tile as an (x, y) coordinate pair.
     *
     * @return An array containing the x and y coordinates.
     */
    public int[] getPos() {
        int[] pos = {this.xPos, this.yPos};
        return pos;
    }

    /**
     * Get the key of the tile.
     *
     * @return the key of the tile.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Set the x coordinate.
     *
     * @param pos The new x coordinate.
     */
    public void setxPos(int pos) {
        this.xPos = pos;
    }

    /**
     * Set the y coordinate.
     *
     * @param pos The new y coordinate.
     */
    public void setyPos(int pos) {
        this.yPos = pos;
    }

    /**
     * Set the key of the tile.
     *
     * @param key The new id coordinate.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Sets the level to which this tile belongs.
     *
     * @param level The level object to which this tile is associated.
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Retrieves the level that this tile is part of.
     *
     * @return The  Level object associated with this tile.
     */
    public Level getLevel() {
        return level;
    }
}