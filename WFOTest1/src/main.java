import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class main {
    public static int sumOfDuplicates(int[] arr) {
        HashSet<Integer> traverseArr = new HashSet<>();
        HashSet<Integer> duplicateArr = new HashSet<>();
        int sum = 0;

        for (int num : arr) {
            if (traverseArr.contains(num) && !duplicateArr.contains(num)) {
                sum += num;
                duplicateArr.add(num);
            } else {
                traverseArr.add(num);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 2, 3, 5, 1, 6, 3, 3, 2, 4};
        // 1, 3, 2
//        int[] numbers = {1, 3, 2, 4, 5};
//        int[] numbers = {3, 3, 3, 4,  4, 5};
//        int[] numbers = {1, 2, 2, 3, 5, 5};
        int result = sumOfDuplicates(numbers);
        System.out.println("Sum of duplicates: " + result);
    }

    @Test
    void tests() {
        assertEquals(6, main.sumOfDuplicates(new int[]{1, 3, 2, 3, 5, 1, 6, 3, 3, 2, 4}));
        assertEquals(5, main.sumOfDuplicates(new int[]{1, 2, 3, 2, 4, 3, 5}));
        assertEquals(0, main.sumOfDuplicates(new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, main.sumOfDuplicates(new int[]{}));
        assertEquals(0, main.sumOfDuplicates(new int[]{1}));
        assertEquals(1, main.sumOfDuplicates(new int[]{1, 1}));
        assertEquals(0, main.sumOfDuplicates(new int[]{0, 0, 0}));
        assertEquals(15, main.sumOfDuplicates(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        assertEquals(55, main.sumOfDuplicates(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        assertEquals(0, main.sumOfDuplicates(new int[]{-1, -2, -3, -1, -2, -3, 0, 1, 2, 3, 1, 2, 3}));
        assertEquals(100000, main.sumOfDuplicates(new int[]{100000, 100000, 1, 2, 3, 4, 5}));
    }
}