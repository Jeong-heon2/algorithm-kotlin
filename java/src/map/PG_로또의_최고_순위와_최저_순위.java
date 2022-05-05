package map;

public class PG_로또의_최고_순위와_최저_순위 {
    class Solution {
        public int[] solution(int[] lottos, int[] win_nums) {
            int[] answer = new int[2];
            boolean[] arr = new boolean[46];
            for (int i : win_nums) arr[i] = true;
            int cnt = 0;
            int zero = 0;
            for (int i : lottos) {
                if (i == 0) zero++;
                if (arr[i]) cnt++;
            }
            answer[0] = getRank(cnt + zero);
            answer[1] = getRank(cnt);
            return answer;
        }

        private int getRank(int cnt) {
            if (cnt <= 1) return 6;
            return 7 - cnt;
        }
    }
}
