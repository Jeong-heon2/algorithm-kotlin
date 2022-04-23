package sort;

import java.util.*;

public class LEET_TWO_SUM {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {2, 7, 11, 15};
        int[] res = s.twoSum(nums, 9);
        System.out.println(res[0] + " " + res[1]);
    }

    // O(N)
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            // memo : current 의 target에 대한 보수를 메모  < 보수, 인덱스 >
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int i = 0 ; i < nums.length ; i++) {
                int current = nums[i];
                if (map.containsKey(current)) {
                    return new int[] {map.get(current), i};
                } else {
                    map.put(target - current, i);
                }
            }
            return new int[] {0,0};
        }
    }
}
