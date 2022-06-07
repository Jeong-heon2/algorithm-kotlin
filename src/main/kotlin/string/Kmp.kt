package string

import java.io.BufferedReader
import java.io.InputStreamReader

// kmp 문자열 매칭 알고리즘
// p: pattern 문자열 , s: 문자열
// p가 s의 부분 문자열인가? 그렇다면 p가 등장하는 위치 (index?)
// O(|p| + |s|) 시간 복잡도 알고리즘
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val s = readLine()
    val p = readLine()

    val pi = failFunction(p)
    var j = 0
    var i = 0
    while (i < s.length) {
        if (s[i] != p[j]) {
            if (j == 0) {
                i++
            } else {
                j = pi[j-1]
            }
        } else {
            if (j == p.lastIndex) {
                println(i - j)
                return
            }
            i++
            j++
        }
    }
    println(-1)
}

fun failFunction(p: String): IntArray {
    val pi = IntArray(p.length){0}
    var j = 0
    for (i in 1 until p.length) {
        while (j > 0 && p[i] != p[j]) {
            j = pi[j-1]
        }
        if (p[i] == p[j]) {
            pi[i] = j + 1
            j = pi[i]
        } else {
            pi[j] = 0
        }
    }
    return pi
}