package binarysearch

import java.util.*;

class PG_입국심사 {
    fun solution(n: Int, times: IntArray): Long {
        return binarySearch(n, times)
    }

    private fun binarySearch(n: Int, times: IntArray): Long {
        Arrays.sort(times);
        var left = 0L
        var right = times[times.lastIndex].toLong() * (n / times.size + 1)
        var answer = Long.MAX_VALUE
        while (left <= right) {
            val mid = (left + right) / 2
            val checkedPeopleCount = checkPeople(mid, times)
            if (checkedPeopleCount >= n) {
                answer = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return answer
    }

    private fun checkPeople(time: Long, times: IntArray): Long {
        return times.fold(0L) { acc , i ->
            acc + time / i
        }
    }
}