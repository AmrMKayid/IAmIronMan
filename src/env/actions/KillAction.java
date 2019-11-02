package env.actions;

import env.EndGameState;
import generics.Cell;
import generics.Node;
import generics.enums.Actions;

import java.util.ArrayList;
import java.util.TreeSet;

public class KillAction extends EndGameAction {

	public KillAction() {
		super(Actions.KILL.toString());

	}

	@Override
	public Node act(Node node) {
		int warriorsDamage = 0;
		EndGameState currentState = (EndGameState) node.getState();
		TreeSet<Cell> whiteWalkersPositions = currentState.getWarriorsPositions();
		ArrayList<Cell> adjacentWarriors = getAdjacentCells(currentState.getIronManPosition());

		boolean isWarriorAround = false;
		for (Cell warrior : adjacentWarriors) {
			isWarriorAround = isWarriorAround || whiteWalkersPositions.contains(warrior);
		}
		if (!isWarriorAround) return null;


		TreeSet<Cell> warriorsPositions = (TreeSet<Cell>) currentState.getWarriorsPositions().clone();

		for (Cell adjacentWarrior : adjacentWarriors) {
			if (warriorsPositions.contains(adjacentWarrior)) {
				warriorsPositions.remove(adjacentWarrior);
				warriorsDamage += 2;
			}
		}

		byte damage = (byte) (getDamageFromAdj(currentState.getIronManPosition(), currentState) + warriorsDamage);
		byte newDamage = (byte) (currentState.getIronManDamage() + damage);
		if (newDamage >= 100)
			return null;

		this.setCost(damage);

		return new Node(new EndGameState(
			currentState.getIronManPosition(),
			newDamage,
			currentState.getInfinityStones(),
			warriorsPositions
		), node, this);
	}
}
