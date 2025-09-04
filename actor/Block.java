package actor;

import tile.Path;
import tile.Tile;

/**
 * Represents a movable block object in the game.
 * This class extends the {@code Actor} class, using boundaries and coordinates to represent possible moves.
 * Blocks can interact with various elements in the game, such as water and ice tiles.
 *
 * @author Carl Antill, Tomas Williams
 * @version 1.6
 */
public class Block extends Actor {


	protected final String[] passableTiles = {"PA", "Bx", "Tx", "IC", "SE", "SW", "NE", "NW", "WT"};

	/**
	 * Constructs a new Block instance with a default key.
	 */
	public Block(){
		this.key = "BC";
	}

	/**
	 * Executes a tick for the block, handling movement and interactions with game elements.
	 *
	 * @param direction The direction in which to attempt to move the block.
	 * @return True if the block successfully moved or interacted, false otherwise.
	 */
	public boolean tick (Direction direction) {
		boolean check = false;
		if (this.checkTile(Direction.CURRENTPOS).equals("IC")) {
			//this.onIce();
		} else {
			check = this.setNextMove(direction);
		}
		return check;
	}

	/**
	 * Sets the next move for the block based on the direction and tile interactions.
	 *
	 * @param direction The direction in which to move the block.
	 * @return True if the move is successful, false otherwise.
	 */
	public Boolean setNextMove(Direction direction) {
		boolean check = false;
		if (this.passTile(this.checkTile(direction))) {
			this.move(direction);
			Tile curTile = tileLayer.getTile(this.xPos, this.yPos);
			if (curTile.getKey().equals("WT")) {
				onWater(curTile);
			}
			check = true;
		}
		return check;
	}

	/**
	 * Handles the interaction of the block with water tiles.
	 * Converts water tiles to path tiles and removes the block.
	 *
	 * @param tile The water tile the block interacts with.
	 */
	public void onWater(Tile tile) {
		tileLayer.setTile(tile.getxPos(), tile.getyPos(), new Path());
		tileLayer.writeLayer();
		actorLayer.setActor(tile.getxPos(), tile.getyPos(), null);
		actorLayer.writeLayer();

		//remove item
	}
}