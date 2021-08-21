package pg_weekly_challenge

class Q2 {

    inner class Solution {
        fun solution(scores: Array<IntArray>): String {
            var answer: String = ""
            val n = scores.size
            for(i in 0 until n){
                val myScore = scores[i][i]
                var max = 0
                var min = 100
                var sum = 0
                for(j in 0 until n){
                    val score = scores[j][i]
                    if(i != j){
                        max = max.coerceAtLeast(score)
                        min = min.coerceAtMost(score)
                        sum += score
                    }
                }
                answer += if(myScore > max || myScore < min){
                    getGrade(sum / (n-1))
                }else{
                    getGrade((sum+myScore) / n)
                }
            }
            return answer
        }
        private fun getGrade(mean: Int):String = when {
            mean >= 90 -> "A"
            mean >= 80 -> "B"
            mean >= 70 -> "C"
            mean >= 50 -> "D"
            else -> "F"
        }
    }
}