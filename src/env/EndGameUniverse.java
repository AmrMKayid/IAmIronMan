package env;

import generics.Cell;
import generics.enums.CellType;

import java.util.TreeSet;

/**
 * Class used for representing the input string as a 2D Map
 * and visualizing the map for animation
 */

public class EndGameUniverse {

	private static EndGameUniverse singleInstance = null;

	public byte rows, cols;
	private Cell ironManCell, thanosCell;
	private CellType[][] grid;
	private TreeSet<Cell> stonesPositions, warriorsPositions;

	private EndGameUniverse() {
	}

	public static EndGameUniverse getInstance() {
		if (singleInstance == null)
			singleInstance = new EndGameUniverse();
		return singleInstance;
	}

	/**
	 * Static method used for rendering the grid
	 *
	 * @param grid grid of cell types
	 */
	static void render(CellType[][] grid) {
		for (CellType[] cells : grid) {
			for (CellType c : cells) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	/**
	 * Initializing the universe and making the graph
	 *
	 * @param gridString
	 */
	void init(String gridString) {
		this.makeGrid(gridString);
	}

	/**
	 * Converting the gridString into Cells in the Universe
	 * and using them to know the positions of ironman, thanos, stones and warriors
	 *
	 * @param gridString Examples: "5,5; 1,2; 3,1; 0,2,1,1,2,1,2,2,4,0,4,1; 0,3,3,0,3,2,3,4,4,3"
	 *                   => gridArray: ['5,5', ' 1,2', ' 3,1', ' 0,2,1,1,2,1,2,2,4,0,4,1', ' 0,3,3,0,3,2,3,4,4,3']
	 */
	private void makeGrid(String gridString) {
		this.stonesPositions = new TreeSet<>();
		this.warriorsPositions = new TreeSet<>();

		String[] gridArray = gridString.split(";");
		Cell mapSize = new Cell(gridArray[0].split(","));
		this.rows = mapSize.getRow();
		this.cols = mapSize.getCol();

		this.grid = new CellType[rows][cols];
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++)
				grid[row][col] = CellType.Free;

		ironManCell = new Cell(gridArray[1].split(","));
		grid[ironManCell.row][ironManCell.col] = CellType.IRONMAN;

		thanosCell = new Cell(gridArray[2].split(","));
		grid[thanosCell.row][thanosCell.col] = CellType.THANOS;

		String[] s = gridArray[3].split(",");
		for (int i = 0; i < s.length - 1; i += 2) {
			byte row = (byte) Integer.parseInt(s[i]);
			byte col = (byte) Integer.parseInt(s[i + 1]);
			grid[row][col] = CellType.INFINITYSTONE;
			stonesPositions.add(new Cell(row, col));
		}

		String[] w = gridArray[4].split(",");
		for (int i = 0; i < w.length - 1; i += 2) {
			byte row = (byte) Integer.parseInt(w[i]);
			byte col = (byte) Integer.parseInt(w[i + 1]);
			grid[row][col] = CellType.WARRIOR;
			warriorsPositions.add(new Cell(row, col));
		}
	}

	/**
	 * Rendering the initial given universe
	 */
	void renderInitialUniverse() {
		render(this.grid);
	}

	TreeSet<Cell> getStonesPositions() {
		return stonesPositions;
	}

	public TreeSet<Cell> getWarriorsPositions() {
		return warriorsPositions;
	}

	/**
	 * Used for checking thanos position for the damage
	 *
	 * @param p
	 * @return
	 */
	public boolean isThanosCell(Cell p) {
		return grid[p.getRow()][p.getCol()] == CellType.THANOS;
	}

	Cell getIronManCell() {
		return ironManCell;
	}

	Cell getThanosCell() {
		return thanosCell;
	}
}
