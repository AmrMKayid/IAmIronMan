package env.actions;

import env.EndGameState;
import generics.Cell;
import generics.Node;

public class MoveAction extends EndGameAction {

	Cell newValues;

	public MoveAction(String name, Cell values) {
		super(name);
		this.newValues = values;
	}

	@Override
	public Node act(Node node) {
		EndGameState currentState = (EndGameState) node.getState();
		Cell newPosition = new Cell((byte) (currentState.getIronManPosition().row + newValues.row),
			(byte) (currentState.getIronManPosition().col + newValues.col));

		if (!isValidMove(newPosition, currentState))
			return null;

		byte damage = (byte) getDamageFromAdj(newPosition, currentState);
		byte newDamage = (byte) (currentState.getIronManDamage() + damage);
		if (newDamage >= 100)
			return null;

		this.setCost(damage);

		EndGameState nextState = new EndGameState(newPosition,
			newDamage,
			currentState.getInfinityStones(),
			currentState.getWarriorsPositions());

		if (node.getParent() != null
			&& nextState.compareTo(node.getParent().getState()) == 0) {
			return null;
		}
		return new Node(nextState, node, this);
	}
}
