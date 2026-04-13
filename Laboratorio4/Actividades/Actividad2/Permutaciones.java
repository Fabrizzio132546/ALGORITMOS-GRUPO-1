package Actividad2;

import java.util.List;
import java.util.ArrayList;

public class Permutaciones {
    public static void permutar(int[] nums, boolean[] usado, List<Integer> actual) {
        if (actual.size() == nums.length) {
            System.out.println(actual);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!usado[i]) {
                usado[i] = true;
                actual.add(nums[i]);

                permutar(nums, usado, actual);
                // backtracking
                actual.remove(actual.size() - 1);
                usado[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        boolean[] usado = new boolean[nums.length];
        permutar(nums, usado, new ArrayList<>());
    }
}

