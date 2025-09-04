package game;

import java.io.*;
import java.util.HashMap;

/**
 * The {@code HighScoreTable} class manages the high scores for different levels in a game.
 * It provides functionalities to add, retrieve, display, save, and load high scores.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.4
 */
public class HighScoreTable {

    // File path for the CSV file where high scores are stored.
    private final static String CSV_FILE_PATH = "/home/Fwuffypc/Documents/230 cw/ChipsChallengeCW/test.csv";
    // HashMap to store high scores with the level as the key and the score as the value.
    private static HashMap<String, Integer> highScores;

    /**
     * Constructs a {@code HighScoreTable} and initializes the high scores HashMap.
     */
    public HighScoreTable() {
        highScores = new HashMap<>();
    }

    /**
     * Adds or updates a high score for a specified level.
     *
     * @param level The level for which to set the high score.
     * @param score The score to set as the high score for the level.
     */
    public void addHighScore(String level, int score) {
        highScores.put(level, score);
    }

    /**
     * Retrieves the high score for a specific level.
     *
     * @param level The level for which to get the high score.
     * @return The high score for the specified level, or -1 if the level is not found.
     */
    public int getHighScore(String level) {
        return highScores.getOrDefault(level, -1); // Return -1 if the level is not found
    }

    /**
     * Displays all high scores stored in the table.
     */
    public void displayHighScores() {
        System.out.println("High Scores:");
        for (String level : highScores.keySet()) {
            int score = highScores.get(level);
            System.out.println(level + ": " + score);
        }
    }

    /**
     * Saves all high score data from the HashMap to an existing CSV file.
     * The method writes each level's high score as a line in the CSV file.
     */
    public void saveHighScoreTable() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH))) {
            highScores.forEach((level, score) -> writer.println(level + "," + score));
            System.out.println("High score table saved successfully to " + CSV_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving high score table: " + e.getMessage());
        }
    }

    /**
     * Loads the high score table from a CSV file.
     * This method reads each line from the file and updates the high score HashMap.
     */
    public void loadHighScoreTable() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            highScores.clear();
            reader.lines().map(line -> line.split(",")).forEach(parts -> highScores.put(parts[0], Integer.parseInt(parts[1])));
            System.out.println("High score table loaded successfully from " + CSV_FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("No previous high score table found. Starting with an empty table.");
        } catch (IOException e) {
            System.err.println("Error loading high score table: " + e.getMessage());
        }
    }
    /**
     * Retrieves the high scores map containing scores for different levels.
     * Each entry in the map consists of a level identifier as the key and its corresponding high score as the value.
     *
     * @return A {@code HashMap<String, Integer>} representing the high scores for different levels.
     */
    public static HashMap<String, Integer> getHighScores() {
        return highScores;
    }
    /**
     * Sets the high scores map for different levels.
     * This method allows updating or replacing the existing high scores with a new map.
     *
     * @param highScores A {@code HashMap<String, Integer>} representing the new high scores to be set for different levels.
     */
    public static void setHighScores(HashMap<String, Integer> highScores) {
        HighScoreTable.highScores = highScores;
    }
}

