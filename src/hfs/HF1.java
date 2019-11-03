package hfs;

import env.EndGameState;
import generics.Node;

public class HF1 extends HF {
	@Override
	public int apply(Node node) {
		EndGameState state = (EndGameState) node.getState();
		int remainingStones = state.getInfinityStones().size();
		return remainingStones * 3;
	}
}
