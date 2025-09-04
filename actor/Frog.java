package actor;

import tile.Tile;
import java.util.*;

/**
 * The `Frog` class represents a frog character in the game.
 * It has the ability to move towards the player's position.
 *
 * @author Carl Antill, Tomas Williams
 * @version 1.6
 */
public class Frog extends Monster {

	protected final String[] passableTiles = {"PA", "Bx"};

	/**
	 * Creates a frog object at the set given positions
	 * @param xPos The x position.
	 * @param yPos The y position.
	 */
	public Frog (int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.key = "FG";
	}

	/**
	 * Constructs a new `Frog` object with the default key "FG".
	 */
	public Frog(){
		this.key = "FG";
	}

	/**
	 * Moves the frog character towards the player's position.
	 *
	 * @param playerX The X-coordinate of the player's position.
	 * @param playerY The Y-coordinate of the player's position.
	 */
	public void moveTowardsPlayer(int playerX, int playerY) {
		List<Direction> path = findPath(playerX, playerY);
		if (!path.isEmpty()) {
			Direction nextMove = path.get(0);
			move(nextMove);
		}
	}

	/**
	 * Finds the path from the frog's current position to the player using the A* algorithm.
	 *
	 * @param playerX         The x-coordinate of the player.
	 * @param playerY         The y-coordinate of the player.
	 * @return The list of directions representing the path.
	 */
	private List<Direction> findPath(int playerX, int playerY) {
		// A* algorithm implementation
		PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getTotalCost));
		Map<Tile, Node> nodeMap = new HashMap<>();

		Tile startTile = tileLayer.getTile(this.xPos, this.yPos);
		Tile destinationTile = tileLayer.getTile(playerX, playerY);

		Node startNode = new Node(startTile, null, 0, calculateHeuristic(startTile, destinationTile), null);
		openSet.add(startNode);
		nodeMap.put(startTile, startNode);

		while (!openSet.isEmpty()) {
			Node current = openSet.poll();

			if (current.getTile().equals(destinationTile)) {
				// Reconstruct the path
				List<Direction> path = new ArrayList<>();
				Node node = current;
				while (node.getParent() != null) {
					path.add(node.getDirection());
					node = node.getParent();
				}
				Collections.reverse(path);
				return path;
			}

			for (Direction direction : Direction.values()) {
				int newX = current.getTile().getxPos() + direction.getXOffset(direction);
				int newY = current.getTile().getyPos() + direction.getYOffset(direction);

				if (newX >= 0 && newX < tileLayer.getWidth() && newY >= 0 && newY < tileLayer.getHeight()) {
					Tile neighbor = tileLayer.getTile(newX, newY);
					if (passTile(neighbor) && !nodeMap.containsKey(neighbor)) {
						int newCost = current.getCost() + 1;
						int heuristic = calculateHeuristic(neighbor, destinationTile);
						Node neighborNode = new Node(neighbor, current, newCost, heuristic, direction);
						openSet.add(neighborNode);
						nodeMap.put(neighbor, neighborNode);
					}
				}
			}
		}

		// No path found
		return new ArrayList<>();
	}

	/**
	 * Calculates the heuristic (estimated cost) between two tiles.
	 *
	 * @param from The starting tile.
	 * @param to   The destination tile.
	 * @return The heuristic value.
	 */
	private int calculateHeuristic(Tile from, Tile to) {
		return Math.abs(from.getxPos() - to.getxPos()) + Math.abs(from.getyPos() - to.getyPos());
	}

	/**
	 * The `Node` class represents a node used in the A* algorithm for pathfinding.
	 * Each node contains information about a tile, its parent node, cost, heuristic,
	 * and the direction from the parent node.
	 */
	private static class Node {
		private final Tile tile;
		private final Node parent;
		private final int cost;
		private final int heuristic;
		private final Direction direction;

		/**
		 * Constructs a new `Node` object.
		 *
		 * @param tile The tile associated with this node.
		 * @param parent The parent node of this node.
		 * @param cost The cost to reach this node.
		 * @param heuristic The heuristic value of this node.
		 * @param direction The direction from the parent node to this node.
		 */
		public Node(Tile tile, Node parent, int cost, int heuristic, Direction direction) {
			this.tile = tile;
			this.parent = parent;
			this.cost = cost;
			this.heuristic = heuristic;
			this.direction = direction;
		}

		/**
		 * Gets the tile associated with this node.
		 *
		 * @return The tile.
		 */
		public Tile getTile() {
			return tile;
		}

		/**
		 * Gets the parent node of this node.
		 *
		 * @return The parent node.
		 */
		public Node getParent() {
			return parent;
		}

		/**
		 * Gets the cost to reach this node.
		 *
		 * @return The cost.
		 */
		public int getCost() {
			return cost;
		}

		/**
		 * Gets the heuristic value of this node.
		 *
		 * @return The heuristic value.
		 */
		public int getHeuristic() {
			return heuristic;
		}

		/**
		 * Calculates the total cost of this node (cost + heuristic).
		 *
		 * @return The total cost.
		 */
		public int getTotalCost() {
			return cost + heuristic;
		}

		/**
		 * Gets the direction from the parent node to this node.
		 *
		 * @return The direction.
		 */
		public Direction getDirection() {
			return direction;
		}
	}
}
