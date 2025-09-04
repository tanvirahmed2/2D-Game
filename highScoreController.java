import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Controller class for the High Score screen.
 * Handle actions related to navigating back to the main menu.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.4
 */
public class highScoreController {


    @FXML
    private Button back;


    @FXML
    /*void back(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/test.fxml"));
            Pane root = fxmlLoader.load();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current stage

            // Close the current stage
            currentStage.close();

            // Open a new stage with the loaded FXML
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Handles the "Back" button click event to return to the main menu.
     *
     * @param event The ActionEvent associated with the button click.
     */
    void back(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/test.fxml"));
            Pane root = fxmlLoader.load();
            MainMenuTest.mainScene.setRoot(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
