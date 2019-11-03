import env.EndGame;
import generics.State;
import hfs.HF1;
import hfs.HF2;
import strategies.*;

public class Main {

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		String[] gridStrings = {
			"5,5;1,2;3,1;0,2,1,1,2,1,2,2,4,0,4,1;0,3,3,0,3,2,3,4,4,3",
			"5,5;2,2;4,2;4,0,1,2,3,0,2,1,4,1,2,4;3,2,0,0,3,4,4,3,4,4",
			"6,6;5,3;0,1;4,3,2,1,3,0,1,2,4,5,1,1;1,3,3,3,1,0,1,4,2,2",
			"7,7;5,4;0,1;5,0,5,6,3,1,4,3,1,2,6,3;2,5,2,6,1,0,5,5,6,5",
			"8,8;7,2;2,2;7,6,2,3,3,0,0,1,6,0,5,5;7,3,4,4,1,6,2,4,2,6",
			"9,9;2,5;3,3;6,2,5,1,3,0,2,8,8,3,0,5;5,4,5,5,1,6,6,3,4,8",
			"10,10;5,1;0,4;3,1,6,8,1,2,9,2,1,5,0,8;7,8,7,6,3,3,6,0,3,8",
			"11,11;9,5;7,1;9,0,8,8,9,1,8,4,2,3,9,10;2,0,0,10,6,3,10,6,6,2",
			"12,12;0,6;9,11;8,3,3,0,11,8,7,4,7,7,10,2;2,8,11,2,2,6,4,6,9,8",
			"13,13;4,2;2,4;6,1,1,10,8,4,9,2,2,8,9,4;6,4,3,4,3,11,1,12,1,9",
			"14,14;2,13;12,7;8,6,9,4,7,1,4,4,4,7,2,3;8,13,0,4,0,8,5,7,10,0",
			"15,15;12,13;5,7;7,0,9,14,14,8,5,8,8,9,8,4;6,6,4,3,10,2,7,4,3,11",
		};

		String[] strategies = {
			"BF",
			"DF",
			"ID",
			"UC",
			"GR1",
			"GR2",
			"AS1",
			"AS2",
		};

		for (String strategy : strategies) {
			for (String gridString : gridStrings) {
				String solution = Main.solve(gridString, strategy, false);
				System.out.println(solution);
			}
		}

		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime; // get difference of two nanoTime values
		System.out.println("Execution time in seconds : " + timeElapsed / 1000000000);
	}

	public static String solve(String gridString, String strategy, boolean visualize) {

		EndGame env = new EndGame(gridString);
		State initialState = env.reset();

		if (visualize) {
			System.out.println("\tAvengers EndGame: friday, let's save the universe!");
			env.render();
			System.out.println("Helping Iron Man with Search Algorithm: " + strategy);
		}

		BaseAgent searchStrategy;
		switch (strategy) {
			case "BF":
				searchStrategy = new BFS(initialState);
				break;
			case "DF":
				searchStrategy = new DFS(initialState);
				break;
			case "ID":
				searchStrategy = new IDS(initialState);
				break;
			case "UC":
				searchStrategy = new UCS(initialState);
				break;
			case "GR1":
				searchStrategy = new GR(initialState, new HF1());
				break;
			case "GR2":
				searchStrategy = new GR(initialState, new HF2());
				break;
			case "AS1":
				searchStrategy = new AS(initialState, new HF1());
				break;
			case "AS2":
				searchStrategy = new AS(initialState, new HF2());
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + strategy);
		}

		return env.search(searchStrategy, visualize);
	}
}
