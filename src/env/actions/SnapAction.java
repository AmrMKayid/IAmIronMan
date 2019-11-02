package env.actions;

import env.EndGameState;
import generics.Node;
import generics.enums.Actions;

public class SnapAction extends EndGameAction {

	public SnapAction() {
		super(Actions.SNAP.toString());
	}

	@Override
	public Node act(Node node) {
		EndGameState currentState = (EndGameState) node.getState();

		if (!this.universe.isThanosCell(currentState.getIronManPosition()))
			return null;

		if (!currentState.getInfinityStones().isEmpty())
			return null;

		int damage = getDamageFromAdj(currentState.getIronManPosition(), currentState);
		int newDamage = currentState.getIronManDamage() + damage;
		if (newDamage >= 100)
			return null;


		return new Node(new EndGameState(
			currentState.getIronManPosition(),
			newDamage,
			true,
			currentState.getInfinityStones(),
			currentState.getWarriorsPositions()
		), node, this);
	}
}
