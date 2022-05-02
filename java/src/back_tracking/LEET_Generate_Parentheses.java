package back_tracking;

import java.util.ArrayList;
import java.util.List;

public class LEET_Generate_Parentheses {

    public static void main(String[] args) {

    }

    //O(n^4 / root(n) )
    class Solution {
        List<String> ans;
        public List<String> generateParenthesis(int n) {
            ans = new ArrayList<>();
            recur(0, 0, n, new StringBuilder());
            return ans;
        }

        public void recur(int cnt, int sum, int n, StringBuilder sb) {
            if (sb.length() == n*2) {
                ans.add(sb.toString());
                return;
            }

            if (cnt < n) {
                sb.append('(');
                recur(cnt + 1, sum + 1, n, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (sum > 0) {
                sb.append(')');
                recur(cnt, sum - 1, n, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
