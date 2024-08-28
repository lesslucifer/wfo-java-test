# Sum of Duplicates

## Problem Description

Write a Java function that calculates the sum of all numbers in an array that have at least one duplicate. In other words, find all the numbers that appear more than once in the array and return their sum.

## Input

- An array of integers `arr` where 1 ≤ arr.length ≤ 10^5
- Each element in the array satisfies -10^9 ≤ arr[i] ≤ 10^9

## Output

- Return an integer representing the sum of all numbers that have at least one duplicate in the array.
- If no numbers have duplicates, return 0.

## Examples

### Example 1:
Input: `[1, 2, 2, 3, 5, 5]`
Output: `7`
Explanation: The numbers with duplicates are 2 and 5. Their sum is 2 + 5 = 7.

### Example 2:
Input: `[1, 2, 3, 4, 5]`
Output: `0`
Explanation: There are no duplicates in the array, so we return 0.

### Example 3:
Input: `[3, 3, 3, 4, 4, 5]`
Output: `7`
Explanation: The numbers with duplicates are 3 and 4. Their sum is 3 + 4 = 7. Note that we only count each number once, even if it appears more than twice.

## Constraints

- You must solve the problem in O(n) time complexity and O(n) space complexity, where n is the length of the input array.
- You may assume that the input array always contains at least one element.

Your solution should be efficient and work correctly for both small and large input arrays.