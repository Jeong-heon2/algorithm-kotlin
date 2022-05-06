package brute_force;

import java.util.ArrayList;

public class PG_양과늑대 {

    public static void main(String... args) {
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        //[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]
        int[][] edge = {
                {0, 1},
                {1, 2},
                {1, 4},
                {0, 8},
                {8, 7},
                {9, 10},
                {9, 11},
                {4, 3},
                {6, 5},
                {4, 6},
                {8, 9},
        };
        Solution s = new Solution();
        int a = s.solution(info, edge);
        System.out.println(a);
    }
    static class Solution {
        int[] info;
        ArrayList<Integer>[] arr;
        int max;
        public int solution(int[] info, int[][] edges) {
            this.info = info;
            this.arr = (ArrayList<Integer>[]) new ArrayList[17];
            max = 0;
            for (int i = 0 ; i < 17 ; i++) {
                arr[i] = new ArrayList<>();
            }
            for (int[] edge: edges) {
                arr[edge[0]].add(edge[1]);
            }
            dfs(0, 0, 0, new ArrayList<>());
            return max;
        }

        private void dfs(int yang, int wolf, int n, ArrayList<Integer> nexts) {
            // 늑대 or 양? cnt 체크 , max 갱신
            if (info[n] == 1) wolf++;
            else yang++;
            if (yang - wolf <= 0) return;
            max = Math.max(max, yang);
            // 다음 으로 갈 수 있는 곳 방문
            ArrayList<Integer> curNext = new ArrayList<>(nexts);
            curNext.addAll(arr[n]);
            int size = curNext.size();
            for (int i = size - 1; i >= 0 ;i--) {
                int next = curNext.get(i);
                curNext.remove(i);
                dfs(yang, wolf, next, curNext);
                curNext.add(next);
            }
        }
    }
}
