package env.actions;

import env.EndGameState;
import generics.Cell;
import generics.Node;
import generics.enums.Actions;

import java.util.TreeSet;

public class CollectAction extends EndGameAction {

	public CollectAction() {
		super(Actions.COLLECT.toString());
	}

	@Override
	public Node act(Node node) {
		EndGameState currentState = (EndGameState) node.getState();

		if (!currentState.getInfinityStones().contains(currentState.getIronManPosition()))
			return null;

		// Increase Damage by 3 for collecting the stone
		byte damage = (byte) (getDamageFromAdj(currentState.getIronManPosition(), currentState) + 3);
		byte newDamage = (byte) (currentState.getIronManDamage() + damage);
		if (newDamage >= 100)
			return null;

		this.setCost(damage);

		TreeSet<Cell> stonesPositions = (TreeSet<Cell>) currentState.getInfinityStones().clone();
		stonesPositions.remove(currentState.getIronManPosition());

		return new Node(new EndGameState(
			currentState.getIronManPosition(),
			newDamage,
			stonesPositions,
			currentState.getWarriorsPositions()
		), node, this);
	}
}
