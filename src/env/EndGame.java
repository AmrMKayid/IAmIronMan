package env;

import env.actions.CollectAction;
import env.actions.KillAction;
import env.actions.MoveAction;
import env.actions.SnapAction;
import generics.*;
import generics.enums.Actions;
import strategies.BaseAgent;

import java.util.ArrayList;

/**
 * EndGame class is the main environment for our problem
 * and is used to:
 * => get the initial state
 * => get the next state from state space
 * => expanding the search tree and applying the actions to the nodes
 * => implementing the generic search method for all strategies
 * => rendering the environment
 * => Setting the goal test of the problem and testing different states
 * => Printing the solution from the goal node
 * => generating the actions available for the agents to use it to solve the environment
 */
public class EndGame extends BaseEnv {

	private EndGameUniverse universe;

	/**
	 * Constructor for initializing the universe
	 * from the gridString using EndGameUniverse object
	 *
	 * @param gridString the given grid string from the input
	 */
	public EndGame(String gridString) {
		this.universe = EndGameUniverse.getInstance();
		this.universe.init(gridString);
	}

	/**
	 * @return initial state of the endgame problem
	 */
	@Override
	public State reset() {
		return new EndGameState(
			universe.getIronManCell(),
			0,
			universe.getStonesPositions(),
			universe.getWarriorsPositions()
		);
	}

	/**
	 * Applying the action to the current state
	 *
	 * @param state  current state in the environment
	 * @param action the agent's action that will be applied to the state
	 * @return
	 */
	@Override
	public State step(State state, Action action) {
		return action.act(
			new Node(state, null, null)
		).getState();
	}

	/**
	 * Expanding the current node in the search tree
	 *
	 * @param n current node to be expanded
	 * @return ArrayList of expanded nodes
	 */
	@Override
	public ArrayList<Node> expand(Node n) {
		this.generateActions();
//		Collections.shuffle(actions); // Add some randomness !?
		ArrayList<Node> nodes = new ArrayList<>();
		for (Action action : actions) {
			Node newNode = action.act(n);
			if (newNode != null) {
				newNode.setPathCost(getPathCost(newNode, action));
				nodes.add(newNode);
			}
		}
		return nodes;
	}

	/**
	 * Generic Search Algorithm for different types of strategies
	 *
	 * @param baseAgent this represent one of the strategies used in the search method
	 * @param visualize boolean for visualizing the whole solution
	 * @return solution string for the universe
	 */
	public String search(BaseAgent baseAgent, boolean visualize) {
		int totalNodes = 0;
		do {
			Node curr = baseAgent.sample();
			if (curr == null) continue;

			totalNodes++;
			if (goalTest(curr.getState())) {
				return printSolution(curr, visualize, totalNodes);
			}
			ArrayList<Node> nodes = expand(curr);
			baseAgent.add(nodes);
		} while (!baseAgent.isEmpty());

		System.out.println("There is no solution! :'(");
		return null;
	}

	/**
	 * Rendering the initial Universe
	 */
	public void render() {
		System.out.println("#########################################################");
		System.out.println("\t\t\t\t\tEndGame Universe\t\t\t\t\t");
		System.out.println("#########################################################");
		this.universe.renderInitialUniverse();
		System.out.println("#########################################################");
	}

	/**
	 * Testing the state according to the given instruction
	 *
	 * @param state current state
	 * @return boolean indicating whether it is a goal test or not
	 */
	@Override
	public boolean goalTest(State state) {
		/** TODO:
		 * All stones are collected
		 * damage < 100
		 * Thanos is dead from map
		 */
		EndGameState isGoalState = (EndGameState) state;
		return isGoalState.getInfinityStones().isEmpty()
			&& isGoalState.getIronManDamage() < 100
			&& isGoalState.isThanosDead();
	}

	/**
	 * Method for printing the solution
	 *
	 * @param node       the node that contains the solution
	 * @param visualize  boolean for visualization
	 * @param totalNodes total expanded nodes
	 * @return
	 */
	@Override
	public String printSolution(Node node, boolean visualize, int totalNodes) {
		EndGameSolution solution = new EndGameSolution(node, visualize, totalNodes);

		while (node.getParent() != null) {
			solution.addNode(node);
			node = node.getParent();
		}
		solution.addNode(node);
		return solution.getSolution();
	}

	/**
	 * Getting the path cost for each node
	 *
	 * @param node
	 * @param action
	 * @return
	 */
	@Override
	public int getPathCost(Node node, Action action) {
		if (node.getParent() == null) return 0;
		return node.getParent().getPathCost() + action.getCost();
	}

	/**
	 * Method for generating the available actions
	 */
	private void generateActions() {
		this.actions = new ArrayList<>();
		actions.add(new MoveAction(Actions.UP.toString(), new Cell((byte) -1, (byte) 0)));
		actions.add(new MoveAction(Actions.DOWN.toString(), new Cell((byte) 1, (byte) 0)));
		actions.add(new MoveAction(Actions.RIGHT.toString(), new Cell((byte) 0, (byte) 1)));
		actions.add(new MoveAction(Actions.LEFT.toString(), new Cell((byte) 0, (byte) -1)));
		actions.add(new CollectAction());
		actions.add(new SnapAction());
		actions.add(new KillAction());
	}
}
