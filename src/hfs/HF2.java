package hfs;

import env.EndGameState;
import env.actions.CollectAction;
import env.actions.EndGameAction;
import generics.Cell;
import generics.Node;

import java.util.TreeSet;

public class HF2 extends HF {
	@Override
	public int apply(Node node) {
		EndGameState state = (EndGameState) node.getState();
		TreeSet<Cell> remainingStones = state.getInfinityStones();
		EndGameAction collect = new CollectAction();

		int totalDamage = 0;
		for (Cell stone :
			remainingStones) {
			int damage = (collect.getDamageFromAdj(stone, state) + 3);
			totalDamage += damage;
		}

		return totalDamage;
	}
}
