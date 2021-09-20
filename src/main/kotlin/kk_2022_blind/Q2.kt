package kk_2022_blind

import kotlin.concurrent.fixedRateTimer


fun main() {
    val s = Q2.Solution()
    println(s.solution(999914, 3))
}
fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    var i = 2
    while (i * i <= n) {
        if (n % i++ == 0) return false
    }
    return true
}
class Q2 {
    class Solution {
        fun solution(n: Int, k: Int): Int {
            var answer = 0
            val kNotation = changeNotation(n, k)
            println(kNotation)
            //find 0P0
            val sb = StringBuilder()
            var flag = false
            for (i in kNotation.indices) {
                if (kNotation[i] == '0') {
                    if (!flag) {
                        flag = true
                        sb.clear()
                    }else {
                        val num = sb.toString()
                        if (num.isNotEmpty()) {
                            if (!hasZero(num) && isPrime(num.toLong())) {
                                answer++
                            }
                        }
                        sb.clear()
                    }
                } else {
                    sb.append(kNotation[i])
                }
            }
            //find P0
            for (a in kNotation.indices) {
                if (kNotation[a] == '0') {
                    val num = kNotation.substring(0, a)
                    if (num.isNotEmpty()) {
                        if (!hasZero(num) && isPrime(num.toLong())) {
                            answer++
                        }
                    }
                    break
                }
            }
            //find 0P
            for (k in kNotation.lastIndex downTo 0) {
                if(kNotation[k] == '0'){
                    val num = kNotation.substring(k + 1)
                    if (num.isNotEmpty()) {
                        if (!hasZero(num) && isPrime(num.toLong())) {
                            answer++
                        }
                    }
                    break
                }
            }
            //find P
            if (!hasZero(kNotation) && isPrime(kNotation.toLong())){
                answer++
            }
            return answer
        }
        fun changeNotation(n: Int, k: Int): String {
            var quot = n //몫
            var remain = 0//나머지
            val sb = StringBuilder()
            while (quot >= k) {
                remain = quot % k
                sb.append(remain)
                quot /= k
            }
            sb.append(quot)
            return sb.reverse().toString()
        }
        fun isPrime(n: Long): Boolean {
            if (n <= 1) return false
            var i = 2L
            while (i * i <= n) {
                if (n % i++ == 0L) return false
            }
            return true
        }
        fun hasZero(str: String): Boolean {
            str.forEach {
                if (it == '0') return true
            }
            return false
        }
    }
}