package level;

import actor.Actor;
import item.Item;
import tile.Tile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The {@code LevelReader} class is responsible for reading and parsing level data from a file.
 * It extracts various level attributes like time limit, level number, and layer configurations
 * to construct a complete {@code Level} object.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.6
 */
public class LevelReader {




    // Constant message for file not found scenario.
    private static final String FILE_NOT_FOUND = "File not found";

    /**
     * Reads level data from a specified file and populates a {@code Level} object.
     * This method handles the extraction of level details and layer data, setting them in the provided {@code Level} object.
     * If the specified file is not found, it throws a {@code FileNotFoundException}.
     *
     * @param fileName The name of the file containing level data.
     * @param level    The {@code Level} object to be populated with the extracted data.
     * @throws FileNotFoundException if the specified file cannot be found.
     */
    public static void readLevelFromFile(String fileName, Level level) {
        File levelFile = new File(fileName);
        try {
            Scanner in = new Scanner(levelFile);

            // Reading time limit from the file.
            int timeLimit = in.nextInt();
            in.nextLine();

            // Extracting level number from the file name.
            int levelNumber = extractLevelNumber(fileName);
            level.setLevelNumber(levelNumber);

            // Parsing the size of the level
            String[] size = in.nextLine().split(",");
            int width = Integer.parseInt(size[0]);
            int height = Integer.parseInt(size[1]);

            // Reading and constructing each layer: tiles, items, actors.
            Layer<Tile> tiles = new Layer<>(width, height, timeLimit, readBlock(in, width, height));
            Layer<Item> items = new Layer<>(width, height, timeLimit, readBlock(in, width, height));
            Layer<Actor> actors = new Layer<>(width, height, timeLimit, readBlock(in, width, height));

            // Setting extracted data to the Level object.
            level.setTimeLimit(timeLimit);
            level.setWidth(width);
            level.setHeight(height);

            level.setTiles(tiles);
            level.setItems(items);
            level.setActors(actors);

            in.close();


        } catch (FileNotFoundException fnf) {
            System.out.println(FILE_NOT_FOUND);
            System.exit(0);
        }
    }

    /**
     * Extracts the level number from the file name.
     * Assumes the level number is the numeric part in the file name.
     *
     * @param fileName The file name from which to extract the level number.
     * @return The extracted level number.
     */
    private static int extractLevelNumber(String fileName) {
        // Extract the numeric part of the filename (assuming it's at the end before the extension)
        String numericPart = fileName.replaceAll("[^0-9]", "");
        return Integer.parseInt(numericPart);
    }

    /**
     * Reads a block of data from the scanner, representing a layer in the level.
     * Each row of the layer is read and split into individual elements.
     *
     * @param in     The scanner to read from.
     * @param width  The width of the layer.
     * @param height The height of the layer.
     * @return A 2D array of strings representing the layer data.
     */
    private static String[][] readBlock(Scanner in, int width, int height) {
        String[][] block = new String[height][width];
        for (int i = 0; i < height && in.hasNextLine(); i++) {
            String layerRow = in.nextLine();

            block[i] = layerRow.split(",");
        }
        return block;
    }





}
