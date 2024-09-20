import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class main {

    public static List<Integer> getDuplicatedNumbers(int[] arr) {
        Map<Integer, Integer> temp = new HashMap<>();
        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            Integer item = arr[i];
            Integer countItem = temp.getOrDefault(arr[i],0);

            temp.put(item, countItem + 1);

            if (temp.get(item) >= 2) {
                result.add(item);
            }
        }
        return result.stream().toList();
    }

    public static int sumOfDuplicates(int[] arr) {
        List<Integer> duplicatedNums = getDuplicatedNumbers(arr);
        return duplicatedNums.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 2, 3, 5, 1, 6, 3, 3, 2, 4};
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