import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Oferta {
	public static class Task {
		public static final String INPUT_FILE = "oferta.in";
		public static final String OUTPUT_FILE = "oferta.out";

		int N;
		int K;
		int[] prices;

		/**
		 * The main method that solves the problem.
		 */
		public void solve() {
			readInput();
			writeOutput(minPrice());
		}

		/**
		 * Reads the input from the file.
		 */
		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				N = sc.nextInt();
				K = sc.nextInt();
				prices = new int[N];
				for (int i = 0; i < N; i++) {
					prices[i] = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Writes the output to the file.
		 *
		 * @param minimumPrice The minimum price to write to the file.
		 */
		private void writeOutput(double minimumPrice) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				pw.printf("%.1f\n", minimumPrice);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Computes the minimum price for the given products.
		 *
		 * @return The minimum price.
		 */
		private double minPrice() {
			double[][] dp = new double[N + 1][K + 1];
			dp[0][0] = 0;
			List<Double> firstK = new ArrayList<>();

			for (int i = 1; i <= N; i++) {
				// Memorize the fist K prices for each series of products
				firstK = computeSmallestPrices(i, dp);

				// Sort the list of prices
				firstK.sort(null);

				// Remove duplicates
				firstK = firstK.stream().distinct().toList();

				// Put the sorted smallest K prices in the dp matrix
				memorizePrices(i, firstK, dp);
			}

			return getKthPrice(firstK, dp);
		}

		/**
		 * Computes the smallest prices for the given series of products.
		 *
		 * @param i The current product.
		 * @param dp The dynamic programming matrix.
		 * @return A list of the smallest prices.
		 */
		private List<Double> computeSmallestPrices(int i, double[][] dp) {
			List<Double> firstK = new ArrayList<>();
			for (int j = 1; j <= K; j++) {
				// Initialize the minimum price with the current product
				dp[i][j] = prices[i - 1];
				if (i >= 2) {
					// Compute the price when we are taking the last product alone
					double takeFst = dp[i][j] + dp[i - 1][j];

					// Compute the price when we are taking the last 2 products together
					double min2 = Math.min(prices[i - 1], prices[i - 2]);
					double takeLast2 = dp[i - 2][j] + prices[i - 1] + prices[i - 2] - min2 / 2;

					// Memorize the prices
					firstK.add(takeFst);
					firstK.add(takeLast2);
					if (i >= 3) {
						// Compute the price when we are taking the last 3 products together
						double min3 = Math.min(min2, prices[i - 3]);
						double takeLast3 =
								dp[i - 3][j] + prices[i - 1] + prices[i - 2] + prices[i - 3] - min3;

						// Memorize the price
						firstK.add(takeLast3);
					}
				}
			}
			return firstK;
		}

		/**
		 * Memorizes the smallest prices in the dynamic programming matrix.
		 *
		 * @param i The current product.
		 * @param firstK The list of the smallest prices.
		 * @param dp The dynamic programming matrix.
		 */
		private void memorizePrices(int i, List<Double> firstK, double[][] dp) {
			if (i >= 2) {
				for (int j = 1; j <= K; j++) {
					if (j > firstK.size()) {
						// If we don't have j prices, we repeat the last one
						dp[i][j] = firstK.get(firstK.size() - 1);
					} else {
						// Otherwise, we memorize the j-th price
						dp[i][j] = firstK.get(j - 1);
					}
				}
			}
		}

		/**
		 * Returns the K-th minimum price for the given products.
		 *
		 * @param firstK The list of the smallest prices.
		 * @param dp The dynamic programming matrix.
		 * @return The K-th minimum price.
		 */
		private double getKthPrice(List<Double> firstK, double[][] dp) {
			// Return the K-th minimum price for the given products
			if (K == 1) {
				// If we need the first price, return the first price
				return dp[N][1];
			} else if (firstK.size() < K) {
				// If we don't have at least K prices, return -1
				return -1;
			} else {
				// Otherwise, return the K-th price
				return dp[N][K];
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
