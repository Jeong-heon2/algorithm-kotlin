package tree

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val arr = IntArray(N)
    repeat(N) {
        arr[it] = readLine().toInt()
    }
    val tree = Array<Pair<Int, Int>?>(4*N) {null}

    fun init(n: Int, start: Int, end: Int) {
        if (start == end) tree[n] = Pair(arr[start], arr[start])
        else {
            init(n*2, start, (start + end)/2)
            init(n*2 + 1, (start + end)/2 + 1, end)
            tree[n] = Pair(
                (tree[n*2]?.first ?: 0).coerceAtMost(tree[n*2 + 1]?.first ?: 0),
                (tree[n*2]?.second ?: 0).coerceAtLeast(tree[n*2 + 1]?.second ?: 0)
            )
        }
    }

    fun query(n: Int, i: Int, j: Int, start: Int, end: Int): Pair<Int, Int> {
        if (i > end || j < start) return Pair(-1, -1)
        if (i <= start && end <= j) return tree[n] ?: Pair(-1, -1)
        val resultLeft = query(n*2, i, j, start, (start + end)/2)
        val resultRight = query(n*2 + 1, i, j, (start + end)/2 + 1, end)
        return when {
            resultLeft.first == -1 && resultLeft.second == -1 -> resultRight
            resultRight.first == -1 && resultRight.second == -1 -> resultLeft
            else -> {
                Pair(
                    resultLeft.first.coerceAtMost(resultRight.first),
                    resultLeft.second.coerceAtLeast(resultRight.second)
                )
            }
        }
    }

    init(1, 0, arr.lastIndex)
    repeat(M) {
        val (a, b) = readLine().split(" ").map {it.toInt()}
        val res = query(1, a - 1, b - 1, 0, arr.lastIndex)
        bw.write(
            "${res.first} ${res.second}\n"
        )
    }
    close()
    bw.flush()
    bw.close()
}