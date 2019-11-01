package generics;

import strategies.BaseAgent;

import java.util.ArrayList;

public abstract class BaseEnv {

	public ArrayList<Action> actions;

	/**
	 * This method is used to reset the environment
	 * and get the initial state of the environment
	 *
	 * @return environment's initial state
	 */
	public abstract State reset();

	/**
	 * Transition function which is used to get the next state
	 * after applying the given action on the current state of the environment
	 *
	 * @param state  current state in the environment
	 * @param action the agent's action that will be applied to the state
	 * @return new next state in the environment
	 */
	public abstract State step(State state, Action action);

	/**
	 * Testing whether the given state in the goal test set of the environment
	 *
	 * @param state current state
	 * @return boolean indicates whether or not the state is in the goal test set
	 */
	public abstract boolean goalTest(State state);

	/**
	 * Expanding the current node by applying the list of actions in the environment
	 *
	 * @param n current node to be expanded
	 * @return a list of new nodes
	 */
	public abstract ArrayList<Node> expand(Node n);

	/**
	 * Generic search method used for searching with different strategies
	 *
	 * @param baseAgent this represent one of the strategies used in the search method
	 * @param visualize  boolean for visualizing the whole solution
	 * @return sequence of actions that leads to a solution
	 */
	public abstract String search(BaseAgent baseAgent, boolean visualize);

	/**
	 * Used for printing the solution from the given node
	 *
	 * @param node       the node that contains the solution
	 * @param visualize  boolean for visualization
	 * @param totalNodes total expanded nodes
	 * @return sequence of actions that leads to a solution
	 */
	public abstract String printSolution(Node node, boolean visualize, int totalNodes);

	public ArrayList<Action> getActions() {
		return actions;
	}

	public abstract int getPathCost(Node node, Action action);
}
