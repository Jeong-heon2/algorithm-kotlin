package kk_2022_blind

class Q1 {
    class Solution {
        fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
            val idxMap = mutableMapOf<String,Int>()
            for (i in id_list.indices) {
                idxMap[id_list[i]] = i
            }
            val reportMap = mutableMapOf<String,HashSet<Int>>()
            report.forEach {
                val tokens = it.split(" ")
                if (reportMap.containsKey(tokens[1])) {
                    idxMap[tokens[0]]?.let { idx ->
                        reportMap[tokens[1]]?.add(idx)
                    }
                } else {
                    reportMap[tokens[1]] = HashSet()
                    idxMap[tokens[0]]?.let { idx ->
                        reportMap[tokens[1]]?.add(idx)
                    }
                }
            }
            val answer = IntArray(id_list.size)
            reportMap.forEach {
                if (it.value.size >= k) {
                    it.value.forEach { idx ->
                        answer[idx]++
                    }
                }
            }
            return answer
        }
    }
}