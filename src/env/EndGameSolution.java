package env;

import generics.Action;
import generics.Cell;
import generics.Node;
import generics.enums.CellType;

import java.util.ArrayList;
import java.util.Collections;

class EndGameSolution {

	private EndGameUniverse map;

	private String solutionString;
	private ArrayList<Node> solutionNodes;
	private ArrayList<Action> solutionActions;
	private ArrayList<CellType[][]> solutionGrids;

	private int depth;
	private int totalNodes;
	private boolean visualize;

	EndGameSolution(boolean visualize, int totalNodes) {
		this.totalNodes = totalNodes;
		this.visualize = visualize;
		solutionString = "";
		solutionNodes = new ArrayList<>();
		solutionActions = new ArrayList<>();
		solutionGrids = new ArrayList<>();
		map = EndGameUniverse.getInstance();
	}

	void addNode(Node node) {
		solutionNodes.add(node);
	}

	String printSolution() {

		for (Node node : solutionNodes) {
			depth = Math.max(depth, node.getDepth());
			solutionActions.add(node.getAction());
			solutionGrids.add(gridFromNode(node));
		}

		Collections.reverse(solutionActions);
		for (Action action :
			solutionActions) {
			solutionString += (action != null) ? action + "," : "";
			System.out.print(solutionString);
		}
		solutionString += ";" + depth + ";" + totalNodes;

		if (visualize) {
			System.out.println("\n\n\n############################################");
			System.out.println("Total number of expanded nodes: " + totalNodes);
			System.out.println("Depth of the solution: " + depth);
			System.out.println("############################################");
			System.out.println();

			for (int i = solutionGrids.size() - 1; i >= 0; i--) {
				System.out.println(solutionActions.get(i) != null ? solutionActions.get(i)
					+ ", Iron Man Damage: " + ((EndGameState) solutionNodes.get(i).getState()).getIronManDamage() : "Initial State");
				System.out.println("############################################");
				EndGameUniverse.render(solutionGrids.get(i));
				System.out.println("############################################\n");
			}
		}

		return solutionString;
	}

	private CellType[][] gridFromNode(Node node) {

		EndGameState state = (EndGameState) node.getState();

		CellType[][] grid = new CellType[this.map.rows][this.map.cols];
		for (int row = 0; row < this.map.rows; row++)
			for (int col = 0; col < this.map.cols; col++)
				grid[row][col] = CellType.Free;

		for (Cell stone : state.getInfinityStones()) {
			grid[stone.row][stone.col] = CellType.INFINITYSTONE;
		}

		for (Cell warrior : state.getWarriorsPositions()) {
			grid[warrior.row][warrior.col] = CellType.WARRIOR;
		}


		Cell thanosCell = this.map.getThanosCell();
		grid[thanosCell.row][thanosCell.col] = CellType.THANOS;

		Cell ironManCell = state.getIronManPosition();
		grid[ironManCell.row][ironManCell.col] = CellType.IRONMAN;

		return grid;
	}
}
