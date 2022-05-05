package map;

import java.util.*;

public class PG_신고결과받기 {

    class Solution {

        public int[] solution(String[] id_list, String[] report, int k) {
            HashMap<String, HashSet<String>> map = new HashMap<>();
            HashMap<String, Integer> res = new HashMap<>();
            for (int i = 0 ; i < id_list.length ; i++) {
                res.put(id_list[i], 0);
            }
            for (int i = 0 ; i < report.length ; i++) {
                String[] tokens = report[i].split(" ");
                if (map.containsKey(tokens[1])) {
                    map.get(tokens[1]).add(tokens[0]);
                } else {
                    HashSet<String> set = new HashSet<>();
                    set.add(tokens[0]);
                    map.put(tokens[1], set);
                }
            }
            for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {
                if (entry.getValue().size() >= 2) {
                    for (String id : entry.getValue()) {
                        res.put(id, res.get(id) + 1);
                    }
                }
            }

            int[] answer = new int[id_list.length];
            for (int i = 0 ; i < answer.length; i++) {
                answer[i] = res.get(id_list[i]);
            }
            return answer;
        }
    }
}
