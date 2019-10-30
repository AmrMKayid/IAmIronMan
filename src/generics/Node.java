package generics;

/**
 * Data Structure that keep track
 * of the search tree that is being constructed.
 */

public class Node implements Comparable<Node> {
	private State state;        // The state of the state space that this node corresponds to.
	private Node parent;        // The parent of this node
	private Action action;    // The operator applied to generate this node.
	private int depth;            // The depth of the node in the tree.
	private int pathCost;            // The path cost from the root.

	/**
	 * Constructs a node with the specified state, parent, action
	 *
	 * @param state  the state in the state space to which the node corresponds.
	 * @param parent the node in the search tree that generated the node.
	 * @param action the action that was applied to the parent to generate the node.
	 */
	public Node(State state, Node parent, Action action) {
		this.state = state;
		this.parent = parent;
		this.action = action;

		if (this.parent != null) {
			this.depth = parent.getDepth() + 1;
			this.pathCost = parent.getPathCost() + action.getCost();
		}
	}

	/**
	 * @return the state in the state space to which the node corresponds.
	 */
	public State getState() {
		return state;
	}

	/**
	 * @return the node's parenet node
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @return the action that was applied to the parent to generate the node.
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @return the depth of the node in the search tree
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @return the cost of the path from the initial state to this node
	 */
	public int getPathCost() {
		return pathCost;
	}

	/**
	 * @param pathCost set the path cost for the current node
	 */
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}

	/**
	 * @param o The node to be compared with
	 * @return the comparison between the two nodes
	 */
	@Override
	public int compareTo(Node o) {
		return this.pathCost - o.pathCost;
	}
}
