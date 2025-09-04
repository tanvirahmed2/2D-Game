package item;

/**
 * The {@code EmptyItem} class represents an empty or placeholder item within a game.
 * It extends the {@code Item} class, typically used to signify the absence of an actual item in a specific location.
 * This can be useful in various game mechanics, such as indicating an item has been picked up or an empty slot in an inventory.
 *
 * @author Max Holloway, Tanvir Ahmed, Carl Antill, Tomas Williams
 * @version 1.2
 */
public class EmptyItem extends Item {

    /**
     * Constructs a new {@code EmptyItem}.
     * Initializes the empty item with a default state, typically indicating it's not picked up,
     * and sets a predefined key for identification.
     */
    public EmptyItem() {
        super(false);
        this.key = "II";
    }
}
