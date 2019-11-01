package generics;

/**
 * Class to represent the actions and their costs
 */
public abstract class Action {

	private String name;
	private byte cost;

	public Action(String name, byte cost) {
		this.name = name;
		this.cost = cost;
	}

	public abstract Node act(Node node);

	public byte getCost() {
		return cost;
	}

	public void setCost(byte cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return name.toLowerCase();
//		return name + "(" + cost + ")";
	}

}
