package level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * The {@code LevelWriter} class is responsible for writing and saving the state of a game level to a file.
 * It outputs various level attributes and configurations, including time limits, dimensions, and the layout
 * of different layers such as tiles, items, and actors.
 *
 * @author Max Holloway, Tanvir Ahmed
 * @version 1.6
 */
public class LevelWriter {

    // Constant message for file not found scenario.
    private static final String FILE_NOT_FOUND = "File not found";

    /**
     * Writes the current state of a {@code Level} object to a file.
     * This method serializes the level's configuration, including its time limit, dimensions,
     * and the content of its layers (tiles, items, actors) into a specified file. If the specified file
     * cannot be created or opened for writing, it throws a {@code FileNotFoundException}.
     *
     * @param filePath The path of the file where the level data will be saved.
     * @param level    The {@code Level} object whose state is to be written to the file.
     * @throws FileNotFoundException if the specified file cannot be created or opened for writing.
     */
    public void writeLevelToFile(String filePath, Level level) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println(level.getTimeLimit());
            writer.println(level.getWidth() + "," + level.getHeight());
            // writes keys in level layout
            writeLayers(writer, level);

        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
            System.exit(0);
        }
    }

    /**
     * Writes a single layer's data to the file.
     * This helper method takes a 2D string array representing a layer and writes each row as a line in the file.
     *
     * @param writer      The {@code PrintWriter} object used for writing to the file.
     * @param stringBlock A 2D string array representing the layer data to be written.
     */
    private static void writeLayerToFile(PrintWriter writer, String[][] stringBlock) {
        for (String[] row : stringBlock) {
            writer.println(String.join(",", row));
        }
    }

    /**
     * Writes the layers of the level (tiles, items, actors) to the file.
     * This method serializes each layer's data and writes it to the file using the {@code PrintWriter}.
     *
     * @param writer The {@code PrintWriter} object used for writing to the file.
     * @param level  The {@code Level} object containing the layers to be written.
     */
    private static void writeLayers(PrintWriter writer, Level level) {
        writeLayerToFile(writer, level.getTiles().writeLayer());
        writeLayerToFile(writer, level.getItems().writeLayer());
        writeLayerToFile(writer, level.getActors().writeLayer());
    }
}

