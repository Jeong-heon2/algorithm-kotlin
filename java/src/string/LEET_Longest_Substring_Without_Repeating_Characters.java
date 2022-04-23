package string;

import java.util.HashMap;
import java.util.HashSet;

public class LEET_Longest_Substring_Without_Repeating_Characters {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.lengthOfLongestSubstring("aabaab!bb");
    }

    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int max = 0;
            HashSet<Character> set = new HashSet<>();
            int j = 0;
            int len = s.length();
            for (int i = 0 ; i < len; i++) {
                char ch = s.charAt(i);
                if (set.contains(ch)) {
                    max = Math.max(max, set.size());
                    // 구간을 벗어난 것들을 제거
                    while (s.charAt(j) != ch) {
                        set.remove(s.charAt(j++));
                    }
                    j++;
                }
                set.add(ch);
            }
            max = Math.max(max, set.size());
            return max;
        }
    }

    static class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            int max = 0;
            // 문자와 인덱스를 저장
            HashMap<Character, Integer> map = new HashMap<>();
            int j = 0;
            int len = s.length();
            for (int i = 0 ; i < len; i++) {
                char ch = s.charAt(i);
                // 구간에서 벗어난 것들을 삭제하지 않음
                // 대신에 구간에 들어오는지를 확인
                if (map.containsKey(ch) && map.get(ch) >= j) {
                    max = Math.max(max, i - j);
                    j = map.get(ch) + 1;
                }
                map.put(ch, i);
            }
            max = Math.max(max, len - j);
            return max;
        }
    }
}
