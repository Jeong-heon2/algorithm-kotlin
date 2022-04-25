package string;

public class LEET_Longest_Common_Prefix {
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i < 200; i++) {
                if (i >= strs[0].length()) break;
                char ch = strs[0].charAt(i);
                for (int j = 0 ; j < strs.length; j++) {
                    if (i >= strs[j].length()) return sb.toString();
                    if (ch != strs[j].charAt(i)) return sb.toString();
                }
                sb.append(ch);
            }
            return sb.toString();
        }
    }
}
