package hash;
import java.util.*;

public class PG_영어_끝말잇기 {

    class Solution {
        public int[] solution(int n, String[] words) {
            ArrayList<Integer> list = new ArrayList<>();
            HashSet<String> set = new HashSet<>();
            set.add(words[0]);
            int round = 1;
            for (int i = 1 ; i < words.length ; i++) {
                int num = i % n + 1;
                if (set.contains(words[i]) ||
                        words[i-1].charAt(words[i-1].length() - 1) != words[i].charAt(0)
                ) {
                    return new int[] {num, round};
                }
                set.add(words[i]);
                if (num == n) round++;
            }
            return new int[] {0, 0};
        }
    }
}
