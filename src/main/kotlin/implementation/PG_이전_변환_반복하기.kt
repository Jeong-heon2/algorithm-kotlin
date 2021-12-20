package implementation

class PG_이전_변환_반복하기 {
    class Solution {
        fun solution(s: String): IntArray {
            var ones = 0
            var zeros = 0
            s.forEach {
                if (it == '0') zeros++
                else ones++
            }
            var cnt = 0
            var removedZeroCnt = zeros
            while (ones != 1 || zeros != 0) {
                val res = change(ones)
                ones = res[0]
                zeros = res[1]
                removedZeroCnt += zeros
                cnt++
            }
            return intArrayOf(cnt, removedZeroCnt)
        }

        fun change(oneN: Int): IntArray {
            if (oneN == 1) return intArrayOf(1, 0)
            var zeros = 0
            var ones = 1
            var quo = oneN
            while (quo > 1) {
                if (quo % 2 == 0) zeros++
                else ones++
                quo = quo/2
            }
            return intArrayOf(ones, zeros)
        }
    }
}