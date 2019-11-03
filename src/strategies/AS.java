package strategies;

import evals.ASEvalFn;
import generics.NC;
import generics.State;
import hfs.HF;

import java.util.PriorityQueue;

public class AS extends UCS {

	public AS(State initialState, HF hf) {
		super(initialState);
		this.evaluator = new ASEvalFn(hf);
		this.comparator = new NC(evaluator);
		reset();
	}

	@Override
	public void init() {
		queue = new PriorityQueue<>(this.comparator);
	}
}
