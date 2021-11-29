package stack;

import java.util.Stack;

public class PG_짝지어제거하기 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("abccaabaa"));
    }

    static class Solution
    {
        public int solution(String s)
        {
            if (s.length() == 1) return 0;

            Stack<Character> stack = new Stack();

            for (int i = 0; i < s.length(); i++) {
                if (stack.size() > 0 && s.charAt(i) == stack.peek()) stack.pop();
                else stack.push(s.charAt(i));
            }
            if (stack.size() > 0) return 0;
            else return 1;

        }
    }
}
