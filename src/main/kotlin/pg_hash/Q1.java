package pg_hash;

import java.util.HashMap;

public class Q1 {

    public static void main(String[] args) {

    }
    class Solution {
        public String solution(String[] participant, String[] completion) {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            for (String name : participant) {
                Integer res = map.putIfAbsent(name, 1);
                if (res != null) map.put(name, res + 1);
            }
            for (String name : completion) {
                Integer res = map.computeIfPresent(name, (k, v) -> v - 1);
                if (res != null && res == 0) map.remove(name);
            }
            for (String name : map.keySet()) {
                return name;
            }
            return "";
        }
    }
}