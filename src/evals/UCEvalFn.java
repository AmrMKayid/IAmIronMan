package evals;

import generics.Node;

public class UCEvalFn extends EvalFn {
	public UCEvalFn() {
		super(null);
	}

	@Override
	public int eval(Node node) {
		return node.getPathCost();
	}
}
