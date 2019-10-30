package generics;

/**
 * Cell that represent the location in the universe
 */
public class Cell implements Comparable<Cell> {
	public int row, col;

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Cell(String[] pos) {
		this.row = Integer.parseInt(pos[0]);
		this.col = Integer.parseInt(pos[1]);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	/**
	 * checking the cell does not exceed the borders
	 *
	 * @param rows max rows in the universe
	 * @param cols max columns in the universe
	 * @return
	 */
	public boolean isValid(int rows, int cols) {
		return row >= 0 && row < rows && col >= 0 && col < cols;
	}

	@Override
	public int compareTo(Cell o) {
		return (row != o.row) ? row - o.row : col - o.col;
	}
}
