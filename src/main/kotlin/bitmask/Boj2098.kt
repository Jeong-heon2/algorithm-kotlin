package bitmask

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.properties.Delegates

/*
TSP 외판원 순회
Dp + Bit mask
https://withhamit.tistory.com/246
 */
private var n by Delegates.notNull<Int>()
private lateinit var arr : Array<IntArray>
private lateinit var dp : Array<IntArray>
private const val max = 17000000

@OptIn(ExperimentalStdlibApi::class)
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt() // 도시들의 수
    arr = Array(n){IntArray(n)}
    val one = 1
    dp = Array(one.rotateLeft(n)){IntArray(n)}
    repeat(n) { i ->
        val st = StringTokenizer(readLine())
        repeat(n) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    println(tsp(0, 0))

}
@OptIn(ExperimentalStdlibApi::class)
fun tsp(visited: Int, now: Int) : Int {
    val one = 1
    val nextVisited = visited or one.rotateLeft(now)

    if (nextVisited == one.rotateLeft(n) - 1) {
        if (arr[now][0] > 0) {
            return arr[now][0]
        } else {
            return max
        }
    }

    if (dp[nextVisited][now] > 0) {
        return dp[nextVisited][now]
    }

    dp[nextVisited][now] = max
    repeat(n) { i ->
        if (i != now && (nextVisited and one.rotateLeft(i)) == 0 && arr[now][i] > 0) {
            dp[nextVisited][now] = minOf(dp[nextVisited][now], tsp(nextVisited, i) + arr[now][i])
        }
    }

    return dp[nextVisited][now]
}
