package actor;

import item.Item;
import level.Layer;
import tile.GameButton;
import tile.Ice;
import tile.Tile;
import tile.Trap;
/**
 * Represents all actor entities in the game, including their positions using a coordinate pair (x, y).
 * The class provides functionalities for moving actors, checking collisions, and interacting with game elements like traps and buttons.
 *
 * @author Carl Antill, Tomas Williams
 * @version 1.8
 */
public class Actor {

	// X-coordinate of the actor.
	protected int xPos;

	protected boolean onIce = false;

	// Y-coordinate of the actor.

	protected int yPos;

	// Unique identifier for the actor.
	protected String key;

	// Layer containing tile objects in the game.
	protected static Layer tileLayer;
	// Layer containing actor objects in the game.
	protected static Layer actorLayer;
	// Layer containing item objects in the game.
	protected static Layer itemLayer;
	// Array of keys representing tiles that the actor can pass through.
	protected final String[] passableTiles =
			{"Bx", "Tx", "Cx", "IC", "SE", "SW", "NE", "NW", "DI", "EX", "RU", "GU", "YU", "BU", "WT", "PA"};

	// Current direction of the actor.
	protected Direction direction;
	// Direction the actor slides on ice.
	private Direction slideDirection;

	/**
	 * Default constructor for the Actor class.
	 */
	public Actor(){

	}

	/**
	 * Sets the position of the actor.
	 *
	 * @param x The x-coordinate of the actor's position.
	 * @param y The y-coordinate of the actor's position.
	 */
	public void setPos(int x, int y){
		this.xPos = x;
		this.yPos = y;
	}


	public boolean getOnIce() {
		return this.onIce;
	}

	public void setOnIce(boolean state) {
		this.onIce = state;
	}


	/**
	 * Retrieves the x-coordinate of the actor's position.
	 *
	 * @return The x-coordinate of the actor.
	 */
	public int getxPos() {
		return xPos;
	}
	/**
	 * Sets the x-coordinate of the actor's position.
	 *
	 * @param xPos The new x-coordinate for the actor.
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	/**
	 * Retrieves the y-coordinate of the actor's position.
	 *
	 * @return The y-coordinate of the actor.
	 */
	public int getyPos() {
		return yPos;
	}
	/**
	 * Sets the y-coordinate of the actor's position.
	 *
	 * @param yPos The new y-coordinate for the actor.
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	/**
	 * Sets the key identifier for the actor.
	 *
	 * @param key The key to identify the actor.
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * Retrieves the tile layer associated with the actor.
	 *
	 * @return The static layer containing tile objects.
	 */
	public static Layer getTileLayer() {
		return tileLayer;
	}
	/**
	 * Sets the tile layer associated with the actor.
	 *
	 * @param tileLayer The layer containing tile objects.
	 */
	public static void setTileLayer(Layer tileLayer) {
		Actor.tileLayer = tileLayer;
	}
	/**
	 * Retrieves the actor layer.
	 *
	 * @return The static layer containing actor objects.
	 */
	public static Layer getActorLayer() {
		return actorLayer;
	}
	/**
	 * Sets the actor layer.
	 *
	 * @param actorLayer The layer containing actor objects.
	 */
	public static void setActorLayer(Layer actorLayer) {
		Actor.actorLayer = actorLayer;
	}
	/**
	 * Retrieves the item layer associated with the actor.
	 *
	 * @return The static layer containing item objects.
	 */

	public static Layer getItemLayer() {
		return itemLayer;
	}
	/**
	 * Sets the item layer associated with the actor.
	 *
	 * @param itemLayer The layer containing item objects.
	 */
	public static void setItemLayer(Layer itemLayer) {
		Actor.itemLayer = itemLayer;
	}
	/**
	 * Retrieves the array of keys representing tiles that the actor can pass through.
	 *
	 * @return An array of strings representing passable tiles.
	 */
	public String[] getPassableTiles() {
		return passableTiles;
	}
	/**
	 * Retrieves the direction the actor slides on ice.
	 *
	 * @return The direction of sliding.
	 */
	public Direction getSlideDirection() {
		return slideDirection;
	}
	/**
	 * Enum representing possible directions an actor can move in the game.
	 */

	public enum Direction {
		UP,
		DOWN,
		RIGHT,
		LEFT,
		CURRENTPOS;

		/**
		 * Gets the first character of the direction name.
		 * This can be used for short representations of the direction.
		 *
		 * @return The first character of the direction's name.
		 */

		public Direction getRightTurnDirection(Direction currentDirection) {
			switch (currentDirection) {
				case UP:
					return Direction.RIGHT;
				case RIGHT:
					return Direction.DOWN;
				case DOWN:
					return Direction.LEFT;
				case LEFT:
					return Direction.UP;
				default:
					throw new IllegalStateException("Unexpected value: " + currentDirection);
			}
		}
		public Direction getLeftTurnDirection(Direction currentDirection) {
			switch (currentDirection) {
				case UP:
					return Direction.LEFT;
				case RIGHT:
					return Direction.UP;
				case DOWN:
					return Direction.RIGHT;
				case LEFT:
					return Direction.DOWN;
				default:
					throw new IllegalStateException("Unexpected value: " + currentDirection);
			}
		}

		public char getDirection(){
			return name().charAt(0);
		}

		/**
		 * Calculates the horizontal offset when moving in a given direction.
		 * The offset is used to determine the change in the x-coordinate.
		 *
		 * @param direction The direction of movement.
		 * @return The x-offset for the given direction.
		 */
		public int getXOffset(Direction direction) {
			return switch (direction) {
				case LEFT -> -1;  // Move left (decrement x)
				case RIGHT -> 1;  // Move right (increment x)
				default -> 0;     // No change in x for other directions
			};
		}

		/**
		 * Calculates the vertical offset when moving in a given direction.
		 * The offset is used to determine the change in the y-coordinate.
		 *
		 * @param direction The direction of movement.
		 * @return The y-offset for the given direction.
		 */
		public int getYOffset(Direction direction) {
			return switch (direction) {
				case DOWN -> 1;  // Move down (decrement y)
				case UP -> -1;     // Move up (increment y)
				default -> 0;     // No change in y for other directions
			};
		}
	}

	/**
	 * Sets the layer instances for actors, tiles, and items.
	 * This method assigns the respective layers to the static fields of the Actor class.
	 *
	 * @param actors The layer containing actor objects.
	 * @param tiles  The layer containing tile objects.
	 * @param items  The layer containing item objects.
	 */
	public void setLayers(Layer<Actor> actors, Layer<Tile> tiles, Layer<Item> items){
		this.tileLayer = tiles;
		this.actorLayer = actors;
		this.itemLayer = items;
	}
	/**
	 * Get the x coordinate.
	 *
	 * @return The x coordinate.
	 */
	public int getXPos() {
		return this.xPos;
	}

	/**
	 * Get the y coordinate.
	 *
	 * @return the y coordinate.
	 */
	public int getYPos() {
		return this.yPos;
	}

	/**
	 * Get the position of the actor.
	 *
	 * @return the (x, y) coordinate.
	 */
	public int[] getPos() {
		int[] pos = {this.xPos, this.yPos};
		return pos;
	}

	/**
	 * Set the x coordinate.
	 *
	 * @param pos The new x coordinate.
	 */
	public void setXPos(int pos) {
		this.xPos = pos;
	}

	/**
	 * Set the y coordinate.
	 *
	 * @param pos The new y coordinate.
	 */
	public void setYPos(int pos) {
		this.yPos = pos;
	}

	/**
	 * Move the actor in a direction.
	 *
	 * @param direction The direction to move the actor.
	 */
	public void move(Direction direction) {
		int newY = this.yPos;
		int newX = this.xPos;
		switch (direction) {
			case UP:
				newY = this.yPos - 1;
				break;
			case DOWN:
				newY = this.yPos + 1;
				break;
			case RIGHT:
				newX = this.xPos + 1;
				break;
			case LEFT:
				newX = this.xPos - 1;
			default:
				break;
		}
		actorLayer.setActor(newX, newY, this);
		actorLayer.setActor(xPos, yPos, null);
		actorLayer.writeLayer();

		this.yPos = newY;
		this.xPos = newX;
	}


	/**
	 * Determines if the monster can pass through a given tile.
	 * @param tile The tile to check if it passes through.
	 */
	public boolean passTile(Tile tile) {
		boolean passes = false;
		for (String passableTile : this.passableTiles) {
			if (tile.getKey().equals(passableTile)) {
				passes = true;
				break;
			}
		}
		return passes;
	}

	/**
	 * Check the tile in the direction chosen.
	 *
	 * @param direction The direction to check the tile.
	 * @return the tile in the direction chosen.
	 */
	public Tile checkTile(Direction direction) {
		Tile tile = null;
		switch (direction) {
			case UP:
				tile = tileLayer.getTile(this.xPos, this.yPos -1);
				break;
			case DOWN:
				tile = tileLayer.getTile(this.xPos, this.yPos + 1);
				break;
			case RIGHT:
				tile = tileLayer.getTile(this.xPos + 1, this.yPos);
				break;
			case LEFT:
				tile = tileLayer.getTile(this.xPos - 1, this.yPos);
				break;
			case CURRENTPOS:
				tile = tileLayer.getTile(this.xPos, this.yPos);
			default:
				break;
		}

		return tile;
	}

	/**
	 * Detects if another actor is on the same tile as the current actor.
	 *
	 * @param actor The actor to detect collison with.
	 * @return collides If the actor collides.
	 */
	public boolean collidesWith(Actor actor) {
		boolean thisIsMonster = false;
		boolean actorIsMonster = false;
		boolean collides = false;
		
		if (this instanceof Monster) {
			thisIsMonster = true;
		}
		
		if (actor instanceof Monster) {
			actorIsMonster = true;
		}
		
		if (thisIsMonster ^ actorIsMonster) {
			collides = true;
		}
		
		return collides;
	}

	/**
	 * Checks for collision in a specified direction.
	 * Determines if moving in the given direction would result in a collision with another actor.
	 *
	 * @param direction The direction in which to check for a collision.
	 * @return True if a collision would occur, false otherwise.
	 */
	public boolean checkCollision(Direction direction) {
		boolean collides = false;

		// Calculate new positions
		int newX = this.xPos;
		int newY = this.yPos;

		switch (direction) {
			case UP:
				newY = this.yPos - 1;
				break;
			case LEFT:
				newX = this.xPos - 1;
				break;
			case DOWN:
				newY = this.yPos + 1;
				break;
			case RIGHT:
				newX = this.xPos + 1;
				break;
			default:
				break;
		}

		// Check if the new positions are within bounds
		if (newX >= 0 && newX < actorLayer.getWidth() && newY >= 0 && newY < actorLayer.getHeight()) {
			// Check collision with actor at the new position
			collides = this.collidesWith(actorLayer.getActor(newX, newY));
		}

		return collides;
	}

	/**
	 * Handles the actor's interaction when standing on a button.
	 * Typically used to trigger or toggle traps associated with the button.
	 *
	 * @param button The {@code GameButton} the actor interacts with.
	 */
	public void onButton(GameButton button) {
		button.toggleTraps();
	}

	/**
	 * Moves the entity while it is on an ice tile, simulating sliding movement.
	 * The entity continues to slide in the current direction until it encounters a non-passable tile.
	 * If the tile it encounters is also an ice tile, it will change its direction accordingly.
	 *
	 * @param curTile The current ice tile on which the entity is located.
	 */
	public void onIce(Tile curTile) {
		Ice newTile = (Ice) curTile;
		int newX = curTile.getxPos();
		int newY = curTile.getyPos();

		while (newTile instanceof Ice) {
			if (!curTile.isPassable()) {
				if (newX >= 0 && newX < tileLayer.getWidth() && newY >= 0 && newY < tileLayer.getHeight()) {
					if (this.passTile(this.checkTile(direction))) {
						if (newTile instanceof Ice) {
							direction = newTile.slide(this.direction);
							this.move(direction);
						}
					}
				}
			}
		}
	}




	/**
	 * Checks if the actor is currently on an active trap.
	 * This method is typically used to determine if the actor should be affected by a trap.
	 *
	 * @return True if the actor is on an active trap, false otherwise.
	 */

	public boolean checkActiveTrap() {
		Tile cTile = this.checkTile(Direction.CURRENTPOS);
		Trap currentTile = (Trap) cTile;
		return currentTile.isActive();
	}
	/**
	 * Retrieves the unique key of the actor.
	 *
	 * @return The key representing the actor.
	 */
	public String getKey(){
		return this.key;
	}

	/**
	 * Handles the actor's interaction with ice tiles.
	 * Sets the slide direction based on the ice tile's properties.
	 */
	public void onIce() {
		Tile currentTile = checkTile(Direction.CURRENTPOS);
		if (currentTile instanceof Ice) {
			Ice iceTile = (Ice) currentTile;
			Direction slideDirection = iceTile.getSlideDirection(direction);
			System.out.println("On Ice! Slide Direction: " + slideDirection);
			setSlideDirection(slideDirection);
		}
	}

	/**
	 * Sets the direction in which the actor slides on ice.
	 * This method is typically called when the actor interacts with an ice tile.
	 *
	 * @param slideDirection The direction in which the actor should slide on ice.
	 */
	private void setSlideDirection(Direction slideDirection) {
		System.out.println("Setting slide direction: " + slideDirection);
		// Ensure slideDirection is not null when setting it
		if (slideDirection != null) {
			this.slideDirection = slideDirection;
		}
	}



	/**
	 * Retrieves the current movement direction of the actor.
	 *
	 * @return The current direction of the actor.
	 */
	public Direction getDirection() {
		return this.direction;
	}

	/**
	 * Retrieves the current movement direction of the actor.
	 *
	 * @return The current direction of the actor.
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}


}