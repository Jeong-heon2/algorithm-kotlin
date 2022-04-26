package two_pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LEET_3SUM {
    public static void main(String[] args) {

    }

    class Solution {
        // brute force -> log(n^3) : 조합 알고리즘 3쌍 뽑아서  합이 0인지
        // 0 이 되려면 :  -, +, +  조합 또는  +, -, -  조합 이어야 함 (0도 + 라 치면)
        // 1. -, - 쌍을 모두 구한다  (log(n^2))
        // 2. -, - 쌍 에 대한 0의 보수가 존재하는지 확인 (hashmap) (log(1))
        // 근데 이건 space 를 너무 많이 잡아먹는다,..
        public List<List<Integer>> threeSum(int[] nums) {
            // -10 -4 -1 -1 -1 0 1 2 5 9
            //(nlogn)
            Arrays.sort(nums);
            //find last  minus idx
            //(logn)
            int idx = -1;
            for (int i = 0 ; i < nums.length; i++) {
                if (nums[i] >= 0) {
                    idx = i;
                    break;
                }
            }
            // - , - 쌍을 구하는데 중복을 제거해야함... 중복 제거를 위한 hsahmap을 선언?.. 공간낭비가 아닐까
            // 다른방법을 찾아보자
            return Collections.emptyList();
        }
    }

    class Solution2 {
        // -1, -1, 0, 1, 2

        // 정렬후, 포인터 3개를 이용해서 해결해보자
        // 맨 왼쪽 가리키는 포인터,  맨 오른쪽 가리키는 포인터.  그리고 가운데 포인터

        // 1. 맨 왼쪽 포인터 + 가운데 포인터 + 맨 오른쪽 포인터  = 0 이면   맨오른쪽을 왼쪽으로 옮기며 다른 조합을 찾아보자 (이때 중복 있으므로 제거해야함)
        // 2. > 0 이면   맨 오른쪽을 왼쪽으로 옮겨야한다 ( 숫자를 줄여야하니까 )
        // 3. < 0 이면  가운데 포인터를 오른쪽으로 옮겨야한다 ( 숫자를 키워야하니까 )
        // 4. 1,2,3 을 가운데 포인터 < 맨 오른쪽 포인터 일동안 반복하자
        // 1~4 를  맨 왼쪽 포인터가 최대한 오른쪽으로 갈때까지 반복하자

        // 시간 복잡도 : log(n^2)
        // 공간 : log(1)
        public List<List<Integer>> threeSum(int[] nums) {
            ArrayList<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            for (int idx1 = 0 ; idx1 + 2 < nums.length ; idx1++) {
                // 얘내가 같으면 중복이므로 넘어간다
                if (idx1 > 0 && nums[idx1] == nums[idx1 -1]) continue;
                int idx2 = idx1 + 1;
                int idx3 = nums.length - 1;
                while (idx2 < idx3) {
                    int sum = nums[idx1] + nums[idx2] + nums[idx3];
                    if (sum == 0) {

                        res.add(Arrays.asList(nums[idx1], nums[idx2], nums[idx3]));

                        idx3--;
                        // Skip all duplicates from right
                        while (idx2 < idx3 && nums[idx3] == nums[idx3 + 1]) idx3--;
                    } else if (sum > 0) {
                        idx3--;
                    } else {
                        idx2++;
                    }
                }
            }
            return res;
        }
    }
}
