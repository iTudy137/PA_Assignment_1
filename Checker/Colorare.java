import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Colorare {
	static class Task {
		public static final String INPUT_FILE = "colorare.in";
		public static final String OUTPUT_FILE = "colorare.out";
		private static final long MOD = 1000000007;

		int K;
		int X;
		char T;
		Scanner sc;

		/**
		 * The main method that solves the problem.
		 */
		public void solve() {
			readInput();
			writeOutput(calculatePaintings());
		}

		/**
		 * Reads the input from the file.
		 */
		private void readInput() {
			try {
				sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				K = sc.nextInt();
				sc.nextLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Writes the output to the file.
		 * @param paintings The total number of paintings to write to the file.
		 */
		private void writeOutput(long paintings) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				pw.println(paintings);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Calculates the total number of paintings.
		 * @return The total number of paintings.
		 */
		private long calculatePaintings() {
			long paintings = 1;
			char lastType = ' ';

			// Calculate the number of paintings for each pair of
			// (X - the number of zones, T - the type)
			for (int i = 0; i < K; i++) {
				X = sc.nextInt();
				T = sc.next().charAt(0);

				// Calculate the number of paintings at the intersection with another zone
				// based on the last type of painting and the current type of painting
				paintings = calculateCollisions(paintings, lastType, T);

				// Calculate the number of paintings in a series of paintings
				paintings = calculateSeriesOfPaintings(paintings, T);
				lastType = T;
			}
			sc.close();
			return paintings;
		}

		/**
		 * Calculates the collisions (where two zones intersect).
		 * @param paintings The current number of paintings.
		 * @param lastType The last type of painting.
		 * @param currentType The current type of painting.
		 * @return The updated number of paintings.
		 */
		private long calculateCollisions(long paintings, char lastType, char currentType) {
			if (currentType == 'H') {
				if (lastType == 'H') {
					paintings = (paintings * 3) % MOD;
				} else if (lastType == 'V') {
					paintings = (paintings * 2) % MOD;
				} else {
					paintings = (paintings * 6) % MOD;
				}
			} else {
				if (lastType == 'H') {
					paintings = (paintings) % MOD;
				} else if (lastType == 'V') {
					paintings = (paintings * 2) % MOD;
				} else {
					paintings = (paintings * 3) % MOD;
				}
			}
			return paintings;
		}

		/**
		 * Calculates the paintings in a series of paintings.
		 * @param paintings The current number of paintings.
		 * @param T The type of painting.
		 * @return The updated number of paintings.
		 */
		private long calculateSeriesOfPaintings(long paintings, char T) {
			if (T == 'H') {
				paintings = (paintings * pow(3, X - 1)) % MOD;
			} else {
				paintings = (paintings * pow(2, X - 1)) % MOD;
			}
			return paintings;
		}
		
		/**
		 * Calculates the power.
		 * @param base The base of the power.
		 * @param exponent The exponent of the power.
		 * @return The result of the power calculation.
		 */
		private long pow(long base, int exponent) {
			long result = 1;
			while (exponent > 0) {
				if (exponent % 2 == 1) {
					result = (result * base) % MOD;
				}
				base = (base * base) % MOD;
				exponent /= 2;
			}
			return result;
		}
	}

	/**
	 * The main method that creates a new Task object and solves the problem.
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		new Task().solve();
	}
}
