package playground.ki2022;

import java.util.ArrayList;

public class Q4 {
    public static void main(String... args) {
        Solution s=  new Solution();
        s.solution(
                6, new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[]{1, 3}, new int[]{5});
    }
    static class Solution {
        Node[] nodes;
        int[] gates;
        int[] summits;
        int[][] dp;
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            nodes = new Node[n+1];
            this.gates = gates;
            this.summits = summits;
            this.dp = new int[n+1][2];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node();
                dp[i][0] = -1;
            }

            for (int[] path : paths) {
                nodes[path[0]].nexts.add(new int[]{path[1], path[2]});
                nodes[path[1]].nexts.add(new int[]{path[0], path[2]});
            }

            for (int i = 0 ; i < gates.length; i++) {
                nodes[gates[i]].isGate = true;
            }

            for (int i = 0 ; i < summits.length; i++) {
                nodes[summits[i]].isSummit = true;
            }

            int[] answer = {0, Integer.MAX_VALUE};

            for (int start : gates) {
                int[] res = dfs(start, start);
                if (res[0] != 0) {
                    if (res[1] == answer[1]) {
                        if (res[0] < answer[0]) {
                            answer[0] = res[0];
                        }
                    }
                    else if (res[1] < answer[1]) {
                        answer[0] = res[0];
                        answer[1] = res[1];
                    }
                }
            }
            return answer;
        }

        // {산봉우리 번호, intensity}
        private int[] dfs(int start, int cur) {
            if (nodes[cur].isSummit) return new int[]{cur, 0};
            if (nodes[cur].isGate && start != cur) return new int[]{0, 0};

            //산봉우리 만났다면  cur->next 가중치와 리턴받은 intensity 값중 최소값이 리턴해야 할 intensity
            int idx = 0;
            int intensity = Integer.MAX_VALUE;
            for (int[] next: nodes[cur].nexts) {
                int[] res = new int[2];

                if (dp[next[0]][0] == -1) {
                    dp[next[0]][0] = 0;
                    res = dfs(start, next[0]);
                    dp[next[0]][0] = res[0];
                    dp[next[0]][1] = res[1];
                } else {
                    res[0] = dp[next[0]][0];
                    res[1] = dp[next[0]][1];
                }

                if (res[0] != 0) {
                    int w = Math.max(next[1], res[1]);
                    if (intensity >= w) {
                        if (intensity == w) idx = Math.min(res[0], idx);
                        else idx = res[0];
                        intensity = w;
                    }
                }
            }
            dp[cur][0] = idx;
            dp[cur][1] = intensity;
            System.out.println("리턴 " + cur+ "  산봉우리 : " +idx + " inten : " + intensity);
            return new int[]{idx, intensity};
        }

        class Node {
            ArrayList<int[]> nexts = new ArrayList<>();
            boolean isSummit = false;
            boolean isGate = false;
        }
    }
//    static class Solution {
//        Node[] nodes;
//        int[] gates;
//        int[] summits;
//        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
//            nodes = new Node[n+1];
//            this.gates = gates;
//            this.summits = summits;
//            for (int i = 0; i < nodes.length; i++) {
//                nodes[i] = new Node();
//            }
//
//            for (int[] path : paths) {
//                nodes[path[0]].nexts.add(new int[]{path[1], path[2]});
//                nodes[path[1]].nexts.add(new int[]{path[0], path[2]});
//            }
//
//            for (int i = 0 ; i < gates.length; i++) {
//                nodes[gates[i]].isGate = true;
//            }
//
//            for (int i = 0 ; i < summits.length; i++) {
//                nodes[summits[i]].isSummit = true;
//            }
//
//            int[] answer = {0, Integer.MAX_VALUE};
//
//            for (int start : gates) {
//                int[] res = dfs(start, new boolean[n+1]);
//                if (res[0] != 0) {
//                    if (res[1] < answer[1]) {
//                        answer[0] = res[0];
//                        answer[1] = res[1];
//                    }
//                }
//            }
//            return answer;
//        }
//
//        // {산봉우리 번호, intensity}
//        private int[] dfs(int cur, boolean[] visited) {
//            if (nodes[cur].isSummit) return new int[]{cur, 0};
//            if (nodes[cur].isGate) return new int[]{0, 0};
//
//            //산봉우리 만났다면  cur->next 가중치와 리턴받은 intensity 값중 최소값이 리턴해야 할 intensity
//            int idx = 0;
//            int intensity = Integer.MAX_VALUE;
//            for (int[] next: nodes[cur].nexts) {
//                if (!visited[next[0]]) {
//                    visited[next[0]] = true;
//                    int[] res = dfs( next[0], visited);
//                    visited[next[0]] = false;
//
//                    if (res[0] != 0) {
//                        int w = Math.max(next[1], res[1]);
//                        if (intensity > w) {
//                            intensity = w;
//                            idx = res[0];
//                        }
//                    }
//                }
//            }
//            return new int[]{idx, intensity};
//        }
//
//        class Node {
//            ArrayList<int[]> nexts = new ArrayList<>();
//            boolean isSummit = false;
//            boolean isGate = false;
//        }
//    }
}
