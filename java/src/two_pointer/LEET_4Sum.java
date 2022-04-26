package two_pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LEET_4Sum {

    public static void main(String...args) {
        Solution s = new Solution();
        int[] nums = {-3,-1,0,2,4,5};
        s.fourSum(nums, 2);
    }
    static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> list = new ArrayList<>();
            Arrays.sort(nums);
            for (int idx1 = 0 ; idx1 + 3 < nums.length ; idx1++) {
                if (idx1 > 0 && nums[idx1] == nums[idx1 - 1]) continue;
                for (int idx4 = nums.length - 1; idx4 - 2 > idx1; idx4--) {
                    if (idx4 + 1 < nums.length && nums[idx4] == nums[idx4 + 1]) continue;
                    int idx2 = idx1 + 1;
                    int idx3 = idx4 - 1;
                    while (idx2 < idx3) {
                        int sum = nums[idx1] + nums[idx2] + nums[idx3] + nums[idx4];

                        if (sum == target) {
                            list.add(Arrays.asList(nums[idx1], nums[idx2], nums[idx3], nums[idx4]));
                            idx3--;
                            while (idx2 < idx3 && nums[idx3] == nums[idx3 + 1]) idx3--;
                        } else if (sum > target) {
                            idx3--;
                        } else {
                            idx2++;
                        }
                    }
                }
            }
            return list;
        }
    }
}
