package strategies;

import generics.Node;
import generics.State;

import java.util.Stack;


public class DFS extends BaseAgent {
	int limit;
	private Stack<Node> buffer;

	public DFS(State initialState) {
		super(initialState);
		limit = -1;
	}

	DFS(State initialState, int limit) {
		super(initialState);
		this.limit = limit;
	}

	@Override
	public void init() {
		buffer = new Stack<>();
	}

	@Override
	public Node sample() {
		if (isEmpty()) {
			return null;
		}
		Node n = buffer.pop();

		if (memory.containsKey(n.getState())
			&& memory.get(n.getState()) <= n.getDepth())
			return null;

		if (limit == -1)
			memory.put(n.getState(), n.getDepth());
		else {
			if (n.getDepth() > this.limit) return null;
			memory.put(n.getState(), 0);
		}

		return n;
	}


	@Override
	public void add(Node node) {
		buffer.push(node);
	}

	@Override
	public boolean isEmpty() {
		return buffer.isEmpty();
	}
}
