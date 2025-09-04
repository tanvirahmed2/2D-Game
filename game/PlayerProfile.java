package game;

import level.Level;

import java.io.File;
import java.io.IOException;

/**
 * The {@code PlayerProfile} class represents a player's profile in the game.
 * It includes information about the player, such as their name and current level,
 * and manages saving game states specific to the player.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.3
 */
public class PlayerProfile {

    // File object representing the directory for this user's data.
    private File userFile;
    // File object representing the save file for the current level.
    private File saveFile;
    // Name of the player.
    private String playerName;
    // Static counter to assign a unique ID to each user.
    private static int userNumber = 1;

    private int highestLevel = 1;


    private String lastSavePath;


    private int userId;
    // Variable to keep track of the current level the player is on.
    private int currentLevel = 1;  // Added a variable to keep track of the current level
    // Base directory for saving game data.
    private static final String BASE_DIRECTORY = "saves";

    /**
     * Constructs a new PlayerProfile with the specified player name.
     * Initializes directories for saving the player's game data.
     *
     * @param name The name of the player.
     */
    public PlayerProfile(String name) {
        this.playerName = name;
        this.userId = userNumber;
        this.userFile = new File(BASE_DIRECTORY, playerName); // Use playerName as the directory name
        this.saveFile = new File(userFile, "level" + currentLevel + ".txt"); // Use playerName for the directory and a default level file
        userNumber++;

        File baseDirectory = new File(BASE_DIRECTORY);
        if (!baseDirectory.exists()) {
            baseDirectory.mkdirs();
        }

        if (!userFile.exists()) {
            userFile.mkdirs();
        }


    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHighestLevel() {
        return highestLevel;
    }

    public void setHighestLevel(int highestLevel) {
        this.highestLevel = highestLevel;
    }

    /**
     * Saves the current state of a game level to a file.
     * This method writes the level data to a file specific to the player's profile.
     *
     * @param level The Level object representing the current state of the game to be saved.
     */
    public void saveGame(Level level) {
        int levelNumber = level.getLevelNumber();
        String filename = "level" + levelNumber + ".txt";
        File levelFile = new File(userFile, filename); // Save the level file directly in the user's directory

        // Ensure the directory exists
        if (!levelFile.getParentFile().exists()) {
            levelFile.getParentFile().mkdirs();
        }

        try {
            // Create the level file if it doesn't exist
            if (!levelFile.exists()) {
                levelFile.createNewFile();
            }

            // Write the level content to the file
            level.writeLevel(levelFile.getPath());
            lastSavePath = levelFile.getPath();
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception as needed
        }
    }


    public String getLastSavePath() {
        return lastSavePath;
    }


    /**
     * Retrieves the user file directory.
     *
     * @return The {@code File} object representing the directory for the user's data.
     */
    public File getUserFile() {
        return userFile;
    }

    /**
     * Sets the user file directory.
     *
     * @param userFile The new File object representing the user's data directory.
     */
    public void setUserFile(File userFile) {
        this.userFile = userFile;
    }

    /**
     * Retrieves the save file for the current level.
     *
     * @return The File object representing the save file for the current level.
     */
    public File getSaveFile() {
        return saveFile;
    }

    /**
     * Sets the save file for the current level.
     *
     * @param saveFile The new File object representing the save file for the current level.
     */
    public void setSaveFile(File saveFile) {
        this.saveFile = saveFile;
    }



    /**
     * Sets the player's name.
     *
     * @param playerName The new name to be set for the player.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Retrieves the static user number, used to assign unique IDs to users.
     *
     * @return The current static user number.
     */
    public static int getUserNumber() {
        return userNumber;
    }

    /**
     * Sets the static user number, used to assign unique IDs to users.
     *
     * @param userNumber The new static user number to be set.
     */
    public static void setUserNumber(int userNumber) {
        PlayerProfile.userNumber = userNumber;
    }

    /**
     * Retrieves the unique ID of the user.
     *
     * @return The unique user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique ID of the user.
     *
     * @param userId The new unique ID to be set for the user.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the current level number that the player is on.
     *
     * @return The current level number.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Sets the current level number that the player is on.
     *
     * @param currentLevel The new level number to be set.
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Advances the player to the next level and updates the save file reference.
     * This method increments the current level and prepares the save file for the next level.
     */

    public void nextLevel() {
        currentLevel++;
        String filename = "level" + currentLevel + ".txt";
        saveFile = new File(userFile, filename);
    }

    public void updateLevel(){
        if (highestLevel <= currentLevel){
            highestLevel = currentLevel;
        }
    }

    @Override
    public String toString(){
        return playerName + " " + highestLevel;
    }




}