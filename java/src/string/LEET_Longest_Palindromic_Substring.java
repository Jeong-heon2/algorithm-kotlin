package string;

public class LEET_Longest_Palindromic_Substring {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.longestPalindrome("cbbd");
    }
    /*
    Runtime: 372 ms, faster than 25.57% of Java online submissions for Longest Palindromic Substring.
    Memory Usage: 118.3 MB, less than 10.80% of Java online submissions for Longest Palindromic Substring.
    느리고 메모리도 많이 차지한 듯 하다..
     */
    static class Solution {
        public String longestPalindrome(String s) {

            // 0,1  1,2  2,3  3,4 ... -> isPal? 구해놓고
            // 0,1,2 ->  0 == 2 이면 pal 임.
            // 0,1,2,3 -> aaaa abba
            // 0,1,2,3,4 -> abcba  aaaaa  aabaa   홀수자리개 -> 첫번째 끝에 제외한 가운데가 pal 이어야함
            // 0,1,2,3,4,5 -> aaaaaa aaccaa.  abccba  짝수자리개도 마찬가지. 첫번째 끝에 제외한 가운데가 pal 이어야함


            // 1. 첫번째 끝에 제외한 가운데가 pal 이어야함
            // 처음에  두개짝 isPal 구해놓고
            // 3자리 부터는  첫번째 == 끝번째 &&  첫, 끝 제외 가운데가 isPal?

            // [i][j] = idx i 부터 길이 j  is pal ?
            boolean[][] isPalindromes = new boolean[1001][1001];
            int len = s.length();
            int maxI = 0;
            int maxL = 0;
            for (int i = 0 ; i <= 1000 ; i++) {
                isPalindromes[i][0] = true;
                isPalindromes[i][1] = true;
            }
            for (int l = 2 ; l <= len; l++) {
                for (int i = 0; i+l-1 < len; i++) {
                    if (s.charAt(i) == s.charAt(i+l-1) && isPalindromes[i+1][l-2]) {
                        isPalindromes[i][l] = true;
                        maxI = i;
                        maxL = l;
                    }
                }
            }
            return s.substring(maxI, maxI + maxL);
        }
    }
}
