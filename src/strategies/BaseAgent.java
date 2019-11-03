package strategies;

import generics.Node;
import generics.State;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public abstract class BaseAgent {

	State initialState;
	Map<State, Integer> memory;

	BaseAgent(State initialState) {
		this.initialState = initialState;
		reset();
	}


	void reset() {
		this.memory = new TreeMap<>();
		init();
		add(new Node(this.initialState, null, null));
	}

	public abstract void init();

	public abstract Node sample();

	public abstract void add(Node node);

	public void add(ArrayList<Node> nodes) {
		for (Node node : nodes) {
			add(node);
		}
	}

	public abstract boolean isEmpty();

}
