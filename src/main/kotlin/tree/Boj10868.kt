package tree

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = IntArray(n)
    repeat(n) {
        arr[it] = readLine().toInt()
    }

    val segmentTree = IntArray(4 * n)

    fun init(n: Int, start: Int, end: Int) {
        if (start == end) segmentTree[n] = arr[start]
        else {
            init(n*2, start, (start + end)/2)
            init(n*2 + 1, (start + end)/2 + 1, end)
            segmentTree[n] = segmentTree[n*2].coerceAtMost(segmentTree[n*2 + 1])
        }
    }

    fun query(n: Int, i: Int, j: Int, start: Int, end: Int): Int {
        if (i > end || j < start) return -1
        if (i <= start && end <= j) return segmentTree[n]
        val resultLeft = query(n*2, i, j, start, (start + end)/2)
        val resultRight = query(n*2 + 1, i, j, (start + end)/2 + 1, end)
        return if (resultLeft == -1) resultRight
        else if (resultRight == -1) resultLeft
        else {
            resultLeft.coerceAtMost(resultRight)
        }
    }

    init(1, 0, arr.lastIndex)

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        bw.write(query(1, a - 1, b - 1, 0, arr.lastIndex).toString() + "\n")
    }
    close()
    bw.flush()
    bw.close()
}
