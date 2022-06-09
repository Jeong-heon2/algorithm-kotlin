package stack;

import java.util.Stack;

// stack 을 계속 peek 해서 되돌아가다 보니 시간이 많이 걸린다.
public class LEET_Longest_Valid_Parentheses {

    public static void main(String... args) {
        Solution s = new Solution();
        System.out.println(s.longestValidParentheses(")()())"));
    }
    static class Solution {
        public int longestValidParentheses(String s) {

            Stack<PointOrPar> stack = new Stack<>();

            int len = s.length();
            int cur = 0;
            int ans = 0;
            int i = 0;
            char ch = s.charAt(0);

            while (true){
                if (ch == '(') {
                    stack.push(new PointOrPar(false, ch));
                } else {
                    if (!stack.isEmpty()) {
                        if (stack.peek().isPoint) {
                            cur += stack.pop().value;
                            continue;
                        } else {
                            if (stack.peek().value == '(') {
                                stack.pop();
                                stack.push(new PointOrPar(true, cur + 2));
                            } else {
                                if (cur != 0) stack.push(new PointOrPar(true, cur));
                                stack.push(new PointOrPar(false, ch));
                            }
                        }
                    } else {
                        if (cur != 0) stack.push(new PointOrPar(true, cur));
                        stack.push(new PointOrPar(false, ch));
                    }
                }

                if (i < len - 1) {
                    ch = s.charAt(++i);
                    cur = 0;
                } else {
                    break;
                }
            }

            int sum = 0;
            while (!stack.empty()) {
                PointOrPar pop = stack.pop();
                if (pop.isPoint) {
                    sum += pop.value;
                } else {
                    ans = Math.max(ans, sum);
                    sum = 0;
                }
            }
            ans = Math.max(ans, sum);
            return ans;
        }
    }

    static class PointOrPar {
        int value;
        private final boolean isPoint;

        public PointOrPar(boolean isPoint, int value) {
            this.isPoint = isPoint;
            this.value = value;
        }
    }

}
