package math

import kotlin.math.abs

class Solution {
    fun divide(dividend: Int, divisor: Int): Int {
        val sign = if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) 1 else -1
        var n = abs(dividend.toLong())
        val div = abs(divisor.toLong())

        var ans = 0L
        while (n >= div) {
            var tmp = 1L
            while (div * tmp.shl(1) < n) {
                tmp = tmp.shl(1)
            }
            n -= tmp * div
            ans += tmp
        }
        val res = sign * ans
        return when {
            res > Int.MAX_VALUE -> Int.MAX_VALUE
            res < Int.MIN_VALUE -> Int.MIN_VALUE
            else -> res.toInt()
        }
    }
}


fun main() {
    val a = Int.MIN_VALUE
    println(a)
    val b : Long = abs(a.toLong())
    println(b)
}