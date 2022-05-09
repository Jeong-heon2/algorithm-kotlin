package playground.ki2022;

public class Q1 {
    public static void main(String... args) {
        Solution s= new Solution();
        s.solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5});
    }
    static class Solution {
        public String solution(String[] survey, int[] choices) {
            String answer = "";
            int[] points = new int[8];
            for (int i = 0 ; i < survey.length; i++) {
                switch(choices[i]) {
                    case 1: points[getIdx(survey[i].charAt(0))] += 3;
                    case 2: points[getIdx(survey[i].charAt(0))] += 2;
                    case 3: points[getIdx(survey[i].charAt(0))] += 1;
                    case 5: points[getIdx(survey[i].charAt(1))] += 1;
                    case 6: points[getIdx(survey[i].charAt(1))] += 2;
                    case 7: points[getIdx(survey[i].charAt(1))] += 3;
                    default:
                }
            }
            if (points[0] >= points[1]) answer += "R"; else answer += "T";
            if (points[2] >= points[3]) answer += "C"; else answer += "F";
            if (points[4] >= points[5]) answer += "J"; else answer += "M";
            if (points[6] >= points[7]) answer += "A"; else answer += "N";
            return answer;
        }

        private int getIdx(char ch) {
            switch(ch) {
                case 'R': return 0;
                case 'T': return 1;
                case 'C': return 2;
                case 'F': return 3;
                case 'J': return 4;
                case 'M': return 5;
                case 'A': return 6;
                default: return 7;
            }
        }
    }
}
