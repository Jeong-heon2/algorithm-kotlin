package math;

public class PG_k진수에서_소수_개수_구하기 {
    class Solution {
        public int solution(int n, int k) {
            int answer = 0;
            String kBinary = toBinary(n, k);
            StringBuilder sb = new StringBuilder();
            int len = kBinary.length();
            for (int i = 0 ; i < len ; i++) {
                char ch = kBinary.charAt(i);
                if (ch == '0') {
                    if (sb.length() != 0) {
                        if (isPrimeNumber(Long.parseLong(sb.toString()))) {
                            answer++;
                        }
                    }
                    sb.setLength(0);
                } else {
                    sb.append(ch);
                }
            }
            if (sb.length() != 0) {
                if (isPrimeNumber(Long.parseLong(sb.toString()))) {
                    answer++;
                }
            }
            return answer;
        }

        // k 진수로 변환
        private String toBinary(int n, int k) {
            StringBuilder sb = new StringBuilder();
            int q = n;
            while (q > 0) {
                sb.append(q % k);
                q /= k;
            }
            return sb.reverse().toString();
        }

        // 소수인지 판별
        private boolean isPrimeNumber(long n) {
            if (n < 2) return false;
            if (n == 2) return true;
            for(long i=2; i*i<=n; i++){
                if(n % i == 0) return false;
            }
            return true;
        }
    }
}
