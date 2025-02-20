import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Criptat {
	static class Task {
		public static final String INPUT_FILE = "criptat.in";
		public static final String OUTPUT_FILE = "criptat.out";

		int N;
		ArrayList<String> words;

		/**
		 * The main method that solves the problem.
		 */
		public void solve() {
			readInput();
			writeOutput(findMaxPasswordLength(findUniqueLetters()));
		}

		/**
		 * Reads the input from the file.
		 */
		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				N = sc.nextInt();
				words = new ArrayList<>();
				for (int i = 0; i < N; i++) {
					words.add(sc.next());
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Writes the output to the file.
		 * @param maxPasswordLength The maximum password length to write to the file.
		 */
		private void writeOutput(int maxPasswordLength) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				pw.println(maxPasswordLength);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Finds the maximum password length.
		 * @param letters The list of unique letters.
		 * @return The maximum password length.
		 */
		private int findMaxPasswordLength(ArrayList<Character> letters) {
			int maxPasswordLength = 0;
			for (char letter : letters) {
				sortByOccurences(letter);
				int passwordLength = calculatePasswordLength(letter);
				maxPasswordLength = Math.max(maxPasswordLength, passwordLength);
			}
			return maxPasswordLength;
		}

		/**
		 * Counts the occurrences of a letter in a word.
		 * @param word The word to search in.
		 * @param letter The letter to count.
		 * @return The number of occurrences of the letter in the word.
		 */
		private int countOccurrences(String word, char letter) {
			int count = 0;
			for (char c : word.toCharArray()) {
				if (c == letter) {
					count++;
				}
			}
			return count;
		}

		/**
		 * Finds the unique letters in the words.
		 * @return A list of unique letters.
		 */
		private ArrayList<Character> findUniqueLetters() {
			Set<Character> lettersSet = new HashSet<>();
			// Add every unique encounter of a letter to the set
			for (String word : words) {
				for (char letter : word.toCharArray()) {
					lettersSet.add(letter);
				}
			}
			return new ArrayList<>(lettersSet);
		}

		/**
		 * Sorts the words by the occurrences of a letter and the length of the word.
		 * @param letter The letter to sort by.
		 */
		private void sortByOccurences(char letter) {
			words.sort((o1, o2) -> {
				// Calculate the dominance of the letter in the word as occurrences / length
				double dominance1 = (double) countOccurrences(o1, letter) / o1.length();
				double dominance2 = (double) countOccurrences(o2, letter) / o2.length();
				if (dominance1 != dominance2) {
					// Sort by the dominance of the letter in the word
					return Double.compare(dominance2, dominance1);
				} else {
					// Sort by the length of the word if the words have the same dominance
					return o2.length() - o1.length();
				}
			});
		}

		/**
		 * Calculates the maximum password length for a letter.
		 * @param letter The letter to calculate the maximum password length for.
		 * @return The maximum password length.
		 */
		private int calculatePasswordLength(char letter) {
			int count = 0;
			int passwordLength = 0;

			for (String word : words) {
				// Add words to the password to maximize the password length
				count += countOccurrences(word, letter);
				passwordLength += word.length();

				// Don't consider the word if it makes the password not have a dominant letter
				if (count <= passwordLength / 2) {
					passwordLength -= word.length();
					count -= countOccurrences(word, letter);
				}
			}
			return passwordLength;
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