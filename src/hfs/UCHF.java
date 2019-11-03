package hfs;

import generics.Node;

public class UCHF extends HF {
	@Override
	public int apply(Node node) {
		return node.getPathCost();
	}
}
