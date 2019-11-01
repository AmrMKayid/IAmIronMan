package generics;

/**
 * Cell that represent the location in the universe
 */
public class Cell implements Comparable<Cell> {
	public byte row, col;

	public Cell(byte row, byte col) {
		this.row = row;
		this.col = col;
	}

	public Cell(String[] pos) {
		this.row = (byte) Integer.parseInt(pos[0]);
		this.col = (byte) Integer.parseInt(pos[1]);
	}

	public byte getRow() {
		return row;
	}

	public byte getCol() {
		return col;
	}

	/**
	 * checking the cell does not exceed the borders
	 *
	 * @param rows max rows in the universe
	 * @param cols max columns in the universe
	 * @return
	 */
	public boolean isValid(byte rows, byte cols) {
		return row >= 0 && row < rows && col >= 0 && col < cols;
	}

	@Override
	public int compareTo(Cell o) {
		return (row != o.row) ? row - o.row : col - o.col;
	}
}
