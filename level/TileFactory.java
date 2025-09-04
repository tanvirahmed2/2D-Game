package level;

import tile.*;
import static level.KeyFactory.checkOpen;
import static level.KeyFactory.getColorFromKey;

/**
 * The {@code TileFactory} class is a utility class for creating instances of various {@code Tile} objects.
 * It uses a factory pattern to generate tiles based on a key, which dictates the type of tile to be created.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.4
 */
public class TileFactory {

    /**
     * Creates a tile based on a specified key.
     * The method supports various tile types, including path, dirt, exit, water, wall, door, ice, and others.
     * For some tile types, additional properties such as color or number are derived from the key.
     *
     * @param key The key representing the type of tile to be created.
     * @return An instance of the specified tile type, or {@code null} if the key does not correspond to any tile type.
     */
    static Tile tileKey(String key) {
        return switch (key) {
            case "PA" -> new Path();
            case "DI" -> new Dirt();
            case "EX" -> new Exit();
            case "WT" -> new Water();
            case "WA" -> new Wall();
            case "RL", "RU", "GL", "GU", "YL", "YU", "BL", "BU" -> new Door(getColorFromKey(key), checkOpen(key));
            case "IC", "SE", "SW", "NE", "NW" -> new Ice(key);
            default -> {
                if (key.startsWith("B") || key.startsWith("T") || key.startsWith("C")) {
                    int number = Integer.parseInt(key.substring(1));
                    yield switch (key.charAt(0)) {
                        case 'B' -> new GameButton(number);
                        case 'T' -> new Trap(number);
                        case 'C' -> new ChipSocket(number);
                        default -> null;
                    };
                }
                yield null;
            }
        };
    }
}
