package level;

import item.Chip;
import item.ColourKey;
import item.EmptyItem;
import item.Item;

import static level.KeyFactory.checkPickedUp;
import static level.KeyFactory.getColorFromKey;

/**
 * The {@code ItemFactory} class provides a static method for creating item instances based on a given key.
 * This factory pattern is utilized to generate different types of game items,
 * such as chips, color keys, and empty items, corresponding to specific keys.
 * The factory method simplifies the process of item instantiation, ensuring a consistent
 * and centralized way of creating game items across different levels.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.4
 */
public class ItemFactory {

    /**
     * Creates an item based on a specified key.
     * This method supports various item types including Chips, ColourKeys, and EmptyItems.
     * For some items, additional properties such as picked-up status or color are derived from the key.
     *
     * @param key The key representing the type of item to be created.
     * @return An instance of the specified item type, or {@code null} if the key does not correspond to any item.
     */
    public static Item itemKey(String key) {
        return switch (key) {
            case "CN", "CP" -> new Chip(checkPickedUp(key));
            case "RN", "RP", "GN", "GP", "YN", "YP", "BN", "BP" ->
                    new ColourKey(checkPickedUp(key), getColorFromKey(key));
            case "II" -> new EmptyItem();
            default -> null;
        };
    }
}
