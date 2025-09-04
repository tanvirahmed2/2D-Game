package actor;

/**
 * Represents the generic attributes and behaviors of monsters in the game.
 * This abstract class extends the {@code Actor} class, providing common functionalities for all monster types.
 *
 * @author Carl Antill, Tomas Williams
 * @version 1.5
 */
abstract public class Monster extends Actor {

	// Array of keys representing tiles that the Monster can pass through.
	protected final String[] passableTiles = {"PA", "Bx", "Tx"};
	// Current direction of the Monster
	protected Direction direction;
	// Reference to the player, used to check interactions between the Monster and the player.
	protected Player player;

	/**
	 * Checks if the Monster collides with the player when moving in a given direction.
	 * If a collision is detected, the player's status is updated to not alive.
	 *
	 * @param direction The direction in which to check for collision with the player.
	 */
	public void checkCollidesWithPlayer(Direction direction) {
		if (this.checkCollision(direction)) {
			this.player.setIsAlive(false);
		}
	}

	/**
	 * Flips the Monster's direction to the opposite direction.
	 * For example, if the current direction is UP, it will be changed to DOWN.
	 */
	public void flipDirection(Direction direction) {
		switch(this.direction) {
			case UP:
				this.direction = Direction.DOWN;
				break;
			case DOWN:
				this.direction = Direction.UP;
				break;
			case RIGHT:
				this.direction = Direction.LEFT;
				break;
			case LEFT:
				this.direction = Direction.RIGHT;
				break;
		}
	}

	/**
	 * Turns the Monster's direction to the left by 90 degrees.
	 * This method adjusts the direction based on a left turn.
	 */
	public void turnLeft() {
		switch(this.direction) {
			case UP:
				this.direction = Direction.LEFT;
				break;
			case DOWN:
				this.direction = Direction.RIGHT;
				break;
			case RIGHT:
				this.direction = Direction.UP;
				break;
			case LEFT:
				this.direction = Direction.DOWN;
				break;
		}
	}
	/**
	 * Turns the Monster's direction to the right by 90 degrees.
	 * This method uses a combination of a left turn and flip to achieve a right turn.
	 */
	public void turnRight() {
		this.turnLeft();
		this.flipDirection(this.direction);
	}

	/**
	 * Retrieves an array of tile keys that the monster can pass through.
	 * This method overrides the {@code getPassableTiles} method from the {@code Actor} class.
	 *
	 * @return An array of strings representing the keys of passable tiles.
	 */
	@Override
	public String[] getPassableTiles() {
		return passableTiles;
	}

	/**
	 * Retrieves the current movement direction of the monster.
	 * This method overrides the {@code getDirection} method from the {@code Actor} class.
	 *
	 * @return The current direction of the monster.
	 */
	@Override
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the movement direction of the monster.
	 * This method overrides the {@code setDirection} method from the {@code Actor} class.
	 *
	 * @param direction The new direction for the monster to move in.
	 */
	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Retrieves the player associated with the monster.
	 * This method is used to access the player object, typically for interactions or targeting.
	 *
	 * @return The {@code Player} object associated with the monster.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player associated with the monster.
	 * This method allows the monster to interact with or target the specified player.
	 *
	 * @param player The {@code Player} object to associate with the monster.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
