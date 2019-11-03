package evals;

import generics.Node;
import hfs.HF;

public class GREvalFn extends EvalFn {
	public GREvalFn(HF hf) {
		super(hf);
	}

	@Override
	public int eval(Node node) {
		return hf.apply(node);
	}
}
