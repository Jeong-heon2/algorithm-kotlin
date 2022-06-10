package binary_search;

/*
2분 탐색 변형
 */
public class LEET_Search_in_Rotated_Sorted_Array {
    class Solution {
        public int search(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;
            while(l <= r) {
                int mid = (l+r)/2;
                if (nums[mid] == target) return mid;
                if (nums[l] > nums[mid]) {
                    if (nums[mid] < target && target < nums[l]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                } else if (nums[mid] > nums[r]) {
                    if (nums[r] < target && target < nums[mid]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (nums[mid] > target) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
            }
            return -1;
        }
    }
}
