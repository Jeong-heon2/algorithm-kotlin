package string;

public class LEET_zigzag_conversion {

    class Solution {
        public String convert(String s, int numRows) {
            if (numRows == 1) return s;
            int len = s.length();
            int interval = 2 * numRows - 2;
            StringBuilder sb = new StringBuilder();
            //첫줄
            for (int i = 0 ; i < len ; i += interval) {
                sb.append(s.charAt(i));
            }
            //그 사이 줄들
            for (int r = 1; r < numRows - 1; r++) {
                for (int i = r; i < len; i += interval) {
                    sb.append(s.charAt(i));
                    int j = (i - r) + interval - r;
                    if (j < len) sb.append(s.charAt(j));

                }
            }
            //막줄
            for (int i = numRows-1 ; i < len ; i += interval) {
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }
    }
}
