package greedy;

import java.util.LinkedList;
import java.util.Queue;

public class LEET_JUMP_GAME2 {

    public static void main(String... args) {
        Solution s = new Solution();
        s.jump(new int[] {2,3,1,1,4});
    }

    static class Solution {
        public int jump(int[] nums) {
            int jumps = 0;
            int curr = 0;
            int prev = 0;
            for(int i = 0 ; i < nums.length ; i++)
            {
                if(i > prev)
                {
                    jumps++;
                    prev = curr;
                }
                curr = Math.max(nums[i]+i, curr);
            }
            return jumps;
        }
    }

    class Solution2 {
        public int jump(int[] nums) {
            return bfs(nums);
        }

        private int bfs(int[] nums) {
            boolean[] visited = new boolean[nums.length];
            Queue<Integer> q = new LinkedList<>();
            visited[0] = true;
            q.offer(0);
            int cnt = 0;
            while(q.size() > 0) {
                int qSize = q.size();
                while(qSize-- > 0) {
                    int cur = q.poll();
                    if (cur == nums.length - 1) return cnt;

                    for (int n = nums[cur]; n > 0 ; n--) {
                        int next = cur + n;
                        if (next < nums.length && !visited[next]) {
                            visited[next] = true;
                            q.offer(next);
                        }
                    }
                }
                cnt++;
            }
            return -1;
        }
    }
}
