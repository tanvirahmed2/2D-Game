package level;

import actor.Actor;

import java.awt.*;

/**
 * The {@code KeyFactory} class provides static utility methods to interpret keys
 * and extract specific properties such as color, picked-up status, open status,
 * and direction for various game elements like items and actors.
 * This class serves as a centralized point for decoding information encoded in keys
 * used throughout the game, enhancing the readability and maintainability of the code.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.4
 */
public class KeyFactory {

    /**
     * Extracts the color from a given key.
     * The method interprets the first character of the key as a color indicator.
     *
     * @param key The key from which the color is to be extracted.
     * @return The {@code Color} corresponding to the key, or {@code null} if no matching color is found.
     */
    static Color getColorFromKey(String key) {
        return switch (key.substring(0, 1)) {
            case "R" -> Color.RED;
            case "G" -> Color.GREEN;
            case "B" -> Color.BLUE;
            case "Y" -> Color.YELLOW;
            default -> null;
        };
    }

    /**
     * Checks if an item has been picked up based on its key.
     * The method looks at the second character of the key to determine the picked-up status.
     *
     * @param key The key representing the item.
     * @return {@code true} if the item is marked as picked up, {@code false} otherwise.
     */
    static Boolean checkPickedUp(String key) {
        return key.charAt(1) == 'P';
    }

    /**
     * Checks if a door or similar object is open based on its key.
     * The method looks at the second character of the key to determine the open status.
     *
     * @param key The key representing the door or object.
     * @return {@code true} if the object is marked as open, {@code false} otherwise.
     */
    static Boolean checkOpen(String key) {
        return key.charAt(1) == 'L';
    }

    /**
     * Gets the direction associated with an actor from its key.
     * The method interprets the second character of the key as a direction indicator.
     *
     * @param key The key representing the actor.
     * @return The {@code Actor.Direction} corresponding to the key, or {@code null} if no matching direction is found.
     */
    public static Actor.Direction getDirectionFromKey(String key) {
        return switch (key.charAt(1)) {
            case 'U' -> Actor.Direction.UP;
            case 'D' -> Actor.Direction.DOWN;
            case 'L', 'W' -> Actor.Direction.LEFT;
            case 'R', 'E' -> Actor.Direction.RIGHT;
            default -> null;
        };
    }
}

