package actor;

import tile.GameButton;
import tile.Tile;

/**
 * Represents the Pink Ball monster in the game.
 * The Pink Ball moves in a specific direction until it hits an impassable tile, at which point it flips direction.
 *
 * @author Carl Antill, Tomas Williams
 * @version 1.5
 */
public class PinkBall extends Monster {

	/**
	 * Constructs a Pink Ball at a given position, facing a set direction.
	 *
	 * @param x         The x-coordinate position of the Pink Ball.
	 * @param y         The y-coordinate position of the Pink Ball.
	 * @param direction The initial direction the Pink Ball is facing.
	 */
	private final String[] passableTiles =
			{"Bx", "Tx", "PA"};
	public PinkBall (int x, int y, Actor.Direction direction) {
		this.direction = direction;
		this.xPos = x;
		this.yPos = y;
		this.key = String.format("P%s",direction.getDirection());
	}

	/**
	 * Constructs a Pink Ball facing a set direction.
	 *
	 * @param direction The initial direction the Pink Ball is facing.
	 */
	public PinkBall (Actor.Direction direction) {
		this.direction = direction;
		this.key = String.format("P%s",direction.getDirection());
	}

	/**
	 * Sets the next move for the Pink Ball.
	 * The Pink Ball continues to move in its current direction and flips direction if it encounters an impassable tile.
	 */
	public void setNextMove() {
		int newX = this.xPos + direction.getXOffset(direction);
		int newY = this.yPos + direction.getYOffset(direction);
		Tile nextTile = tileLayer.getTile(newX, newY);
		if (this.checkTile(this.direction) == null || !isTilePassable(nextTile)) {
			flipDirection(this.direction);
		}

		this.move(this.direction);

		if (this.checkTile(Direction.CURRENTPOS).getKey().charAt(0) == 'B') {
			this.onButton(((GameButton) this.checkTile(Direction.CURRENTPOS)));
		}
	}

		private boolean isTilePassable (Tile tile){
			String tileKey = tile.getKey();
			for (String passableTile : passableTiles) {
				if (tileKey.equals(passableTile)) {
					return true;
				}
			}
			return false;
		}
	}
