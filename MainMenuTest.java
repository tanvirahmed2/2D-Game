

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class for the "Frog dominating world chips challenge" game.
 * This class sets up the main scene and stage for the game's user interface.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.5
 */
public class MainMenuTest extends Application {

    //The main scene for the game's user interface.
    public static Scene mainScene;
    //The primary stage for the game's user interface.
    public static Stage primaryStage;

    /**
     * The entry point for launching the game application.
     *
     * @param primaryStage The primary stage for the game's user interface.
     */
    public void start(Stage primaryStage) {
        MainMenuTest.primaryStage = primaryStage;
        try {
            Pane root = FXMLLoader.load(getClass().getResource("Fxml/test.fxml"));
            mainScene = new Scene(root, 900,650);
            mainScene.setFill(Color.BLUE);

            primaryStage.setTitle("Frog dominating world chips challenge");
            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method to launch the game application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}