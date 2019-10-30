package generics;

/**
 * Class to represent the actions and their costs
 */
public abstract class Action {

	private String name;
	private int cost;

	public Action(String name, int cost) {
		this.name = name.toLowerCase();
		this.cost = cost;
	}

	public abstract Node apply(Node node);

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return name;
//		return name + "(" + cost + ")";
	}

}
