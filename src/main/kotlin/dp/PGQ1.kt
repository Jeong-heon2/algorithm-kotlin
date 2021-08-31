package dp
import java.util.*
class PGQ1 {
    class Solution {
        fun solution(N: Int, number: Int): Int {
            if (N == number) return 1
            var answer = -1
            val setList = mutableListOf<HashSet<Long>>()
            setList.add(HashSet())
            setList.add(HashSet())
            setList[1].add(N.toLong())
            /*
            setList[i] 는 N을 i번 써서 만들 수 있는 수 집합
            setList[4] = setList[1] 의 모든 요소와 setList[3] 모든 요소가 사칙연산합 집합  +  setList[2] 와 setList[2] + setList[3] 과 setList[1]  그리고 여기에  + NNNN
             */
            for (k in 2..8){
                val curSet = HashSet<Long>()
                val sb = StringBuilder()
                repeat(k) {
                    sb.append(N.toString())
                }
                curSet.add(sb.toString().toLong())
                for (i in 1 until k){
                    setList[i].forEach { a ->
                        setList[k-i].forEach { b ->
                            curSet.add(a + b)
                            curSet.add(a * b)
                            if (b != 0L) curSet.add(a / b)
                            curSet.add(a - b)
                        }
                    }
                }
                if (curSet.contains(number.toLong())) return k
                setList.add(curSet)
            }
            return answer
        }
    }
}