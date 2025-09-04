package actor;

import tile.Tile;

/**
 * The `Bug` class represents a type of monster in the game. Bugs can move and follow a maze-solving algorithm.
 * They have specific passable tiles they can traverse and change direction based on the right-hand rule.
 * Bugs can be oriented to move either left or right.
 *
 * @author Carl Antill, Tomas Williams
 * @version 1.8
 */
public class Bug extends Monster {
	private final String[] passableTileBug =
			{"Bx", "Tx", "PA"};

	/**
	 * Constructs a `Bug` object with the specified initial direction, position, and key.
	 *
	 * @param xPos The x-coordinate of the initial position.
	 * @param yPos The y-coordinate of the initial position.
	 * @param direction The initial direction of the bug.
	 */
	public Bug(int xPos, int yPos, Direction direction) {
		this.direction = direction;
		this.xPos = xPos;
		this.yPos = yPos;
		this.key = setKey();
	}

	/**
	 * Constructs a `Bug` object with the specified initial direction and sets its key.
	 *
	 * @param direction The initial direction of the bug.
	 */
	public Bug(Direction direction) {
		this.direction = direction;
		this.key = setKey();
	}

	/**
	 * Sets the key based on the bug's current direction.
	 *
	 * @return The key associated with the bug's direction.
	 */
	private String setKey() {
		char directionChar = direction.getDirection();
		return switch (directionChar) {
			case 'L' -> "BE";
			case 'R' -> "BW";
			default -> null;
		};
	}

	/**
	 * Checks if the bug can pass through the given tile based on its passable tiles list.
	 *
	 * @param tile The tile to check for passability.
	 * @return `true` if the bug can pass through the tile, `false` otherwise.
	 */
	public boolean bugPassable(Tile tile) {
		if (tile == null) {
			// Handle the case where tile is null (you may throw an exception or return false)

			throw new IllegalArgumentException("Tile cannot be null");
			// Check if the tile represents a wall
		}
		String tileKey = tile.getKey();
		for (String passableTile : passableTileBug) {
			try {
				if (tileKey.equals(passableTile)) {
					// The tile is passable
					return true;
				}
			} catch (IndexOutOfBoundsException e) {
				// Handle the IndexOutOfBoundsException (you may throw an exception or log an error)
				throw new IndexOutOfBoundsException("Index out of bounds while accessing tile key");
			}
		}
		// The tile is not in the list of passable tiles, so it's not passable
		return false;
	}

	/**
	 * Implements a maze-solving algorithm for the bug to follow.
	 * The bug checks adjacent tiles to determine its movement.
	 */
	public void mazeSolvingAlgorithm() {
		int newX = this.xPos + this.direction.getXOffset(this.direction);
		int newY = this.yPos + this.direction.getYOffset(this.direction);
		Tile rightTile = tileLayer.getTile(newX, newY);

		Direction currentDirection = this.direction.getRightTurnDirection(direction);
		Direction superCurrentDirection = this.direction.getRightTurnDirection(currentDirection);
		System.out.println(currentDirection);
		int altX = this.xPos + this.direction.getXOffset(currentDirection);
		int altY = this.yPos + this.direction.getYOffset(currentDirection);
		Tile altTile = tileLayer.getTile(altX, altY);
		System.out.println("rightTile passable: " + bugPassable(rightTile));
		System.out.println("altTile passable: " + bugPassable(altTile));


		if (bugPassable(altTile)) {
			this.move(currentDirection);
		} else if (bugPassable(altTile) && bugPassable(rightTile)) {
			// Turn right
			this.direction = direction.getRightTurnDirection(direction);
		} else {
			// Turn left (following the right-hand rule)
			this.direction = direction.getLeftTurnDirection(direction);
		}
	}
}