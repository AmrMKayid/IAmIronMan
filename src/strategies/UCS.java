package strategies;


import evals.EvalFn;
import evals.UCEvalFn;
import generics.NC;
import generics.Node;
import generics.State;

import java.util.PriorityQueue;

public class UCS extends BaseAgent {
	protected PriorityQueue<Node> queue;
	NC comparator;
	EvalFn evaluator;

	public UCS(State initialState) {
		super(initialState);
	}

	@Override
	public void init() {
		this.evaluator = new UCEvalFn();
		this.comparator = new NC(evaluator);
		queue = new PriorityQueue<>(this.comparator);
	}

	@Override
	public Node sample() {
		Node curr = queue.poll();
		if (curr == null) return null;
		int currCost = comparator.getNodeCost(curr);
		if (memory.containsKey(curr.getState())) {
			int prevCost = memory.get(curr.getState());
			if (currCost >= prevCost) return null;
		}
		memory.put(curr.getState(), currCost);
		return curr;
	}

	@Override
	public void add(Node node) {
		queue.add(node);
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
