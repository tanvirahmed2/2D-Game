package game;

import item.Chip;
import item.Item;

import java.util.ArrayList;

/**
 * The {@code PlayerInventory} class represents a player's inventory in a game.
 * It manages the collection of items a player has picked up, including chips and other items.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.4
 */
public class PlayerInventory {

    // List to store the items in the player's inventory.
    private ArrayList<Item> inventory = new ArrayList<>();
    // Count of chips collected by the player.
    private int chipCount;

    /**
     * Constructs a {@code PlayerInventory} with an initial chip count of 0.
     */
    public PlayerInventory() {
        this.chipCount = 0;
    }

    /**
     * Uses an item from the inventory.
     * This method can be expanded to include logic for using specific types of items.
     */
    public void useItem() {
        for (Item item : inventory) {

        }
    }

    /**
     * Picks up an item and adds it to the inventory.
     * If the item is a chip, increments the chip count instead of adding to the inventory.
     *
     * @param item The item to be picked up.
     */
    public void pickupItem(Item item) {
        if (item.getKey().equals("CN")) {
            this.chipCount += 1;
        } else {
            inventory.add(item);
        }
        System.out.println("picked up" + item.getKey());
    }

    /**
     * Simulates putting down an item from the inventory.
     * Returns the modified key of the item when it's put down.
     *
     * @param item The item to be put down.
     * @return The modified key of the item.
     */
    public String putDown(Item item) {
        return item.getKey().charAt(0) + "N";
    }

    /**
     * Adds an item to the inventory. If the item is a chip, increments the chip count.
     *
     * @param item The item to be added to the inventory.
     */
    public void pickUp(Item item) {
        if (item instanceof Chip) {
            chipCount += 1;
        } else {
            inventory.add(item);
        }

    }

    /**
     * Retrieves the list of items in the player's inventory.
     *
     * @return The list of items in the inventory.
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Sets the list of items in the player's inventory.
     *
     * @param inventory The new list of items to be set as the inventory.
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Gets the count of chips in the inventory.
     *
     * @return The number of chips collected.
     */
    public int getChipCount() {
        return chipCount;
    }

    /**
     * Sets the count of chips in the inventory.
     *
     * @param chipCount The new chip count.
     */
    public void setChipCount(int chipCount) {
        this.chipCount = chipCount;
    }
}
