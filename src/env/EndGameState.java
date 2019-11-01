package env;

import generics.Cell;
import generics.State;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * State Class used for Environment Representation
 * after each time step
 */

public class EndGameState extends State {

	private Cell ironManPosition;
	private int ironManDamage;
	private boolean isThanosDead = false;
	private TreeSet<Cell> infinityStones;
	private TreeSet<Cell> warriorsPositions;

	/**
	 * Normal EndGameState
	 *
	 * @param ironManPosition   the position of iron man in the universe
	 * @param ironManDamage     the current damage of iron man
	 * @param infinityStones    The set of remaining infinity stones in the universe
	 * @param warriorsPositions The set of remaining warriors in the universe
	 */
	public EndGameState(Cell ironManPosition, int ironManDamage,
						TreeSet<Cell> infinityStones,
						TreeSet<Cell> warriorsPositions) {

		this.ironManPosition = ironManPosition;
		this.ironManDamage = ironManDamage;
		this.infinityStones = (TreeSet<Cell>) infinityStones.clone();
		this.warriorsPositions = (TreeSet<Cell>) warriorsPositions.clone();
	}

	/**
	 * Used as representation for Goal State
	 *
	 * @param ironManPosition       the position of iron man in the universe
	 * @param ironManDamage         the current damage of iron man
	 * @param isThanosDead          The end of Thanos after the snap!
	 * @param infinityStones         The set of remaining infinity stones in the universe
	 * @param warriorsPositions The set of remaining warriors in the universe
	 */
	public EndGameState(Cell ironManPosition, int ironManDamage,
						boolean isThanosDead,
						TreeSet<Cell> infinityStones,
						TreeSet<Cell> warriorsPositions) {

		this.ironManPosition = ironManPosition;
		this.ironManDamage = ironManDamage;
		this.isThanosDead = isThanosDead;
		this.infinityStones = (TreeSet<Cell>) infinityStones.clone();
		this.warriorsPositions = (TreeSet<Cell>) warriorsPositions.clone();
	}

	/**
	 * @return true if thanos is dead after the snap
	 */
	boolean isThanosDead() {
		return isThanosDead;
	}

	/**
	 * @return iron man position
	 */
	public Cell getIronManPosition() {
		return ironManPosition;
	}

	/**
	 * @return iron man damage
	 */
	public int getIronManDamage() {
		return ironManDamage;
	}

	/**
	 * @return set of infinity stones
	 */
	public TreeSet<Cell> getInfinityStones() {
		return infinityStones;
	}

	/**
	 * @return set of warriors
	 */
	public TreeSet<Cell> getWarriorsPositions() {
		return warriorsPositions;
	}

	/**
	 * Comparing the state with another state
	 * used for repeated states and informed search
	 *
	 * @param o The state to be compared with
	 * @return the comparison between the two states
	 */
	@Override
	public int compareTo(State o) {
		EndGameState s = ((EndGameState) o);
		if (this.ironManPosition.compareTo(s.ironManPosition) == 0) {
			if (this.ironManDamage == s.ironManDamage) {
				Iterator<Cell> i = this.infinityStones.iterator();
				Iterator<Cell> j = s.infinityStones.iterator();
				while (i.hasNext() && j.hasNext()) {
					Cell a = i.next();
					Cell b = j.next();
					if (a.compareTo(b) != 0)
						return a.compareTo(b);
				}
				if (i.hasNext())
					return 1;

				if (j.hasNext())
					return -1;

				return 0;
			}
			return this.ironManDamage - s.ironManDamage;
		}
		return this.ironManPosition.compareTo(s.ironManPosition);
	}
}
