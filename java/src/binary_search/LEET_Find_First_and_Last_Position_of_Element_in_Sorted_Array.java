package binary_search;

public class LEET_Find_First_and_Last_Position_of_Element_in_Sorted_Array {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;
            int mid = 0;
            while (l <= r) {
                mid = (l+r)/2;
                if (nums[mid] == target) {
                    l = mid;
                    r = mid;
                    while(l >= 0 && nums[l] == target) {
                        l--;
                    }
                    while(r < nums.length && nums[r] == target) {
                        r++;
                    }
                    return new int[]{l+1, r-1};
                } else if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return new int[]{-1, -1};
        }
    }
}
