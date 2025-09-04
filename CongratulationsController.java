import game.ProfileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

/**
 * Controller class for the Congratulations screen.
 * Handle actions related to progressing to the next level or returning to the level selection screen.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.4
 */
public class CongratulationsController {

    @FXML
    private Button nextLevel;

    private String nextLevelPath = "levels/level1.txt";

    @FXML
    private Button backToLevels;

    private boolean showNextLevelButton = true;

    @FXML
    private Label congratulations;

    @FXML
    private Label scoreLabel;

    /**
     * Sets the visibility of the "Next Level" button.
     *
     * @param showNextLevelButton {@code true} to make the "Next Level" button visible, {@code false} to hide it.
     */
    public void setShowNextLevelButton(boolean showNextLevelButton) {
        this.showNextLevelButton = showNextLevelButton;
        nextLevel.setVisible(showNextLevelButton);
    }

    /**
     * Handles the "Next Level" button click event.
     *
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    public void nextLevel(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/levelCanvas.fxml"));
            LevelCanvasController controller = new LevelCanvasController();

            fxmlLoader.setController(controller);

            Pane root = fxmlLoader.load();

            MainMenuTest.mainScene.setRoot(root);

            controller.initialiseLevel(nextLevelPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Moving to the next level!");
    }

    /**
     * Sets the path to the next level based on the current level's file.
     *
     * @param level The File object representing the current level.
     */
    public void setNextLevel(File level) {
        int nextLevelNumber = extractLevelNumber(level.getPath()) + 1;
        this.nextLevelPath = "levels/level" + nextLevelNumber + ".txt";

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

    /**
     * Sets the text of the congratulations label.
     *
     * @param text The text to set for the congratulations label.
     */
    public void setCongratulationsText(String text){
        this.congratulations.setText(text);
    }

    /**
     * Handles the "Back to Levels" button click event.
     *
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    void backToLevels(ActionEvent event) {
        try {
            Pane root = FXMLLoader.load(getClass().getResource("Fxml/levels.fxml"));
            MainMenuTest.mainScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the score label with the provided score value.
     *
     * @param score The score to be displayed in the label.
     */
    public void setScoreLabel(int score){
        scoreLabel.setText("Score: " + score);
    }
}