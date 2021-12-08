package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Boj1967 {
    static int n;
    static int max;
    static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        max = 0;
        graph = (ArrayList<Edge>[]) new ArrayList[n+1];
        for (int i = 1 ; i <= n ; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0 ; i < n-1 ; i++) {
            String[] tokens = br.readLine().split(" ");
            int parent = Integer.parseInt(tokens[0]);
            int child = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);
            graph[parent].add(new Edge(child, weight));
            graph[child].add(new Edge(parent, weight));
        }
        for (int i = 1; i <= n; i++) {
            dfsRecursion(i, 0, new boolean[n+1]);
        }
        System.out.println(max);
    }

    private static void dfsRecursion(int cur, int cnt, boolean[] visited) {
        visited[cur] = true;
        max = Math.max(max, cnt);
        for (Edge e : graph[cur]) {
            if (!visited[e.end]) {
                dfsRecursion(e.end, cnt + e.w, visited);
            }
        }
    }

    //메모리 초과
    private static void dfsStack(int start) {
        boolean[] visited = new boolean[n+1];
        Stack<int[]> stack = new Stack<>();
        //int[0] = 다음 방문지, int[1]  합산된 weight
        stack.push(new int[]{start, 0});

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            visited[cur[0]] = true;
            max = Math.max(max, cur[1]);
            for (Edge next : graph[cur[0]]) {
                if (!visited[next.end]) {
                    stack.push(new int[]{next.end, cur[1] + next.w});
                }
            }
        }
    }

    static class Edge {
        int end;
        int w;
        public Edge(int end, int w) {
            this.end = end;
            this.w = w;
        }
    }
}
