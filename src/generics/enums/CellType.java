package generics.enums;

public enum CellType {
	IRONMAN,
	THANOS,
	WARRIOR,
	INFINITYSTONE,
	Free;

	@Override
	public String toString() {
		switch (this) {
			case IRONMAN:
				return "\033[31m" + "I\t" + "\033[0m";
			case THANOS:
				return "\033[35m" + "T\t" + "\033[0m";
			case WARRIOR:
				return "\033[34m" + "W\t" + "\033[0m";
			case INFINITYSTONE:
				return "\033[33m" + "S\t" + "\033[0m";
			case Free:
				return "\033[37m" + "#\t" + "\033[0m";
		}
		return null;
	}
}
