package impl;

public class LEET_Multiply_Strings {
    class Solution {
        public String multiply(String num1, String num2) {
            if (num1.length() == 1 && num1.equals("0") || num2.length() == 1 && num2.equals("0"))
                return "0";

            String l = "";
            String s = "";
            if (num1.length() > num2.length()) {
                l = num1;
                s = num2;
            } else {
                l = num2;
                s= num1;
            }

            String ans = ""; // 곱셈 계산 결과를 저장
            int up = 0;
            for (int j = s.length() - 1; j >= 0; j--) {
                StringBuilder sb = new StringBuilder();
                int t = s.length() - 1 - j;
                while(t-- > 0) sb.append('0');
                for (int i = l.length() - 1; i >= 0; i--) {
                    int n1 = l.charAt(i) - '0';
                    int n2 = s.charAt(j) - '0';
                    int n = n1*n2 + up;
                    sb.append(n % 10);
                    up = n / 10;
                }
                if (up != 0) sb.append(up);
                up = 0;
                ans = plus(ans, sb.reverse().toString());
            }
            return ans;
        }

        private String plus(String num1, String num2) {
            if (num1.length() == 0) return num2;
            if (num2.length() == 0) return num1;

            int i = num1.length() - 1;
            int j = num2.length() - 1;
            int up = 0;
            StringBuilder sb = new StringBuilder();
            while (i >= 0 && j >= 0) {
                int n1 = num1.charAt(i--) - '0';
                int n2 = num2.charAt(j--) - '0';
                int n = n1 + n2 + up;
                sb.append(n % 10);
                up = n / 10;
            }
            while (i >= 0) {
                int n1 = num1.charAt(i--) - '0';
                int n = up + n1;
                sb.append(n % 10);
                up = n / 10;
            }
            while (j >= 0) {
                int n1 = num2.charAt(j--) - '0';
                int n = up + n1;
                sb.append(n % 10);
                up = n / 10;
            }
            if (up != 0) sb.append(up);
            return sb.reverse().toString();
        }
    }
}
