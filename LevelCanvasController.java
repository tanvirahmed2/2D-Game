

import actor.Actor;
import actor.Frog;
import actor.PinkBall;
import actor.Player;
import game.*;
import item.Item;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import level.Level;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tile.Dirt;
import tile.Path;
import tile.Tile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static level.ItemFactory.itemKey;
import static level.KeyFactory.getDirectionFromKey;

/**
 * Controller class for the game level canvas.
 * Handle actions related to gameplay and level display.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 2.1
 */
public class LevelCanvasController {
    @FXML
    private Canvas canvas1;
    private GridClass grid;
    public Player playerCharacter;
    private Frog frog;
    private PinkBall pinkBall;
    public Level levelData;
    private String levelPath;
    private boolean isGamePaused = false;

    @FXML
    private Button menu;

    @FXML
    private Label timerLabel;

    private int SPRITE_SIZE = 60;

    private int timeRemaining;

    private Timeline countdownTimeLine;
    private boolean isPaused;

    @FXML
    private ImageView itemImage1;
    @FXML
    private ImageView itemImage2;
    @FXML
    private ImageView itemImage3;
    @FXML
    private ImageView itemImage4;
    @FXML
    private ImageView chipImage;
    @FXML
    private Label chipCountLabel;



    private final int PLAYER_TICK = 3;
    private final int MONSTER_TICK = 5;

    private int playerTickCounter = 0;
    private int monsterTickCounter = 0;
    private AnimationTimer gameLoop;


    private PlayerInventory inventory = new PlayerInventory();


    private final String PLAYER_DIED = "You Died";
    private final String RAN_OUT_OF_TIME = "Ran Out Of Time";
    private final String LEVEL_COMPLETED = "You Finished The Level!";


    /**
     * A mapping of keys to image file paths for various game elements.
     * This map associates unique keys with the file paths to images used to represent
     * different tiles, items, doors, and actors in the game.
     *
     * <p>The keys are strings that represent specific game elements, and the values are
     * the file paths to the corresponding image files.</p>
     */
    final Map<String, String> KEY_TO_IMAGE = new HashMap<String, String>() {{
        // Tiles
        put("PA", "png/path.png");
        put("DI", "png/dirt.png");
        put("WT", "png/water.png");
        put("EX", "png/exit.png");
        put("BC", "png/block.png");
        put("T1", "png/trap.png");
        put("B1", "png/button.png");
        put("WA", "png/wall.png");

        put("IC", "png/Ice.png");
        put("SE", "png/IceBottomRight.png");
        put("SW", "png/IceBottomLeft.png");
        put("NE", "png/IceTopRight.png");
        put("NW", "png/IceTopLeft.png");
        put("C2", "png/chipSocket.png");

        //Doors
        put("RL", "png/redDoor.png");
        put("BL", "png/blueDoor.png");
        put("YL", "png/yellowDoor.png");
        put("GL", "png/greenDoor.png");

        // items
        put("RN", "png/redKey.png");
        put("BN", "png/blueKey.png");
        put("YN", "png/yellowKey.png");
        put("GN", "png/greenKey.png");
        put("CN", "png/chip.png");
        // Actor
        put("PY", "png/player.png");
        put("BE", "png/bug.png");
        put("BW", "png/bug.png");
        put("FG", "png/frog.png");
        put("PU", "png/pinkBall.png");
        put("PL", "png/pinkBall.png");
        put("PR", "png/pinkBall.png");
        put("PD", "png/pinkBall.png");

    }};


    /**
     * Initializes the game level with the specified level path.
     *
     * @param levelPath The path to the level data.
     */
    public void initialiseLevel(String levelPath) {
        this.levelPath = levelPath;
        levelData = new Level(levelPath);
        isGamePaused = false;

        pickUpUnseen();
        ProfileManager.getCurrentPlayerProfile().updateLevel();
        System.out.println(ProfileManager.getCurrentPlayerProfile());


        canvas1.setWidth(getLevel().getWidth() * SPRITE_SIZE);
        canvas1.setHeight(getLevel().getHeight() * SPRITE_SIZE);
        GraphicsContext gc = canvas1.getGraphicsContext2D();



        // Create an instance of GridClass with the Layer instance
        grid = new GridClass(gc, levelData);
        // Draw the grid
        grid.drawGrid();
        canvas1.setFocusTraversable(true);
        canvas1.setOnKeyPressed(this::handleKeyPressed);
        drawLayers();

        timeRemaining = this.levelData.getTimeLimit();
        startCountdownTimer();
        startGameLoop();
    }

    /**
     * Retrieves the item at the specified coordinates in the level.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The item at the specified coordinates.
     */
    public Item getItem(int x, int y){
        return this.levelData.getItems().getItem(x, y);
    }

    /**
     * Performs the pick-up action for the player character.
     */
    public void pickUp() {
        int x = playerCharacter.getXPos();
        int y = playerCharacter.getYPos();

        if (getItem(x, y).getKey() != "II") {
            if (!getItem(x, y).getPickedUp()) {
                inventory.pickUp(itemKey(getItem(x, y).getKey()));
                getItem(x, y).pickUp();

            }
        }
    }


    public void onFrog(){
        int px = playerCharacter.getXPos();
        int py = playerCharacter.getyPos();
        int fx = frog.getXPos();
        int fy = frog.getYPos();
        if (px == fx && py == fy){
            playerCharacter.setIsAlive(false);
        }
    }
    /**
     * Handles the game tick, which represents a unit of time in the game.
     * This method is responsible for various game-related actions and updates.
     */
    private void gameTick() {
        if (!isPaused){
            if (playerTickCounter >= PLAYER_TICK) {
                handleKeyPressed(null);
                pickUp(); // Perform the pickUp() action

                // Schedule focus change to the main canvas after pickUp()
                Platform.runLater(() -> canvas1.requestFocus());

                updateInventory(inventory);
                onDirt();
                onFrog();
                //checkPlayerOnIce();

                if (playerCharacter.getOnExit()) {
                    nextLevel();
                    gameLoop.stop();
                    showCongratulations(this.levelData);
                    this.levelData.writeLevel("level/saveFile.txt");
                }

                if (!playerCharacter.getIsAlive()) {
                    gameLoop.stop();
                    showGameOverScene(PLAYER_DIED);
                }

                if (timeRemaining == 0) {
                    gameLoop.stop();
                    showGameOverScene(RAN_OUT_OF_TIME);
                }
                grid.clearGrid();
                drawLayers();
                playerTickCounter = 0;
            }
            playerTickCounter++;

        }
        if (monsterTickCounter >= MONSTER_TICK) {
            frog.moveTowardsPlayer(playerCharacter.getXPos(), playerCharacter.getYPos());
            pinkBall.setNextMove();
            monsterTickCounter = 0;

        }
        monsterTickCounter++;
    }




    /**
     * Iterates through all items in the level grid and adds them to the player's inventory if they have been picked up.
     */
    private void pickUpUnseen() {
        for (int y = levelData.getHeight() - 1; y >= 0; y--) {
            for (int x = levelData.getWidth() - 1; x >= 0; x--) {
                if (this.levelData.getItems().getItem(x,y).getPickedUp()){
                    inventory.pickUp(this.levelData.getItems().getItem(x,y));
                }
            }
        }
    }

    /**
     * Advances the player to the next level if the current level's number is higher than their highest completed level.
     * This method checks if the current level's number is greater than the highest level completed by the player.
     * If so, it advances the player to the next level.
     */
    public void nextLevel(){
        int highest = ProfileManager.getCurrentPlayerProfile().getHighestLevel();
        if (highest <= this.levelData.getLevelNumber()){
            ProfileManager.getCurrentPlayerProfile().nextLevel();
        }
    }


    /**
     * Handles the player's interaction with dirt tiles. When the player steps on a dirt tile, it is converted into a path tile.
     * This method determines the player's current position and checks if the tile at that position is of type "Dirt."
     * If it is, the tile is replaced with a "Path" tile, signifying that the player has walked over it.
     */
    private void onDirt(){
        int x = playerCharacter.getXPos();
        int y = playerCharacter.getYPos();

        Tile tile = levelData.getTiles().getTile(x, y);
        if (tile instanceof Dirt){
            tile = new Path();
            levelData.getTiles().setTile(x, y, tile);
        }
    }

    /**
     * Pauses the game when the "Pause" button is clicked.
     *
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    void pause(ActionEvent event) {
        isPaused = !isPaused;

    }

    /**
     * Resumes the game when the "Play" button is clicked.
     *
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    void load(ActionEvent event) {
        // Construct the path to the level file
        String levelFilePath = ProfileManager.getCurrentPlayerProfile().getLastSavePath();
        File levelFile = new File(levelFilePath);
        //this.inventory.setChipCount(inventory.getChipCount() - inventory.getChipCount());

        // Check if the level file exists before attempting to initialize the level
        gameLoop.stop();
        countdownTimeLine.stop();
        initialiseLevel(levelFilePath);

    }

    /**
     * Saves the current game state when the "Save" button is clicked.
     *
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    void save(ActionEvent event){
        ProfileManager.getCurrentPlayerProfile().saveGame(levelData);
        System.out.println("Saved Game");
    }

    /**
     * Starts the game loop timer for continuous game updates.
     */
    private void startGameLoop() {
        if (gameLoop == null) {
            final long targetNanoSecondInterval = 1_000_000_000L / 25; // 1 second divided by 25 frames
            gameLoop = new AnimationTimer() {
                long lastTime = 0;
                @Override
                public void handle(long now) {
                    if (!isPaused) {
                        // Calculate elapsed time since the last frame
                        long elapsedNanoSeconds = now - lastTime;

                        // Check if enough time has passed to update the game
                        if (elapsedNanoSeconds >= targetNanoSecondInterval) {
                            gameTick();
                            lastTime = now;
                        }
                    }
                }
            };
        }
        gameLoop.start(); // Start the timer
    }


    /**
     * Handles the "Menu" button click event. Stops the game loop if it's running and returns to the main menu.
     *
     * @param event The ActionEvent associated with the button click.
     */
    @FXML
    void menu(ActionEvent event) {
        if (gameLoop != null) {
            gameLoop.stop(); // Stop the game loop
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/levels.fxml"));
            Pane root = fxmlLoader.load();
            MainMenuTest.mainScene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the game over scene with the specified cause of the game ending.
     *
     * @param causeOfEnd The cause of the game ending (e.g., "You Died", "Ran Out Of Time").
     */
    private void showGameOverScene(String causeOfEnd) {
        try {
            // Load the FXML for the game over scene
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/gameOver.fxml"));
            Parent gameOverRoot = fxmlLoader.load();

            // Access the controller associated with the loaded FXML
            GameOverController gameOverController = fxmlLoader.getController();

            gameOverController.setCurrentLevel(new File(levelPath));

            // Set the result label text
            gameOverController.setOverText(causeOfEnd);

            // Get the root of the current scene
            Scene currentScene = canvas1.getScene();

            // Replace the root of the current scene with the game over root
            currentScene.setRoot(gameOverRoot);

            // Update the title of the stage
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.setTitle("Game Over");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the congratulations scene when a level is completed.
     *
     * @param level The completed level object.
     */
    private void showCongratulations(Level level) {
        try {
            // Load the FXML for the congratulations scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/completed.fxml"));
            Parent congratulationsRoot = loader.load();

            // Access the controller associated with the loaded FXML
            CongratulationsController controller = loader.getController();
            int score = ScoreCalc.calc(inventory.getChipCount(), timeRemaining);
            controller.setScoreLabel(score);


            // Assuming your Level class has a method to get the current level number
            int completedLevelNumber = level.getLevelNumber();

            if (ProfileManager.getCurrentPlayerProfile().getHighestLevel() == completedLevelNumber){
                ProfileManager.getCurrentPlayerProfile().setHighestLevel(
                        ProfileManager.getCurrentPlayerProfile().getHighestLevel() + 1);
            }


            controller.setNextLevel(new File(levelPath));

            if (completedLevelNumber >= 4) {
                // Hide the "Next Level" button if the level number is greater than or equal to 4
                controller.setShowNextLevelButton(false);
            }

            // Get the root of the current scene
            Scene currentScene = canvas1.getScene();

            // Replace the root of the current scene with the congratulations root
            currentScene.setRoot(congratulationsRoot);

            // Update the title of the stage
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.setTitle("Congratulations");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the player's inventory.
     */
    private void showInventory(){

    }


    /**
     * Proceeds to the next level in the game.
     */
    private void showNextLevel(){

    }

    /**
     * Draws the layers of the game including tiles, actors, and items.
     */
    public void drawLayers(){
        drawLayer(levelData.getTiles().writeLayer());
        drawLayer(levelData.getActors().writeLayer());
        drawLayer(levelData.getItems().writeLayer());
    }



    /**
     * Updates the inventory box based on the player's inventory.
     *
     * @param inventory The player's inventory.
     */
    public void updateInventory(PlayerInventory inventory) {
        ArrayList<Item> inv = inventory.getInventory();
        int chipCount = inventory.getChipCount();
        int maxItems = Math.min(inv.size(), 4); // Limit to 4 items for itemImage2 to itemImage4

        // Set images for itemImage1 to itemImage4 based on the inventory
        for (int i = 0; i < maxItems; i++) {
            ImageView currentItemImageView = getItemImageView(i + 1); // i + 1 because itemImage1 is already set
            currentItemImageView.setImage(new Image(KEY_TO_IMAGE.get(inv.get(i).getKey())));
        }

        // Update chip count label and chip image
        if (chipCount > 0) {
            chipCountLabel.setText("Count: " + String.valueOf(chipCount));
            chipImage.setImage(new Image(KEY_TO_IMAGE.get("CN"))); // Assuming "CN" is the key for chip image
        } else {
            chipCountLabel.setText("");
            chipImage.setImage(null);
        }
    }

    /**
     * Gets the ImageView associated with a specific inventory item index.
     *
     * @param index The index of the inventory item (1 to 4).
     * @return The ImageView for the specified inventory item index.
     * @throws IllegalArgumentException If the index is invalid.
     */
    private ImageView getItemImageView(int index) {
        switch (index) {
            case 1:
                return itemImage1;
            case 2:
                return itemImage2;
            case 3:
                return itemImage3;
            case 4:
                return itemImage4;
            default:
                throw new IllegalArgumentException("Invalid item index: " + index);
        }
    }



    /**
     * Handles the key pressed event for player movement.
     *
     * @param event The KeyEvent representing the key press.
     */
    private void handleKeyPressed(KeyEvent event) {
        if (event == null) {
            return;
        }
        KeyCode lastKeyPressed = event.getCode();

        // Update player movement based on the last pressed key
        switch (lastKeyPressed) {
            case W:
                playerCharacter.moveDirection(Actor.Direction.UP, inventory);
                break;
            case S:
                playerCharacter.moveDirection(Actor.Direction.DOWN, inventory);
                break;
            case A:
                playerCharacter.moveDirection(Actor.Direction.LEFT, inventory);
                break;
            case D:
                playerCharacter.moveDirection(Actor.Direction.RIGHT, inventory);
                break;
            // Handle other keys if needed
            default:
                break;
        }
    }

    /**
     * Initializes the player character at the specified coordinates (x, y).
     *
     * @param y The y-coordinate of the player character.
     * @param x The x-coordinate of the player character.
     * @return The initialized player character.
     */
    public Player initialisePlayer(int y, int x) {
        playerCharacter = new Player(x, y);
        playerCharacter.setLayers(levelData.getActors(), levelData.getTiles(), levelData.getItems());
        return playerCharacter;
    }


    public Frog initialiseFrog(int y, int x){
        frog = new Frog(x, y);
        frog.setLayers(levelData.getActors(), levelData.getTiles(), levelData.getItems());
        return frog;
    }
    public PinkBall initalisePinkBall (int y, int x, Actor.Direction direction) {
        pinkBall = new PinkBall(x, y, direction);
        pinkBall.setLayers(levelData.getActors(), levelData.getTiles(), levelData.getItems());
        return pinkBall;
    }
    /**
     * Draws the specified layer data onto the game canvas.
     *
     * @param layerData The 2D array representing the layer to be drawn.
     */

    private void drawLayer(String[][] layerData) {
        for (int i = layerData.length - 1; i >= 0; i--) {
            for (int j = layerData[i].length - 1; j >= 0; j--) { // Fix: Change loop condition
                String key = layerData[i][j];
                if (key.equals("PY")) {
                    initialisePlayer(i, j);
                }
                if (key.equals("FG")) {
                    initialiseFrog(i, j);
                }
                if (key.equals("PL")) {
                    initalisePinkBall(i, j, getDirectionFromKey(key));
                }
                if (key != null) {
                    String imagePath = KEY_TO_IMAGE.get(key);
                    //System.out.println(imagePath);
                    if (imagePath != null) {
                        try {
                            Image image = new Image(imagePath);
                            grid.drawImageOntoSquare(image, j, i);
                        } catch (Exception e) {
                            // Handle the exception (e.g., print the stack trace or log the error)
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * Retrieves the current level data.
     *
     * @return The current level data.
     */
    public Level getLevel() {
        return this.levelData;
    }

    /**
     * Retrieves the player character.
     *
     * @return The player character.
     */
    public Player getPlayer() {
        return this.playerCharacter;
    }

    /**
     * Starts the countdown timer for the level.
     * The timer updates the timer label and stops when the time reaches zero.
     */
    private void startCountdownTimer() {
        countdownTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateTimerLabel();
            if (timeRemaining < 0) {
                countdownTimeLine.stop();
            }
        }));
        countdownTimeLine.setCycleCount(Timeline.INDEFINITE);
        countdownTimeLine.play();
    }

    /**
     * Updates the timer label with the remaining time in the format "MM:SS".
     * Decrements the time remaining each second.
     */
    private void updateTimerLabel() {
        if(!isPaused) {
            int minutes = timeRemaining / 60;
            int seconds = timeRemaining % 60;
            String time = String.format("%02d:%02d", minutes, seconds);
            timerLabel.setText(time);
            this.levelData.setTimeLimit(timeRemaining);
            timeRemaining--;
        }
    }

    /**
     * Checks if the player character is on an ice tile and updates its state accordingly.
     */
    /*
    private void checkPlayerOnIce() {
        int x = playerCharacter.getXPos();
        int y = playerCharacter.getYPos();
        Tile tile = levelData.getTiles().getTile(x, y);

        if (tile instanceof Ice) {
            playerCharacter.onIce();
        }
    }*/
}
