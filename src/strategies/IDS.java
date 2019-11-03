package strategies;

import generics.Node;
import generics.State;

public class IDS extends BaseAgent {
	private DFS buffer;

	public IDS(State initialState) {
		super(initialState);
	}

	@Override
	public void init() {
		buffer = new DFS(this.initialState, 0);
	}

	@Override
	public Node sample() {
		return buffer.sample();
	}

	@Override
	public void add(Node node) {
		buffer.add(node);
	}

	@Override
	public boolean isEmpty() {
		boolean isDone = buffer.isEmpty();
		if (isDone) {
			System.out.print("\rCurrent depth " + buffer.limit + " failed " + " === Moving to depth ===> " + (buffer.limit + 1));
			buffer.limit++;
			buffer.reset();
		}
		return false;
	}
}
