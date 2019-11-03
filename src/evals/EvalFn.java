package evals;

import generics.Node;
import hfs.HF;

public abstract class EvalFn {
	HF hf;

	EvalFn(HF hf) {
		this.hf = hf;
	}

	public abstract int eval(Node node);
}
