package brute_force;

public class PG_양궁대회 {
    class Solution {
        int maxPoint;
        int n;
        int[] info;
        int[] lion;
        public int[] solution(int n, int[] info) {
            this.maxPoint = 0;
            this.n = n;
            this.info = info;
            this.lion = new int[11];
            dfs(0, 0, new int[11]);
            if (maxPoint == 0) return new int[]{-1};
            return this.lion;
        }

        //cnt : 현제 몇발 쐈나
        // i :  10 - i 점수 과녁
        private void dfs(int cnt, int i, int[] arr) {
            // 다 쐈으니까 점수 계산
            if (cnt == n) {
                calculate(arr);
                return;
            }
            // 화살이 남음 . 0번에 다 쏘면 됨
            if (i == 11) {
                arr[10] += n - cnt;
                calculate(arr);
                arr[10] -= n - cnt;
                return;
            }
            // i 과녁을 어파치 보다 많이 쏠지 안 쏠지 2가지 경우 탐색
            int arrow = n - cnt; //남은 화살 수
            if (arrow >= info[i] + 1) {
                arr[i] += info[i] + 1;
                dfs(cnt + info[i] + 1, i + 1, arr);
                arr[i] -= info[i] + 1;
            }
            // 안 쏘는 경우
            dfs(cnt, i + 1, arr);
        }

        // 점수 계산
        private void calculate(int[] arr) {
            int aScore = 0;
            int lScore = 0;
            for (int i = 0 ; i < 11; i++) {
                if (info[i] == 0 && arr[i] == 0) continue;
                if (info[i] >= arr[i]) aScore += 10 - i;
                else lScore += 10 - i;
            }
            int sum = lScore - aScore;
            if (sum > maxPoint) {
                maxPoint = sum;
                lion = arr.clone();
            } else if (sum == maxPoint) {
                for (int i = 10; i >= 0 ; i--) {
                    if (lion[i] < arr[i]) {
                        lion = arr.clone();
                        return;
                    }
                    if (lion[i] > arr[i]) return;
                }
            }
        }
    }
}
