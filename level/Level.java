package level;


import actor.*;
import item.EmptyItem;
import item.Item;
import tile.*;
import java.io.File;
import java.util.ArrayList;
import static level.LevelReader.readLevelFromFile;

/**
 * The {@code Level} class defines and manages the structure of a game level.
 * It encompasses various layers like actors, tiles, and items, along with properties
 * such as level number, time limit, and dimensions. This class is responsible for
 * initializing and managing the game level's state.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 2.4
 */
public class Level {

    // Level of the game
    private int levelNumber;
    // Maximum time allowed for the level
    private int timeLimit;
    // File object representing the level file
    private File levelFile;
    // Width of the layer
    private int width;
    // Height of the layer
    private int height;

    // Constant string for file not found error message.
    final private String FILE_NOT_FOUND = "File not found";
    // Reference to a reset version of the level, for restarting purposes.
    private Level resetLevel;

    // Name of the file from which the level is loaded.
    private String fileName;
    // Layer containing actor objects in the level.
    private Layer<Actor> actors;
    // Layer containing tile objects in the level.
    private Layer<Tile> tiles;
    // Layer containing item objects in the level.
    private Layer<Item> items;

    // List of items to be stored or managed within the level.
    private ArrayList<Item> itemsToStore;

    /**
     * Default constructor for creating an empty level.
     */
    public Level() {
    }

    /**
     * Constructs a Level object from a file.
     * Initializes the level based on the data from the specified file and writes the initial state
     * to a reset file for later use.
     *
     * @param fileName The name of the file containing the level data.
     */
    public Level(String fileName) {
        this.fileName = fileName;
        createLevel();
        //resetLevel = this;
        this.itemsToStore = this.items.checkPickedItems();


        writeLevel("level/resetLevel.txt");
    }

    /**
     * Initializes the level by reading data from the specified file and setting up layer interactions.
     * This method reads the level configuration from a file and then initializes the interactions
     * between different layers of the level, such as linking traps with their corresponding mechanisms.
     */
    public void createLevel() {
        readLevelFromFile(fileName, this);
        initialiseLayerInteractions();
    }

    /**
     * Writes the current state of the level to a file.
     * This method is used for saving the level's state to a file, which can be useful for
     * level persistence or creating snapshots of the game state at different points.
     *
     * @param fileName The name of the file where the level data will be written.
     */
    public void writeLevel(String fileName) {
        LevelWriter level = new LevelWriter();
        level.writeLevelToFile(fileName, this);
    }



    /**
     * Initializes interactions between different layers of the level.
     * For example, this method could be used to link traps with their corresponding control mechanisms,
     * ensuring that the game logic reflects these relationships.
     */
    public void initialiseLayerInteractions() {
        this.tiles.linkTraps(this.tiles.getTraps());
    }


    /**
     * Retrieves the current level number of the game.
     * The level number typically represents the sequence or difficulty level in the game.
     *
     * @return The current level number.
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * Sets the level number for the game.
     * This method can be used to update or initialize the level to a specific sequence number.
     *
     * @param levelNumber The new level number to be set.
     */
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Gets the time limit set for completing the level.
     * The time limit is the maximum allowed time for players to complete the level.
     *
     * @return The time limit for the level.
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Sets a new time limit for the level.
     * This method is useful for adjusting the difficulty or duration of the level.
     *
     * @param timeLimit The new time limit to be set for the level.
     */
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * Retrieves the File object representing the file from which the level is loaded.
     * This can include the layout, settings, and other configurations of the level.
     *
     * @return The File object representing the level file.
     */
    public File getLevelFile() {
        return levelFile;
    }

    /**
     * Sets the File object representing the file for the level.
     * This method is used to specify or change the source file for the level's configuration.
     *
     * @param levelFile The new File object representing the level file.
     */
    public void setLevelFile(File levelFile) {
        this.levelFile = levelFile;
    }

    /**
     * Gets the width of the level.
     *
     * @return the width of the level
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the width of the level.
     *
     * @param width the new width of the level
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the level.
     *
     * @return the height of the level
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the height of the level.
     *
     * @param height the new height of the level
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the layer containing all actor objects in the level.
     *
     * @return The layer of actors.
     */
    public Layer<Actor> getActors() {
        return actors;
    }

    /**
     * Sets the layer of actors in the level.
     * This method allows for replacing or updating the entire actor layer.
     *
     * @param actors The new layer of actors to be set.
     */
    public void setActors(Layer<Actor> actors) {
        this.actors = actors;
    }

    /**
     * Gets the layer containing all tile objects in the level.
     *
     * @return The layer of tiles.
     */
    public Layer<Tile> getTiles() {
        return tiles;
    }

    /**
     * Sets the layer of tiles in the level.
     * This method allows for replacing or updating the entire tile layer.
     *
     * @param tiles The new layer of tiles to be set.
     */
    public void setTiles(Layer<Tile> tiles) {
        this.tiles = tiles;
    }

    /**
     * Gets the layer containing all item objects in the level.
     *
     * @return The layer of items.
     */
    public Layer<Item> getItems() {
        return items;
    }

    /**
     * Sets the layer of items in the level.
     * This method allows for replacing or updating the entire item layer.
     *
     * @param items The new layer of items to be set.
     */
    public void setItems(Layer<Item> items) {
        this.items = items;
    }

    /**
     * Simulates the action of picking up an item at specified coordinates.
     * This method is typically used to update the item layer when an item is interacted with or collected.
     *
     * @param x The x-coordinate where the item is picked up.
     * @param y The y-coordinate where the item is picked up.
     */
    public void pickUp(int x, int y) {
        items.setItem(x, y, new EmptyItem());
    }

    /**
     * Retrieves the constant message used for file not found scenarios.
     *
     * @return The file not found message string.
     */
    public String getFILE_NOT_FOUND() {
        return FILE_NOT_FOUND;
    }

    /**
     * Retrieves the reset level object.
     * The reset level is typically used to restore the level to its initial state.
     *
     * @return The reset level object.
     */
    public Level getResetLevel() {
        return resetLevel;
    }

    /**
     * Sets the reset level object.
     * This method can be used to assign a new reset level for restoring the level state.
     *
     * @param resetLevel The new reset level to be set.
     */
    public void setResetLevel(Level resetLevel) {
        this.resetLevel = resetLevel;
    }

    /**
     * Gets the file name from which the level data is loaded.
     *
     * @return The file name of the level.
     */

    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the file name for the level.
     * This method is used to specify or change the file from which the level is loaded.
     *
     * @param fileName The new file name to be set for the level.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Retrieves the list of items to be stored or managed within the level.
     *
     * @return The list of items to store.
     */
    public ArrayList<Item> getItemsToStore() {
        return itemsToStore;
    }

    /**
     * Sets the list of items to be stored within the level.
     * This method can be used to update or manage the inventory of items in the level.
     *
     * @param itemsToStore The new list of items to be stored.
     */
    public void setItemsToStore(ArrayList<Item> itemsToStore) {
        this.itemsToStore = itemsToStore;
    }
}
