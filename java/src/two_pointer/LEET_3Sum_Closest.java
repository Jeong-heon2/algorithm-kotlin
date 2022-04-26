package two_pointer;

import java.util.Arrays;

public class LEET_3Sum_Closest {
    // 3Sum 문제 변형
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            int res = 13001;
            Arrays.sort(nums);
            for (int idx1 = 0 ; idx1 + 2 < nums.length; idx1++) {
                int idx2 = idx1 + 1;
                int idx3 = nums.length - 1;
                while (idx2 < idx3) {
                    int sum = nums[idx1] + nums[idx2] + nums[idx3];
                    if (sum == target) {
                        return target;
                    } else if (sum > target) {
                        idx3--;
                    } else {
                        idx2++;
                    }
                    if (Math.abs(target - res) > Math.abs(target - sum)) {
                        res = sum;
                    }
                }
            }
            return res;
        }
    }
}
