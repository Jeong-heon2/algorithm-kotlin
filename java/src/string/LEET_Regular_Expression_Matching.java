package string;

public class LEET_Regular_Expression_Matching {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isMatch("abc", ".*"));
    }
    static class Solution {
        public boolean isMatch(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();
            int j = 0;
            char pattern = p.charAt(0);
            for (int i = 0 ; i < sLen ; i++) {
                char ch = s.charAt(i);
                if (pattern == '*') {
                    if (p.charAt(j-1) != '.') {
                        if (ch != p.charAt(j-1)) {
                            if (j + 1 < pLen) pattern = p.charAt(++j);
                            else return false;
                        }
                    }
                } else if (pattern == '.') {
                    if (j + 1 < pLen) pattern = p.charAt(++j);
                    else {
                        if (i + 1 < sLen) return false;
                    }
                } else {
                    if (ch == pattern) {
                        if (j + 1 < pLen) pattern = p.charAt(++j);
                        else {
                            if (i + 1 < sLen) return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
            for (int t = j+1 ; t < pLen ; t++) {
                if (p.charAt(t) != '*') return false;
            }
            return true;
        }
    }
}
