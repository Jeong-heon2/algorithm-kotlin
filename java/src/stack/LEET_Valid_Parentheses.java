package stack;

import java.util.Stack;

public class LEET_Valid_Parentheses {
    class Solution {
        public boolean isValid(String s) {
            int len = s.length();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < len; i++) {
                char ch = s.charAt(i);
                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                } else {
                    if (stack.size() <= 0) return false;
                    char pre = stack.pop();
                    if ((pre == '(' && ch != ')') ||
                            (pre == '{' && ch != '}') ||
                            (pre == '[' && ch != ']'))
                        return false;
                }
            }
            return stack.size() == 0;
        }
    }
}
