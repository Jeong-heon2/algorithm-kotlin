package kk_2022_blind

fun main() {
    val fees = intArrayOf(180, 5000, 10, 600)
    val records = arrayOf("05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT")
    val s = Q3.Solution()
    s.solution(fees, records)
}
class Q3 {
    class Solution {
        fun solution(fees: IntArray, records: Array<String>): IntArray {
            val carInfoMap = mutableMapOf<Int, MutableList<Int>>()
            records.forEach {
                val tokens = it.split(" ")
                val carNum = tokens[1].toInt()
                val time = convertMinutes(tokens[0])
                if (!carInfoMap.containsKey(carNum)) {
                    carInfoMap[carNum] = mutableListOf()
                }
                carInfoMap[carNum]?.add(time)
            }

            //차량 번호 순 정렬
            val sortedCarInfoMap = carInfoMap.toSortedMap()
            var answer = IntArray(sortedCarInfoMap.size)
            //요금 계산
            var i = 0
            sortedCarInfoMap.forEach{
                if(it.value.size % 2 != 0){
                    sortedCarInfoMap[it.key]?.add(1439)
                }
                //누적 주차 시간
                var sumTime = 0
                var j = 0
                while (j + 2 <= it.value.size) {
                    sumTime += it.value[j+1] - it.value[j]
                    j += 2
                }
                //주차 요금
                if (sumTime <= fees[0]) {
                    answer[i] = fees[1]
                } else {
                    answer[i] = fees[1] + cal(sumTime - fees[0], fees[2]) * fees[3]
                }
                i++
            }
            return answer
        }
        fun convertMinutes(str: String): Int{
            val tokens = str.split(":")
            return tokens[0].toInt() * 60 + tokens[1].toInt()
        }
        fun cal(time: Int, divide: Int): Int{
            val quot = time / divide
            val remain = time % divide
            if (remain > 0) return quot + 1
            else return quot
        }
    }
}