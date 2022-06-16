package string;

public class LEET_WildCard_Matching {
    public static void main(String... args) {
        Solution s = new Solution();
        s.isMatch("acdcb"
        , "a*c?b");
    }
    static class Solution {
        public boolean isMatch(String s, String p) {
            int i = 0;
            int j = 0;
            int match = 0;
            int star = -1;

            while(i < s.length()) {
                if(j < p.length() && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
                    i++;
                    j++;
                }
                else if(j < p.length() && p.charAt(j)  == '*') {
                    star = j;
                    match = i;
                    j++;
                }
                else if(star != -1) {
                    j = star + 1;
                    i = ++match;
                }
                else {
                    return false;
                }
            }

            while(j < p.length() && p.charAt(j) == '*') {
                j++;
            }

            return j == p.length();
        }
    }
}
