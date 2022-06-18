package impl;

import java.util.ArrayList;

public class LEET_Fraction_Addition_and_Subtraction {
    class Solution {
        public String fractionAddition(String expression) {
            ArrayList<int[]> nums = new ArrayList<>();
            int i = 0;
            int len = expression.length();
            int lcd = 1;
            while (i < len) {
                StringBuilder sb = new StringBuilder();
                int[] num = new int[2];
                while (i < len && expression.charAt(i) != '/') {
                    sb.append(expression.charAt(i++));
                }
                num[0] = Integer.parseInt(sb.toString());
                sb.setLength(0);
                i++;
                while (i < len && expression.charAt(i) != '-' && expression.charAt(i) != '+') {
                    sb.append(expression.charAt(i++));
                }
                num[1] = Integer.parseInt(sb.toString());
                nums.add(num);
                lcd = lcd(num[1], lcd);
            }
            int numerator = 0;
            int denominator = lcd;
            for (int[] num: nums) {
                numerator += num[0] * (lcd / num[1]);
            }
            int gcd = gcd(numerator, denominator);
            return (numerator / gcd) + "/" + (denominator/gcd);
        }

        private int gcd(int a, int b) {
            if (b == 0) return Math.abs(a);
            else return gcd(b, a % b);
        }

        private int lcd(int a, int b) {
            int g = gcd(a, b);
            return g * (a/g) * (b/g) ;
        }
    }
}
