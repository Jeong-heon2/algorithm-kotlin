package greedy;

public class LEET_Container_With_Most_Water {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.maxArea(new int[] {1,8,6,2,5,4,8,3,7});
    }

    static class Solution {
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int max = 0;
            while (left < right) {
                int h = 0;
                if (height[left] < height[right]) {
                    h = height[left];
                    left++;
                } else {
                    h = height[right];
                    right--;
                }
                max = Math.max(max, h * (Math.abs(left - right) + 1));
            }
            return max;
        }
    }
}
