import game.PlayerProfile;
import game.ProfileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller class for user-related actions in the game's user interface.
 * This class handles user interactions such as creating or loading user profiles.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.2
 */
public class UserController {

    @FXML
    private VBox createUserBox;

    @FXML
    private TextField userNameField;

    private String nextScene;

    @FXML
    private Button back;

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
     * Sets the next scene to navigate to after creating or loading a user profile.
     *
     * @param nextScene The name of the next scene to navigate to.
     */
    public void setNextScene(String nextScene) {
        this.nextScene = nextScene;
    }

    /**
     * Handles the action when the "Create User" button is clicked, displaying the user creation form.
     */
    @FXML
    private void createUserButtonAction() {
        createUserBox.setVisible(true);
    }

    /**
     * Handles the action when the "Load User" button is clicked.
     * Placeholder for loading user profiles.
     */
    @FXML
    private void loadUserButtonAction() {
        System.out.println("Load User button clicked.");

        // Get the list of user directories in the "saves" directory
        List<String> userDirectories = getUserDirectories();

        // Show a dialog to let the user choose from the list
        String selectedUser = showUserSelectionDialog(userDirectories);

        // Load the selected user profile
        if (selectedUser != null) {
            loadUserProfile(selectedUser);
            System.out.println("Loaded user profile for: " + selectedUser);
        } else {
            System.out.println("User loading canceled.");
        }

        // After loading user, go to the levels scene
        goToLevelsScene();
    }

    /**
     * Handles the action when the "Create User" button is clicked.
     * Creates a new user profile based on the entered username.
     */
    @FXML
    private void createUserAction() {
        String userName = userNameField.getText();

        // Validate userName if needed

        // Create a playerProfileClass with the userName and the first level as the save file
        PlayerProfile playerProfile = new PlayerProfile(userName);
        ProfileManager.setCurrentPlayerProfile(playerProfile);

        // Additional logic to handle the created user profile

        // After creating user, go to the levels scene
        goToLevelsScene();
    }

    /**
     * Navigates to the levels scene in the game's user interface.
     */
    @FXML
    void goToLevelsScene(){
        try {
            Pane root = FXMLLoader.load(getClass().getResource("Fxml/levels.fxml"));
            MainMenuTest.mainScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of user directories found in the "saves" directory.
     *
     * @return A list of user directory names.
     */
    private List<String> getUserDirectories() {
        List<String> userDirectories = new ArrayList<>();

        // Assuming "saves" is a directory in the current working directory
        File savesDirectory = new File("saves");

        if (savesDirectory.exists() && savesDirectory.isDirectory()) {
            File[] userFiles = savesDirectory.listFiles(File::isDirectory);

            if (userFiles != null) {
                for (File userFile : userFiles) {
                    userDirectories.add(userFile.getName());
                }
            }
        }

        return userDirectories;
    }

    /**
     * Loads the user profile for the selected user.
     *
     * @param selectedUser The name of the selected user.
     */
    private void loadUserProfile(String selectedUser) {
        ProfileManager.setCurrentPlayerProfile(new PlayerProfile(selectedUser));
    }

    /**
     * Shows a user selection dialog to choose from the available user directories.
     *
     * @param userDirectories A list of user directory names.
     * @return The selected user's directory name, or null if none is selected.
     */
    private String showUserSelectionDialog(List<String> userDirectories) {
        if (userDirectories.isEmpty()) {
            // Show an alert if there are no user directories
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Users");
            alert.setHeaderText(null);
            alert.setContentText("No users found in the 'saves' directory.");
            alert.showAndWait();
            return null;
        }

        // Show a choice dialog to let the user choose from the list
        ChoiceDialog<String> dialog = new ChoiceDialog<>(userDirectories.get(0), userDirectories);
        dialog.setTitle("Load User");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose a user:");

        return dialog.showAndWait().orElse(null);
    }
}