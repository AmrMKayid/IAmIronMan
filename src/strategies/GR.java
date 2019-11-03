package strategies;

import evals.GREvalFn;
import generics.NC;
import generics.State;
import hfs.HF;

import java.util.PriorityQueue;

public class GR extends UCS {

	public GR(State initialState, HF hf) {
		super(initialState);
		this.evaluator = new GREvalFn(hf);
		this.comparator = new NC(evaluator);
		reset();
	}

	@Override
	public void init() {
		queue = new PriorityQueue<>(this.comparator);
	}
}
