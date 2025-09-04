import game.ProfileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Controller class for the level selection screen.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.5
 */
public class LevelController {

    @FXML
    private Button level1Button;

    @FXML
    private Button level2Button;

    @FXML
    private Button level3Button;

    @FXML
    private Button level4Button;

    @FXML
    private Button back;

    public String level;

    /**
     * Event handler for the "Back" button. Returns to the main menu screen.
     *
     * @param event The action event triggered by clicking the "Back" button.
     */
    @FXML
    void back(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/test.fxml"));
            Pane root = fxmlLoader.load();
            MainMenuTest.mainScene.setRoot(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Event handler for the "Level 1" button. Loads and initializes level 1.
     *
     * @param event The action event triggered by clicking the "Level 1" button.
     */
    @FXML
    private void initialize() {
        int highestLevel = ProfileManager.getCurrentPlayerProfile().getHighestLevel();

        level1Button.setVisible(highestLevel >= 1);
        level2Button.setVisible(highestLevel >= 2);
        level3Button.setVisible(highestLevel >= 3);
        level4Button.setVisible(highestLevel >= 4);

    }

    @FXML
    void level1Button(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/levelCanvas.fxml"));
            LevelCanvasController controller = new LevelCanvasController();

            fxmlLoader.setController(controller);

            Pane root = fxmlLoader.load();
            level = "levels/level1.txt";
            MainMenuTest.mainScene.setRoot(root);
            controller.initialiseLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Event handler for the "Level 2" button. Loads and initializes level 2.
     *
     * @param event The action event triggered by clicking the "Level 2" button.
     */
    @FXML
    void level2Button(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/levelCanvas.fxml"));
            LevelCanvasController controller = new LevelCanvasController();

            fxmlLoader.setController(controller);

            Pane root = fxmlLoader.load();
            level = "levels/level2.txt";
            MainMenuTest.mainScene.setRoot(root);
            controller.initialiseLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Event handler for the "Level 3" button. Loads and initializes level 3.
     *
     * @param event The action event triggered by clicking the "Level 3" button.
     */
    @FXML
    void level3Button(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/levelCanvas.fxml"));
            LevelCanvasController controller = new LevelCanvasController();

            fxmlLoader.setController(controller);

            Pane root = fxmlLoader.load();
            level = "levels/level3.txt";
            MainMenuTest.mainScene.setRoot(root);
            controller.initialiseLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Event handler for the "Level 4" button. Loads and initializes level 4.
     *
     * @param event The action event triggered by clicking the "Level 4" button.
     */
    @FXML
    void level4Button(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/levelCanvas.fxml"));
            LevelCanvasController controller = new LevelCanvasController();

            fxmlLoader.setController(controller);

            Pane root = fxmlLoader.load();
            level = "levels/level4.txt";
            MainMenuTest.mainScene.setRoot(root);
            controller.initialiseLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
