package actor;

import game.PlayerInventory;

import item.Item;
import javafx.scene.image.Image;
import tile.*;



/**
 * Represents a user-controlled actor known as the player.
 * This class extends the base Actor class and includes additional functionality specific to the player,
 * such as movement, item interaction, and checking game state conditions.
 *
 * @author Carl Antill, Tomas Williams
 * @version 2.4
 */
public class Player extends Actor {

	// Image representing the player in the game.
	private Image playerImage;


	// Flag indicating whether the player is alive.
	private Boolean isAlive = true;
	// Flag indicating whether the player is on an exit tile.
	private Boolean onExit = false;



	/**
	 * Constructs a new Player with the specified initial position.
	 *
	 * @param x The initial x-coordinate.
	 * @param y The initial y-coordinate.
	 */
	public Player(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		this.key = "PY";
	}

	/**
	 * Default constructor for the Player class.
	 * Initializes the player with a default key.
	 */
	public Player() {
		this.key = "PY";
	}


	/**
	 * Checks if the player is alive.
	 *
	 * @return The living state of the player.
	 */
	public Boolean getIsAlive() {
		return this.isAlive;
	}

	/**
	 * Changes the living state of the player.
	 *
	 * @param state The state to set the living state of the player.
	 */
	public void setIsAlive(Boolean state) {
		this.isAlive = state;
	}

	/**
	 * Checks if the player is on an exit tile.
	 *
	 * @return True if the player is currently on an exit tile, false otherwise.
	 */
	public Boolean getOnExit() {
		return this.onExit;
	}

	/**
	 * Sets the player's state to indicate whether they are on an exit tile.
	 *
	 * @param state True if the player is on an exit tile, false otherwise.
	 */
	public void setOnExit(Boolean state) {
		this.onExit = state;
	}


	/**
	 * Handles actions after the player moves in a specified direction.
	 * This includes checking for collisions with blocks or monsters, interactions with items,
	 * and determining if the player has reached an exit tile or encountered a trap.
	 *
	 * @param direction The direction in which to move the player.
	 * @param inventory The inventory of the player, used for item interactions.
	 */
	public void moveDirection(Direction direction, PlayerInventory inventory) {
		boolean passCheck = false;
		boolean blockCheck = false;
		int newX = this.xPos + direction.getXOffset(direction);
		int newY = this.yPos + direction.getYOffset(direction);
		Tile newTile = tileLayer.getTile(newX, newY);

		Actor tempNewActor = actorLayer.getActor(newX, newY);
		Tile curTile = tileLayer.getTile(this.xPos, this.yPos);

		// Check if the move is passable and within bounds
		if (newX >= 0 && newX < tileLayer.getWidth() && newY >= 0 && newY < tileLayer.getHeight()) {
			if (this.passTile(this.checkTile(direction))) {
				passCheck = true;
			}
		}


		//handles being on ice
		/*
		System.out.println(this.getOnIce());
		System.out.println(curTile.getKey());
		if (curTile.getKey().equals("IC")) {
			this.setOnIce(true);
		} else {
			this.setOnIce(false);
		}*/
		/*
		if (this.getOnIce()) {
			this.onIce(curTile);

		}*/

		// Check if the player collided with a block
		if (tempNewActor != null && "BC".equals(tempNewActor.getKey())) {
			if (tempNewActor instanceof Block) {
				((Block) tempNewActor).setNextMove(direction);
			}
		}
		// Check if the player collided with a monster
		if (this.checkCollision(direction)) {
			this.setIsAlive(false);
		}

		if (passCheck && this.getIsAlive() && !this.getOnIce()) {

			this.move(direction);

			// Is on exit
			if (this.checkTile(Direction.CURRENTPOS).getKey().equals("EX")) {
				this.setOnExit(true);
			} else {

				newTile = this.checkTile(Direction.CURRENTPOS);
				if (newTile.getKey().equals("WT")) {
					this.setIsAlive(false);
				}
				if (newTile instanceof Trap) {
					if (((Trap) newTile).isActive()) {
						this.setIsAlive(false);
					}
				}
			}


			// Check if the player is on a button
			if (curTile.getKey().charAt(0) == 'B') {
				this.onButton((GameButton) this.checkTile(Direction.CURRENTPOS));
			}

			String key = curTile.getKey();
			switch (key) {
				case "RL" -> this.useItem("RP", inventory);
				case "BL" -> this.useItem("BP", inventory);
				case "YL" -> this.useItem("YP", inventory);
				case "GL" -> this.useItem("GP", inventory);
				case "CX" -> this.onChipSocket(inventory.getChipCount());
			}
		}
	}

	/**
	 * Finds and uses an item from the player's inventory, removing it from the inventory.
	 *
	 * @param key       The key of the item to use.
	 * @param inventory The player's inventory containing items.
	 * @return The item that was used, or null if the item with the specified key was not found.
	 */
	public Item useItem(String key, PlayerInventory inventory) {
		Item useItem = null;
		for (Item item : inventory.getInventory()) {
			if (item.getKey().equals(key)) {
				useItem = item;
			}
		}

		inventory.getInventory().remove(useItem);
		return useItem;
	}




	/**
	 * Handles interactions when the player walks on a chip socket tile.
	 * Checks if the player has enough chips to open the socket and, if so, opens it.
	 *
	 * @param chipCount The current number of chips the player has.
	 */
	public void onChipSocket(int chipCount) {
		Tile curTile = tileLayer.getTile(this.xPos, this.yPos);
		if (curTile instanceof ChipSocket){
			if (((ChipSocket) curTile).isEnough(chipCount)){
				((ChipSocket) curTile).openSocket();
				this.move(direction);
			}
		}
	}



	/**
	 * Checks if the player is alive.
	 *
	 * @return {@code true} if the player is alive, {@code false} otherwise.
	 */
	public Boolean getAlive() {
		return isAlive;
	}

	/**
	 * Changes the living state of the player.
	 *
	 * @param alive The state to set the living state of the player. Use {@code true} to indicate the player is alive, and {@code false} to indicate otherwise.
	 */
	public void setAlive(Boolean alive) {
		isAlive = alive;
	}
}