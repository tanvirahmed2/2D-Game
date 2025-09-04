package game;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


import java.util.Scanner;

/**
 * The {@code GameStuff} class encapsulates various functionalities for game management,
 * such as score calculation, saving/loading game states, and restarting levels.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.7
 */
public class GameStuff {

    /**
     * Calculates the score based on the time left and the number of chips collected.
     * The method can be expanded to include functionality for appending the score to a highscore table.
     *
     * @param timeLeft   The remaining time in the game.
     * @param chipsTaken The number of chips collected.
     */
    public void scoreCalc(int timeLeft, int chipsTaken) {

        int highScore = timeLeft * chipsTaken;
        // Implementation details for appending the score and username to a highscore table.
    }

    /**
     * Saves the current game state to a file.
     * This method writes a representation of the game state to a file for later retrieval.
     */
    public static void saveGame() {
        // generic write file
      try {
            FileWriter writer = new FileWriter("test.txt");
            writer.write("testing");
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Loads a game state from a file.
     * This method reads the game state from a file and processes it to restore the game.
     */
    public static void loadGame() {
        try {
            File file = new File("test.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Process each line of the file here
                System.out.println(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restarts the current level.
     * This method reloads the game, assuming the level file does not change.
     */
    public void restartLevel() {
        // reloads the game assuming the level file doesnt change
        loadGame();
    }

    /**
     * Starts the level.
     * This method is intended to initialize and start a level,
     * requiring data from the Level class and other game components.
     */
    public void Start() {
        // Method to start the level, needs data from level
        // class and most of the classes to be completed
    }


}
