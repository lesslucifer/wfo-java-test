import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Main {
    public static int sumOfDuplicates(int[] arr) {
            Map<Integer,Integer> result = new HashMap<>();
            for (int num : arr) {
                result.put(num,result.getOrDefault(num,0) + 1);
            }

            int sum=0;
            for(Map.Entry<Integer,Integer> entry : result.entrySet()) {
                if(entry.getValue()>1) {
                    sum+=entry.getKey();
                }
            }

        return sum;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 2, 3, 5, 1, 6, 3, 3, 2, 4};
        int result = sumOfDuplicates(numbers);
        System.out.println("Sum of duplicates: " + result);
    }

    @Test
    void tests() {
        assertEquals(6, Main.sumOfDuplicates(new int[]{1, 3, 2, 3, 5, 1, 6, 3, 3, 2, 4}));
        assertEquals(5, Main.sumOfDuplicates(new int[]{1, 2, 3, 2, 4, 3, 5}));
        assertEquals(0, Main.sumOfDuplicates(new int[]{1, 2, 3, 4, 5}));
        assertEquals(0, Main.sumOfDuplicates(new int[]{}));
        assertEquals(0, Main.sumOfDuplicates(new int[]{1}));
        assertEquals(1, Main.sumOfDuplicates(new int[]{1, 1}));
        assertEquals(0, Main.sumOfDuplicates(new int[]{0, 0, 0}));
        assertEquals(15, Main.sumOfDuplicates(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        assertEquals(55, Main.sumOfDuplicates(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        assertEquals(0, Main.sumOfDuplicates(new int[]{-1, -2, -3, -1, -2, -3, 0, 1, 2, 3, 1, 2, 3}));
        assertEquals(100000, Main.sumOfDuplicates(new int[]{100000, 100000, 1, 2, 3, 4, 5}));
    }
}