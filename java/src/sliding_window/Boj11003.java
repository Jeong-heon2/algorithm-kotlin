package sliding_window;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj11003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        Deque<int[]> deq = new ArrayDeque<>();
        for (int i = 0 ; i < N ; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (!deq.isEmpty() && i - deq.peekFirst()[0] >= L) {
                deq.pollFirst();
            }
            while (!deq.isEmpty() && deq.peekLast()[1] >= cur) {
                deq.pollLast();
            }
            deq.offer(new int[] {i, cur});
            assert deq.peekFirst() != null;
            bw.write(deq.peekFirst()[1] + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
