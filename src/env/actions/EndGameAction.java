package env.actions;

import env.EndGameState;
import env.EndGameUniverse;
import generics.Action;
import generics.Cell;

import java.util.ArrayList;

public abstract class EndGameAction extends Action {

	EndGameUniverse universe;

	EndGameAction(String name) {
		super(name, (byte) 0);
		this.universe = EndGameUniverse.getInstance();
	}

	boolean isValidMove(Cell position, EndGameState state) {
		boolean validPosition = position.isValid(universe.rows, universe.cols);
		if (!validPosition) return false;

		boolean isThanosCell = this.universe.isThanosCell(position);
		boolean isWarriorCell = hasWarrior(position, state);

		// isThanosCell > True || isEmpty > True
		return !isWarriorCell && (!isThanosCell || state.getInfinityStones().isEmpty());
	}

	private boolean hasWarrior(Cell position, EndGameState state) {
		return state.getWarriorsPositions().contains(position);
	}

	public int getDamageFromAdj(Cell cell, EndGameState state) {
		int damage = 0;
		ArrayList<Cell> adjacentCells = getAdjacentCells(cell);
		for (Cell adjCell : adjacentCells) {
			if (hasWarrior(adjCell, state)) {
				damage += 1;
			}
			if (this.universe.isThanosCell(adjCell)) {
				damage += 5;
			}
		}

		// If IronMan in the same cell of Thanos
		if (this.universe.isThanosCell(cell)) {
			damage += 5;
		}

		return damage;
	}


	ArrayList<Cell> getAdjacentCells(Cell cell) {
		byte[] rows = {1, -1, 0, 0};
		byte[] cols = {0, 0, 1, -1};
		byte row = cell.getRow(), col = cell.getCol();
		ArrayList<Cell> adjCells = new ArrayList<>();
		for (byte dir = 0; dir < 4; dir++) {
			byte newRow = (byte) (row + rows[dir]), newCol = (byte) (col + cols[dir]);
			Cell adjCell = new Cell(newRow, newCol);
			if (adjCell.isValid(universe.rows, universe.cols)) {
				adjCells.add(adjCell);
			}
		}
		return adjCells;
	}
}
