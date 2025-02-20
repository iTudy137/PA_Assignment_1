Copyright 2024 Dinca Tudor-Cristian Group 322 CA

# Proiectarea Algoritmilor - Homework 1

## Problem 1 - Servere

**Solution:** The solution to this problem involves finding the optimal current that maximizes the minimum computing power of the servers. This is achieved by performing a binary search over the possible current values and computing the power for each server at each step. The optimal current is the one that gives the maximum minimum power.

**Key Methods:**
- `findOptimalValue()`: Performs a binary search over the possible current values to find the optimal current.
- `computePower(double x)`: Computes the power for each server for a given current value by using the formula: power = P[i] - abs(x - C[i]) and returns the minimum power.

**Complexity Analysis:**
- Time complexity: O(n log(m)), where n is the number of servers and m is the range of possible current values.
- Space complexity: O(n) to store the power values for each server.

---

## Problem 2 - Colorare

**Solution:** The code for this problem involves calculating a certain coefficient to multiply by for every set of paintings. There are only two possible patterns: Horizontal and Vertical. Considering this, there are just a few cases to consider.

**Key Methods:**
- `calculatePaintings()`: Calculates the total number of paintings.
- `calculateCollisions(long paintings, char lastType, char currentType)`: Calculates the collisions (where two zones intersect) by checking the last and current pattern and applying the right case described above.
  - `calculateSeriesOfPaintings(long paintings, char T)`: Calculates the paintings in a series of paintings making use of the power function.
- `pow(long base, int exponent)`: Calculates the power.

**Complexity Analysis:**
- Time complexity: O(K + log(exp)), where K is the number of sets and exp comes from the power function.
- Space complexity: O(1) as we only store the result of the calculations.

---

## Problem 3 - Compresie

**Solution:** The solution to this problem involves counting the number of common subsequences between the two sequences. This is achieved by iterating through the arrays and comparing the sums of the subsequences. If the sums are equal, we increment a counter and move to the next elements in both arrays. If the sum of one subsequence is less than the other, we move to the next element in the array with the smaller sum. We continue this process until we have iterated through all elements in both arrays.

**Key Method:**
- `countCommonSubsequences()`: Counts the common subsequences by iterating through the arrays and comparing the sums of the subsequences.

**Complexity Analysis:**
- Time complexity: O(n + m), where n and m are the lengths of the two sequences.
- Space complexity: O(1) as we only store the counter variable.

---

## Problem 4 - Criptat

**Solution:** The solution to this problem involves finding the maximum password length. For this we used a Greedy approach. This is achieved by finding the unique letters in the words, sorting the words by the occurrences of a letter and the length of the word, and calculating the maximum password length for a letter. The maximum password length is the maximum of the password lengths calculated for each letter.

**Key Methods:**
- `findMaxPasswordLength(ArrayList<Character> letters)`: Finds the maximum password length by comparing the password lengths calculated for each letter.
- `countOccurrences(String word, char letter)`: Counts the occurrences of a letter in a word.
- `findUniqueLetters()`: Finds the unique letters in the words.
- `sortByOccurences(char letter)`: Sorts the words by the occurrences of a letter and the length of the word.
- `calculatePasswordLength(char letter)`: Calculates the maximum password length for a letter by adding words in a way that lets the password have a dominant letter.

**Complexity Analysis:**
- Time complexity: O(n * words + n log (n)), where n is the number of words and words is the list of words.
- Space complexity: O(n * words + l) to store the words and the unique letters. (l is the number of unique letters in the words.)

---

## Problem 5 - Oferta

**Solution:** The solution to this problem involves using a dynamic programming approach to find the minimum price for the given products. This is achieved by creating a dp matrix in which we store the smallest K prices for each position. For every new product we calculate and memorize the first minimum unique prices. For this we take into consideration the cases in which we group the last element, last two or last three.

**Key Methods:**
- `minPrice()`: This is the main method that orchestrates the solution. It populates the dp matrix with the minimum prices for the given products by calling the methods described above.
- `computeSmallestPrices(int i, double[][] dp)`: This method calculates the smallest K prices for each product. It considers the cases where we group the last product alone, the last two products together, and the last three products together.  
- `memorizePrices(int i, List<Double> firstK, double[][] dp)`: This method memorizes the smallest K prices in the dp matrix. If we don't have K prices, we repeat the last one. Otherwise, we memorize the j-th price.  
- `getKthPrice(List<Double> firstK, double[][] dp)`: This method returns the K-th minimum price for the given products. If we don't have at least K prices, it returns -1. Otherwise, it returns the K-th price.
  
**Complexity Analysis:**
- Time complexity: O(n * k), where n is the number of products and k is the number of prices to be calculated for each product.
- Space complexity: O(n * k) to store the dp matrix.