package level;

import actor.*;
import item.ColourKey;
import item.Item;
import tile.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import static level.ActorFactory.actorKey;
import static level.ItemFactory.itemKey;
import static level.TileFactory.tileKey;

/**
 * The {@code Layer} class represents a layer in a game level, containing a grid of objects such as actors, items, or tiles.
 * The class is generic, allowing for different types of objects to be stored in a single layer.
 * It includes methods for populating the layer, writing the layer to a data structure, managing game elements like traps,
 * and linking game elements such as traps with levers.
 *
 * @param <T> The type of objects stored in the layer, e.g., {@code Actor}, {@code Item}, {@code Tile}.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.8
 */
public class Layer<T> {

    // 2D array representing the data stored in the layer. It holds objects of type T.
    private T[][] data;
    // The width of the layer, indicating the number of columns.
    private int width;
    // The height of the layer, indicating the number of rows.
    private int height;
    // The time limit for the layer, often relevant in time-based levels or challenges.
    private int timeLimit;

    /**
     * Constructs a new Layer with specified dimensions, time limit, and initial data.
     *
     * @param width      The width of the layer.
     * @param height     The height of the layer.
     * @param timeLimit  The time limit for the layer, used in time-based game levels.
     * @param layerBlock The initial data to populate the layer.
     */
    public Layer(int width, int height, int timeLimit, String[][] layerBlock){
        this.width = (width);
        this.height = (height);
        this.timeLimit = timeLimit;
        this.data = (T[][]) new Object[height][width];
        populateLayer(layerBlock);
    }

    /**
     * Populates the layer with objects based on the provided data block.
     *
     * @param layerBlock A 2D array of strings representing the initial data for the layer.
     */
    public void populateLayer(String[][] layerBlock) {
        for (int y = height - 1; y >=0; y--) {
            for (int x = width - 1; x >= 0; x--) {
                this.data[y][x] = createObject(layerBlock[y][x], x, y);
            }
        }
    }

    /**
     * Creates an object based on a key and its position.
     * The method determines the type of object (Actor, Item, Tile) based on the key and creates it.
     *
     * @param key The key representing the object to be created.
     * @param x   The x-coordinate of the object in the layer.
     * @param y   The y-coordinate of the object in the layer.
     * @return The created object, cast to the generic type T.
     */
    public T createObject(String key, int x, int y){
        if (actorKey(key) != null){
            Actor actor = actorKey(key);
            actor.setPos(x, y);
            return (T) actor;
        } else if (itemKey(key) != null){
           return (T) itemKey(key);
        } else if (tileKey(key) != null){
            Tile tile = tileKey(key);
            tile.setPos(x,y);
            return (T) tile;
        } else {
            return null;
        }
    }



    /**
     * Converts the current state of the layer into a 2D String array.
     * Each element in the layer is represented by its key.
     * This method is useful for serializing or visualizing the layer's current state.
     *
     * @return A 2D String array representing the current state of the layer.
     */
    public String[][] writeLayer() {
        String[][] layer = new String[height][width];
        for (int y = height - 1; y >=0; y--) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int x = width - 1; x >= 0; x--) { // Fix: Change loop condition
                T element = this.data[y][x];
                layer[y][x] = getKey(element);
            }
        }
        return layer;
    }

    /**
     * Converts the current state of the layer into a 2D String array, excluding picked-up items.
     * This is similar to {@code writeLayer}, but it replaces keys of picked-up items with a placeholder.
     * Useful for scenarios where the visibility of picked-up items needs to be toggled.
     *
     * @return A 2D String array representing the current state of the layer, without showing picked-up items.
     */
    public String[][] writeLayerNoPickedUp(){
        String[][] layer = new String[height][width];
        for (int y = height - 1; y >=0; y--) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int x = width - 1; x >= 0; x--) { // Fix: Change loop condition
                T element = this.data[y][x];
                String key = getKey(element);
                if (element instanceof ColourKey){
                    if (((ColourKey) element).getPickedUp()){
                        key = "##";
                    }
                }
                layer[y][x] = key;
            }
        }
        return layer;
    }

    /**
     * Retrieves the key of an element in the layer using reflection.
     * This method uses reflection to call the {@code getKey} method on the element, if it exists.
     *
     * @param element The element for which the key is to be retrieved.
     * @return The key of the element as a String, or "##" if the element is null.
     */
    public String getKey(T element){
        if (element != null){
            try {
                Method getKeyMethod = element.getClass().getMethod("getKey");
                Object result = getKeyMethod.invoke(element);
                if (result instanceof String) {
                    return (String) result;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace(); // Handle the exception appropriately

            }
        } else {
            return "##";
        }
        return null;
    }


    /**
     * Retrieves a list of all traps present in the layer.
     * This method scans the layer and collects all elements that are instances of the Trap class.
     *
     * @return An {@code ArrayList<Trap>} containing all the traps found in the layer.
     */
    public ArrayList<Trap> getTraps() {
        ArrayList<Trap> trapList = new ArrayList<>();
        for (int y = height - 1; y >=0; y--) {
            for (int x = width - 1; x >= 0; x--) {
                T element = this.data[y][x];
                if (element instanceof Trap){
                    trapList.add((Trap) element);
                }
            }
        }
        return trapList;
    }

    /**
     * Checks and returns a list of all items in the layer.
     * This method is used to compile a list of all items currently present in the layer,
     * which can be used to update an inventory or check the state of the items.
     *
     * @return An {@code ArrayList<Item>} containing all the items in the layer.
     */
    public ArrayList<Item> checkPickedItems(){
        ArrayList<Item> updatedInventory = new ArrayList<>();
        for (int y = height - 1; y >=0; y--) {
            for (int x = width - 1; x >= 0; x--) {
                T element = this.data[y][x];
                if (element instanceof Item){
                    updatedInventory.add((Item) element);
                }
            }
        }
        return updatedInventory;
    }

    /**
     * Links traps with their corresponding game buttons based on matching numbers.
     * This method establishes a connection between each trap and its associated game button,
     * allowing for the game logic to interactively control traps via buttons.
     *
     * @param trapList A list of traps to be linked with game buttons.
     */
    public void linkTraps(ArrayList<Trap> trapList){
        for (int y = height - 1; y >=0; y--) {
            for (int x = width - 1; x >= 0; x--) {
                T element = this.data[y][x];
                if (element instanceof GameButton) {
                    for (Trap trap : trapList) {
                        if (((GameButton) element).getNumber() == trap.getNumber()) {
                            linkTrapWithLever((GameButton) element, trap);
                        }
                    }
                }
            }
        }
    }

    /**
     * Links a specific trap with a game button.
     * This helper method associates a trap with a game button, allowing the button to control the trap's state.
     *
     * @param gameButton The game button to be linked.
     * @param trap       The trap to be linked to the game button.
     */
    private void linkTrapWithLever(GameButton gameButton, Trap trap) {
        gameButton.addAssociatedTrap(trap);
    }

    /**
     * Retrieves an object from the layer at the specified coordinates.
     *
     * @param x The x-coordinate of the object.
     * @param y The y-coordinate of the object.
     * @return The object at the specified coordinates.
     */
    public T getObject(int x, int y){
        return this.data[y][x];
    }

    /**
     * Gets the entire data of the layer as a 2D array.
     *
     * @return The 2D array representing the layer's data.
     */
    public T[][] getData(){
        return this.data;
    }

    /**
     * Retrieves a tile object from the layer at the specified coordinates.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The tile at the specified coordinates.
     */
    public Tile getTile(int x, int y){
        return (Tile) this.data[y][x];
    }

    /**
     * Retrieves an actor object from the layer at the specified coordinates.
     *
     * @param x The x-coordinate of the actor.
     * @param y The y-coordinate of the actor.
     * @return The actor at the specified coordinates.
     */
    public Actor getActor(int x, int y){
        return (Actor) this.data[y][x];
    }

    /**
     * Retrieves an item object from the layer at the specified coordinates.
     *
     * @param x The x-coordinate of the item.
     * @param y The y-coordinate of the item.
     * @return The item at the specified coordinates.
     */
    public Item getItem(int x, int y){
        return (Item) this.data[y][x];
    }

    /**
     * Sets a tile at the specified coordinates in the layer.
     *
     * @param x    The x-coordinate where the tile will be set.
     * @param y    The y-coordinate where the tile will be set.
     * @param tile The tile to be set at the specified coordinates.
     */
    public void setTile(int x, int y, Tile tile){
        data[y][x] = (T) tile;
    }

    /**
     * Sets an actor at the specified coordinates in the layer.
     *
     * @param x     The x-coordinate where the actor will be set.
     * @param y     The y-coordinate where the actor will be set.
     * @param actor The actor to be set at the specified coordinates.
     */
    public void setActor(int x, int y, Actor actor){
        data[y][x] = (T) actor;
    }

    /**
     * Sets an item at the specified coordinates in the layer.
     *
     * @param x    The x-coordinate where the item will be set.
     * @param y    The y-coordinate where the item will be set.
     * @param item The item to be set at the specified coordinates.
     */
    public void setItem(int x, int y, Item item){
        data[y][x] = (T) item;
    }

    /**
     * Gets the width of the layer.
     *
     * @return The width of the layer.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the layer.
     *
     * @param width The new width of the layer.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the layer.
     *
     * @return The height of the layer.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the layer.
     *
     * @param height The new height of the layer.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the time limit set for the layer.
     *
     * @return The time limit of the layer.
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Sets a new time limit for the layer.
     *
     * @param timeLimit The new time limit to be set.
     */
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * Retrieves an entity (of type T) from the layer at the specified coordinates.
     * This method is a general form of {@code getObject} and can be used for any type T.
     *
     * @param x The x-coordinate of the entity.
     * @param y The y-coordinate of the entity.
     * @return The entity at the specified coordinates.
     */
    public T getEntity(int x, int y){
        return this.data[y][x];
    }

    /**
     * Sets the data for the layer.
     * This method allows for updating the entire data array of the layer with a new set of data.
     * It can be used to replace the current state of the layer with a new one, represented by the provided 2D array.
     *
     * @param data The new data array to set for the layer.
     */
    public void setData(T[][] data) {
        this.data = data;
    }
}
