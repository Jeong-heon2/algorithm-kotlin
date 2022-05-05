package map;
import java.util.*;
public class PG_주차_요금_계산 {
    class Solution {
        public int[] solution(int[] fees, String[] records) {
            int maxTime = 1439;
            // 차량번호, 누적 시간
            TreeMap<String, Integer> res = new TreeMap<>();
            for (String record : records) {
                String[] tokens = record.split(" ");
                int time = tokens[2].equals("IN") ? -1 : 1;
                time *= getMinutes(tokens[0]);
                res.put(tokens[1], res.getOrDefault(tokens[1], 0) + time);
            }

            int[] answer = new int[res.size()];
            int i = 0;
            for (Map.Entry<String, Integer> entry : res.entrySet()) {
                if (entry.getValue() < 0) {
                    res.put(entry.getKey(), maxTime + entry.getValue());
                }
            }
            for (Map.Entry<String, Integer> entry : res.entrySet()) {
                int time = entry.getValue();
                if (entry.getValue() <= 0) time += maxTime;
                if (time < fees[0]) answer[i++] = fees[1];
                else {
                    int remain = (time - fees[0]) % fees[2] > 0 ? fees[3] : 0;
                    answer[i++] = fees[1] + ((time - fees[0]) / fees[2]) * fees[3] + remain;
                }
            }
            return answer;
        }

        private int getMinutes(String time) {
            String[] tokens = time.split(":");
            return Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]);
        }
    }
}
