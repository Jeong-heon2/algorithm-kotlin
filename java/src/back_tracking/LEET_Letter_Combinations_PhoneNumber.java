package back_tracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LEET_Letter_Combinations_PhoneNumber {
    class Solution {
        String digits;
        List<String> list = new ArrayList<String>();
        int n;
        HashMap<Integer, List<Character>> map;
        public List<String> letterCombinations(String digits) {
            this.digits = digits;
            this.n = digits.length();
            initMap();
            recur(0, new StringBuilder());
            return list;
        }
        private void initMap() {
            map = new HashMap<>();
            for (int i = 2; i <= 6; i++) {
                List<Character> l = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    l.add((char)((i-2)*3 + 'a' + j));
                }
                map.put(i, l);
            }
            map.put(7, Arrays.asList('p', 'q', 'r', 's'));
            map.put(8, Arrays.asList('t', 'u', 'v'));
            map.put(9, Arrays.asList('w', 'x', 'y', 'z'));
        }
        private void recur(int i, StringBuilder sb) {
            if (i == n) {
                if (sb.length() > 0) list.add(sb.toString());
                return;
            }
            int n = digits.charAt(i) - '0';
            for (char ch : map.get(n)) {
                sb.append(ch);
                recur(i + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
