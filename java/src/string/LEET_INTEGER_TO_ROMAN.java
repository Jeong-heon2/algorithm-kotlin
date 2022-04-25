package string;

public class LEET_INTEGER_TO_ROMAN {
    public static void main(String[] args) {

    }
    static class Solution {
        public String intToRoman(int num) {
            String ans = "";
            for (int i = num , j = 0; i > 0 ; j++, i /= 10 ) {
                ans = toRoman(i % 10, j) + ans;
            }
            return ans;
        }

        private String toRoman(int v, int n) {
            if (v == 0) return "";
            if (v <= 3) {
                if (n == 0) return mul("I", v);
                else if (n == 1) return mul("X", v);
                else if (n == 2) return mul("C", v);
                else return mul("M", v);
            } else if (v == 4) {
                if (n == 0) return "IV";
                else if (n == 1) return "XL";
                else if (n == 2) return "CD";
                else return mul("M", v);
            } else if (v < 9) {
                if (n == 0) return "V" + mul("I", v - 5);
                else if (n == 1) return "L" + mul("X", v - 5);
                else if (n == 2) return "D" + mul("C", v - 5);
                else return mul("M", v);
            } else {
                if (n == 0) return "IX";
                else if (n == 1) return "XC";
                else if (n == 2) return "CM";
                else return mul("M", v);
            }
        }

        private String mul(String s, int n) {
            String res = "";
            for (int i = 0 ; i < n ; i++) {
                res += s;
            }
            return res;
        }
    }
}
