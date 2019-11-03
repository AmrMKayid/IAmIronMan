package generics;

import evals.EvalFn;

import java.util.Comparator;

public class NC implements Comparator<Node> {
	private EvalFn evaluator;

	public NC(EvalFn evaluator) {
		this.evaluator = evaluator;
	}

	public int getNodeCost(Node node) {
		return evaluator.eval(node);
	}


	@Override
	public int compare(Node o1, Node o2) {
		return evaluator.eval(o1) - evaluator.eval(o2);
	}
}
