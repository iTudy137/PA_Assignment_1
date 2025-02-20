import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Compresie {
	static class Task {
		public static final String INPUT_FILE = "compresie.in";
		public static final String OUTPUT_FILE = "compresie.out";

		int n, m;
		int[] A;
		int[] B;

		/**
		 * The main method that solves the problem.
		 */
		public void solve() {
			readInput();
			writeOutput(countCommonSubsequences());
		}

		/**
		 * Reads the input from the file.
		 */
		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				A = new int[n];
				for (int i = 0; i < n; i++) {
					A[i] = sc.nextInt();
				}
				m = sc.nextInt();
				B = new int[m];
				for (int i = 0; i < m; i++) {
					B[i] = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Writes the output to the file.
		 * @param totalCommonSubsequences The total number of common
		 *     subsequences to write to the file.
		 */
		private void writeOutput(int totalCommonSubsequences) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				pw.println(totalCommonSubsequences);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Counts the common subsequences.
		 * @return The total number of common subsequences.
		 */
		private int countCommonSubsequences() {
			int count = 0, i = 0, j = 0;
			int sumA = A[i];
			int sumB = B[j];

			// Count the number of common subsequences by iterating through the arrays
			// and comparing the sums of the subsequences
			while (i < n && j < m) {
				if (sumA == sumB) {
					count++;
					i++;
					j++;
					if (i < n && j < m) {
						sumA += A[i];
						sumB += B[j];
					}
				} else if (sumA < sumB) {
					i++;
					if (i < n) {
						sumA += A[i];
					}
				} else {
					j++;
					if (j < m) {
						sumB += B[j];
					}
				}
			}

			// Return the total number of common subsequences
			if (count < 2) {
				// If there are less than 2 common subsequences we return -1
				return -1;
			} else {
				return count;
			}
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