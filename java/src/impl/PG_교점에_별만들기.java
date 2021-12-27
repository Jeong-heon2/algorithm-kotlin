package impl;
import java.util.*;

public class PG_교점에_별만들기 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] arr = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
        String[] res = s.solution(arr);
        for (String str : res) {
            System.out.println(str);
        }
    }

    static class Solution {
        public String[] solution(int[][] line) {
            ArrayList<int[]> list = new ArrayList<>();
            // 2 개 쌍 조합
            for (int i = 0 ; i < line.length ; i++) {
                for (int j = i + 1 ; j < line.length ; j++) {
                    int a = line[i][0], b = line[i][1], e = line[i][2];
                    int c = line[j][0], d = line[j][1], f = line[j][2];
                    // 교점이 정수형 좌표냐 ? -> 저장
                    // 정수좌표를 어떻게 알까? 나누어 떨
                    long bunMo = (long)a*d - (long)b*c;
                    if (bunMo == 0) continue;
                    long xRemainder = ((long)b*f - (long)e*d) % bunMo;
                    long yRemainder = ((long)e*c - (long)a*f) % bunMo;
                    if (xRemainder != 0 || yRemainder != 0) continue;
                    long x = ((long)b*f - (long)e*d) / bunMo;
                    long y = ((long)e*c - (long)a*f) / bunMo;
                    list.add(new int[] {(int)y, (int)x});
                }
            }
            //y좌표 오름차순 정렬
            list.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            // 정수형 좌표중 y 가 가장 큰 것과 작은 것의 크기 차이 = 배열의 높이
            int h = Math.abs(list.get(0)[0] - list.get(list.size() - 1)[0]) + 1;
            // 움직여야할 y 값  구하기
            int movY = -list.get(0)[0];
            //x좌표 오름차순 정렬
            list.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });
            // 정수형 좌표중 x 가 가장 큰 것과 작은 것의 크기 차이 = 배열의 너비
            int w = Math.abs(list.get(0)[1] - list.get(list.size() - 1)[1]) + 1;
            int movX = -list.get(0)[1];
            boolean[][] arr = new boolean[h][w];
            for (int[] point : list) {
                point[0] = point[0] + movY;
                point[1] = point[1] + movX;
                arr[point[0]][point[1]] = true;
            }
            // 배열의 만든다.
            // 정수형 좌표들을 만든 배열에 매핑한다
            String[] answer = new String[h];
            for (int i = 0 ; i < h ; i++) {
                int y = h - i - 1;
                StringBuilder sb = new StringBuilder();
                for (int j = 0 ;j < w ; j++) {
                    if (arr[y][j]) sb.append("*");
                    else sb.append(".");
                }
                answer[i] = sb.toString();
            }
            return answer;
        }
    }
}
