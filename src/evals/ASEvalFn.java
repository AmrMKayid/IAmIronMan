package evals;

import generics.Node;
import hfs.HF;

public class ASEvalFn extends EvalFn {
	public ASEvalFn(HF hf) {
		super(hf);
	}

	@Override
	public int eval(Node node) {
		return this.hf.apply(node) + node.getPathCost();
	}
}
