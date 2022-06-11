package string;

public class LEET_Count_And_Say {

    class Solution {

        String[] arr = new String[31];

        public String countAndSay(int n) {
            if (n == 1) return "1";
            arr[1] = "1";
            for(int i = 2; i <= n; i++) {
                arr[i] = say(i);
            }
            return arr[n];
        }

        private String say(int n) {
            int[] cnt = new int[10];
            char pre = ' ';
            int len = arr[n-1].length();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < len ; i++) {
                char ch = arr[n-1].charAt(i);
                if (ch == pre) {
                    cnt[ch - '0']++;
                } else {
                    if (pre != ' ') {
                        sb.append(cnt[pre-'0']);
                        sb.append(pre-'0');
                    }
                    cnt[ch - '0'] = 1;
                }
                pre = ch;
            }

            sb.append(cnt[pre-'0']);
            sb.append(pre-'0');
            return sb.toString();
        }
    }
}
