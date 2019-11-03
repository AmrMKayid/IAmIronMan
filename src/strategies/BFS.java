package strategies;

import generics.Node;
import generics.State;

import java.util.LinkedList;
import java.util.Queue;

public class BFS extends BaseAgent {

	private Queue<Node> buffer;

	public BFS(State initialState) {
		super(initialState);
	}

	@Override
	public void init() {
		buffer = new LinkedList<>();
	}

	@Override
	public Node sample() {
		Node node = buffer.poll();
		assert node != null;
		if (memory.containsKey(node.getState())) return null;
		memory.put(node.getState(), 0);
		return node;
	}

	@Override
	public void add(Node node) {
		buffer.add(node);
	}

	@Override
	public boolean isEmpty() {
		return buffer.isEmpty();
	}
}
