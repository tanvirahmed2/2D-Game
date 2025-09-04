import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;

/**
 * Controller class for the Game Over screen.
 * Handle actions related to returning to the main menu or restarting the current level.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.7
 */
public class GameOverController {

    @FXML
    private Button menu;

    @FXML
    private Button restart;

    @FXML
    private Label overText;




    public String level;

    private String currentLevelPath = "levels/level1.txt";

    /**
     * Handles the "Main Menu" button click event.
     *
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    void menu(ActionEvent event) {
        try {
            Pane root = FXMLLoader.load(getClass().getResource("Fxml/levels.fxml"));
            MainMenuTest.mainScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sets the text of the game over label.
     *
     * @param text The text to set for the game over label.
     */
    public void setOverText(String text){
        this.overText.setText(text);
    }



    /**
     * Handles the "Restart Level" button click event.
     *
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    void restart(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/levelCanvas.fxml"));
            LevelCanvasController controller = new LevelCanvasController();
            fxmlLoader.setController(controller); // Assuming levelCanvasController is your existing instance
            Pane root = fxmlLoader.load();

            MainMenuTest.mainScene.setRoot(root);

            controller.initialiseLevel(currentLevelPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the path to the current level based on the provided level file.
     *
     * @param level The File object representing the current level.
     */
    public void setCurrentLevel(File level) {
        int levelNumber = extractLevelNumber(level.getPath());
        this.currentLevelPath = "levels/level" + levelNumber + ".txt";

    }

    /**
     * Extracts the numeric part (level number) from a filename.
     *
     * @param fileName The filename from which to extract the level number.
     * @return The extracted level number as an integer.
     */
    private int extractLevelNumber(String fileName) {
        // Extract the numeric part of the filename (assuming it's at the end before the extension)
        String numericPart = fileName.replaceAll("[^0-9]", "");
        return Integer.parseInt(numericPart);
    }





}
