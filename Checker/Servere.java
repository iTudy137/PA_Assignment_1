import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Servere {
	static class Task {
		public static final String INPUT_FILE = "servere.in";
		public static final String OUTPUT_FILE = "servere.out";
		public static final double INF = 1000000000;
		public static final double tolerance = 0.01f;

		int N;
		int[] P;
		int[] C;

		/**
		 * The main method that solves the problem.
		 */
		public void solve() {
			readInput();
			writeOutput(computePower(findOptimalValue()));
		}

		/**
		 * Reads the input from the file.
		 */
		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				N = sc.nextInt();
				P = new int[N];
				for (int i = 0; i < N; i++) {
					P[i] = sc.nextInt();
				}
				C = new int[N];
				for (int i = 0; i < N; i++) {
					C[i] = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Writes the output to the file.
		 * @param minPower The minimum power to write to the file.
		 */
		private void writeOutput(double minPower) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				pw.printf("%.1f\n", minPower);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Finds the optimal value.
		 * @return The optimal value.
		 */
		private double findOptimalValue() {
			double left = 1, right = INF, mid;

			// Perform binary search to find the optimal value
			while (right - left > tolerance) {
				mid = (left + right) / 2;
				if (computePower(mid) < computePower(mid + tolerance)) {
					left = mid;
				} else {
					right = mid;
				}
			}
			return left;
		}

		/**
		 * Computes the power.
		 * @param x The value to compute the power for.
		 * @return The computed power.
		 */
		private double computePower(double x) {
			double minPower = INF;

			// Calculate the power for each server
			for (int i = 0; i < N; i++) {
				// Calculate the power for the current server using the formula
				double power = P[i] - Math.abs(x - C[i]);
				// Update the minimum power
				minPower = Math.min(minPower, power);
			}
			return minPower;
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