package implementation

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    val arr = Array(N+1){IntArray(N+1)}
    val dx = intArrayOf(1, -1, 0, 0)
    val dy = intArrayOf(0, 0, 1, -1)
    var maxLike = 0
    var maxEmpty = 0
    var x = N + 1
    var y = N + 1
    val one = 1
    val flags = Array(N*N + 1){ mutableListOf<Int>()}
    repeat(N*N) {
        val st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        for (i in 1..4) {
            flags[n].add(st.nextToken().toInt())
        }
        for (i in 1..N) {
            for (j in 1..N) {
                if (arr[i][j] != 0) continue
                var like = 0
                var empty = 0
                repeat(4) {
                    val nx = dx[it] + j
                    val ny = dy[it] + i
                    if (nx in 1..N && ny in 1..N) {
                        if(flags[n].contains(arr[ny][nx])) like++
                        if (arr[ny][nx] == 0) empty++
                    }
                }
                if (maxLike < like ||
                    maxLike == like && maxEmpty < empty ||
                    maxLike == like && maxEmpty == empty && y > i ||
                    maxLike == like && maxEmpty == empty && y == i && x > j) {
                        maxLike = like
                        maxEmpty = empty
                        x = j
                        y = i
                }
            }
        }
        arr[y][x] = n
        maxLike = 0
        maxEmpty = 0
        x = N + 1
        y = N + 1
    }
    var score = 0
    for (i in 1..N) {
        for (j in 1..N) {
            var like = 0
            repeat(4) {
                val nx = dx[it] + j
                val ny = dy[it] + i
                if (nx in 1..N && ny in 1..N) {
                    if (flags[arr[i][j]].contains(arr[ny][nx])) like++
                }
            }
            when(like) {
                1 -> score += 1
                2 -> score += 10
                3 -> score += 100
                4 -> score += 1000
            }
        }
    }
    println(score)
}