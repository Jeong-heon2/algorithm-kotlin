package two_pointer;

public class LEET_Trapping_Rain_Water {

    // time : O(n) . 실질적으로 O(2N)
    // space : O(n)
    class Solution {
        public int trap(int[] height) {
            int n = height.length;
            int[] left = new int[n];

            int max = 0;
            for (int i = 0 ; i < n; i++) {
                max = Math.max(max, height[i]);
                left[i] = max;
            }

            max = 0;
            int sum = 0;
            for (int i = n-1; i >= 0; i--) {
                max = Math.max(max, height[i]);
                int min = Math.min(left[i], max);
                if (min > height[i]) {
                    sum += min - height[i];
                }
            }

            return sum;
        }
    }

    // 더 효율적인 방법 ,  two pointer 솔루션
    // time : O(n)
    // space : O(1)
    class Solution2 {
        public int trap(int[] height) {
            int left = 0, right = height.length - 1;
            int ans = 0;
            int left_max = 0, right_max = 0;
            while (left < right) {
                if (height[left] < height[right]) {
                    if (height[left] >= left_max){
                        left_max = height[left];
                    } else {
                        ans += (left_max - height[left]);
                    }
                    ++left;
                }
                else {
                    if (height[right] >= right_max) {
                        right_max = height[right];
                    } else {
                        ans += (right_max - height[right]);
                    }
                    --right;
                }
            }
            return ans;
        }
    }
}
