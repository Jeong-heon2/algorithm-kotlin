package playground

import java.io.BufferedReader
import java.io.InputStreamReader


var ans = 0L
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))){
    val (N, K) = readLine().split(" ").map { it.toInt() }
    val arr = IntArray(N) { it }
    val visited = BooleanArray(N)
    combination(arr, visited, 0, N, K)
    println(ans % 1000000007L)
}

fun combination(arr: IntArray, visited: BooleanArray, start: Int, n: Int, r: Int) {
    if (r == 0) {
        for (i in 0 until n) {
            var sum = 0
            if (visited[i]) {
                sum += arr[i]
            }
            if (sum % n == 0) ans++
        }
    } else {
        for (i in start until n) {
            visited[i] = true
            combination(arr, visited, i + 1, n, r - 1)
            visited[i] = false
        }
    }
}