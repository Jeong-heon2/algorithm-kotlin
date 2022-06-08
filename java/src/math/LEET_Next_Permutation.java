package math;

import java.util.Arrays;

public class LEET_Next_Permutation {


    class Solution {
        // 숫 자  abcde  가 있을 때
        // 맨오른쪽 의 숫자 두쌍 (d, e) 에서
        //  d < e 이면 두 수자 위치를 바꾸고  d+1 부터 오름차순 정렬 시킨 것이 정답.
        // 아니면 그 다음 쌍 비교 (c, d)
        // c < d 이면  위치를 바꾸고, c+ 1 부터 정렬.
        // (a, b) 까지 비교했는데  a < b 가 아니라면
        // 숫자를 뒤집은게 정답.
        public void nextPermutation(int[] nums) {
            if (nums.length <= 1) return;
            int r = nums.length - 1;
            int l = r-1;
            while(l >= 0) {
                if (nums[l] < nums[r]) {
                    int v = -1;
                    for (int x = r + 1; x < nums.length; x++) {
                        if (nums[l] < nums[x] && nums[x] < nums[r]) {
                            v = x;
                        }
                    }
                    if (v != -1) {
                        swap(nums, l, v);
                        Arrays.sort(nums, l + 1, nums.length);
                    } else {
                        swap(nums, l, r);
                        Arrays.sort(nums, l + 1, nums.length);
                    }
                    return;
                } else {
                    l--;
                    r--;
                }
            }
            reverse(nums);
        }

        private void swap(int[] x, int i, int j) {
            int tmp = x[i];
            x[i] = x[j];
            x[j] = tmp;
        }

        private void reverse(int[] x) {
            int b, c;
            int num = x.length;
            for (int a=0; a < num/2; a++) {
                swap(x, a, num-a-1);
            }
        }
    }
}
