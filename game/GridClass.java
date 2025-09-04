package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import level.Level;

/**
 * The {@code GridClass} manages and renders a grid structure for a game level using JavaFX.
 * It utilizes a {@code GraphicsContext} for drawing, and can render images onto specific grid squares.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.9
 */
public class GridClass {

    // Graphics context for drawing on the canvas.
    private GraphicsContext gc;
    // Width of the grid in number of squares.
    private int width;
    // Height of the grid in number of squares.
    private int height;
    // Size of each square in the grid.
    private int squareSize;
    // The grid's state, represented as a 2D array of integers.
    private int[][] stateGrid;

    /**
     * Constructs a GridClass with a specified graphics context and level.
     * Initializes the grid based on the dimensions of the provided level.
     *
     * @param gc    The GraphicsContext used for drawing the grid.
     * @param level The Level instance to determine grid dimensions.
     */
    public GridClass(GraphicsContext gc, Level level) {
        this.gc = gc;
        this.width = level.getWidth();// Get the width from the Layer instance
        this.height = level.getHeight();
        this.squareSize = 60; // You can set a default square size or get it from the Layer if needed
        initializeGrid();
    }

    /**
     * Initializes the grid with a default or specific state.
     * This method sets up the initial configuration of the grid.
     */
    private void initializeGrid() {
        stateGrid = new int[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                // You can initialize the grid based on your own logic
                stateGrid[row][col] = (row + col) % 2 == 0 ? 0 : 1;
            }
        }
    }


    /**
     * Draws the grid on the canvas.
     * This method iterates through the grid and draws each square.
     */
    public void drawGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double x = col * squareSize;
                double y = row * squareSize;

            }
        }
    }

    /**
     * Draws an image onto a specific square in the grid.
     *
     * @param image The {@code Image} to be drawn.
     * @param col   The column number of the square.
     * @param row   The row number of the square.
     */
    public void drawImageOntoSquare(Image image, int col, int row) {
        double x = col * squareSize;
        double y = row * squareSize;
        gc.drawImage(image, x, y, squareSize, squareSize);
    }

    /**
     * Draws an image across multiple squares in the grid.
     *
     * @param image    The {@code Image} to be drawn.
     * @param startCol The starting column number.
     * @param endCol   The ending column number.
     * @param startRow The starting row number.
     * @param endRow   The ending row number.
     */
    public void drawImageOnMultipleSquares(Image image, int startCol, int endCol, int startRow, int endRow) {
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                drawImageOntoSquare(image, col, row);
            }
        }
    }

    /**
     * Clears the entire grid.
     * This method clears the canvas, effectively resetting the visual representation of the grid.
     */
    public void clearGrid() {
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        // Clear the entire canvas with a transparent color
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
    }

    /**
     * Retrieves the GraphicsContext used for drawing on the canvas.
     *
     * @return The current GraphicsContext instance.
     */
    public GraphicsContext getGc() {
        return gc;
    }

    /**
     * Sets the GraphicsContext to be used for drawing on the canvas.
     *
     * @param gc The GraphicsContext instance to set.
     */
    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * Retrieves the width of the grid in terms of number of squares.
     *
     * @return The width of the grid.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the grid in terms of number of squares.
     *
     * @param width The new width to set for the grid.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Retrieves the height of the grid in terms of number of squares.
     *
     * @return The height of the grid.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the grid in terms of number of squares.
     *
     * @param height The new height to set for the grid.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Retrieves the size of each square in the grid.
     *
     * @return The size of a single square in the grid.
     */
    public int getSquareSize() {
        return squareSize;
    }

    /**
     * Sets the size of each square in the grid.
     *
     * @param squareSize The new size for each square in the grid.
     */
    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    /**
     * Retrieves the state grid, a 2D array representing the state of each square in the grid.
     *
     * @return The current state grid.
     */

    public int[][] getStateGrid() {
        return stateGrid;
    }

    /**
     * Sets the state grid, a 2D array representing the state of each square in the grid.
     *
     * @param stateGrid The new state grid to set.
     */
    public void setStateGrid(int[][] stateGrid) {
        this.stateGrid = stateGrid;
    }
}