package playground.navIntern2022;

import java.util.Arrays;
import java.util.Comparator;

public class Q1 {
    public static void main(String... args) {
        Solution s= new Solution();
        System.out.println(s.solution("LSMSMML"));
    }

    static class Solution {
        public String solution(String T) {
            Character[] charArr = new Character[T.length()];

            for (int i = 0; i < T.length(); i++) {
                charArr[i] = T.charAt(i);
            }
            Arrays.sort(charArr, new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    if (o1 == 'S' && o2 == 'S') return 0;
                    else if (o1 == 'S') return -1;
                    else if (o2 == 'S') return 1;
                    else if (o1 == 'M' && o2 == 'M') return 0;
                    else if (o1 == 'M') return -1;
                    else if (o2 == 'M') return 1;
                    else return 0;
                }
            });
            StringBuilder sb = new StringBuilder();
            for (char ch : charArr) {
                sb.append(ch);
            }
            return sb.toString();
        }
    }
}
