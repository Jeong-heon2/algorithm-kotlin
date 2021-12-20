package tree

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (N, M, K) = readLine().split(" ").map { it.toInt() }
    val arr = LongArray(N)
    repeat(N) {
        arr[it] = readLine().toLong()
    }

    val segmentTree = LongArray(4*N)

    fun init(n: Int, start: Int, end: Int) {
        if (start == end) segmentTree[n] = arr[start].toLong()
        else {
            init(n*2, start, (start + end)/2)
            init(n*2 + 1, (start + end)/2 + 1, end)
            segmentTree[n] = segmentTree[n*2] + segmentTree[n*2 + 1]
        }
    }

    fun query(n: Int, i: Int, j: Int, start: Int, end: Int): Long {
        if (i > end || j < start) return -1
        if (i <= start && end <= j) return segmentTree[n]
        val resLeft = query(n*2, i, j, start, (start + end) /2)
        val resRight = query(n*2 + 1, i, j, (start + end) /2 + 1, end)
        return when {
            resLeft == -1L -> resRight
            resRight == -1L -> resLeft
            else -> resLeft + resRight
        }
    }

    fun update(n: Int, b: Int, c: Long, start: Int, end: Int) {
        if (b in start..end) {
            segmentTree[n] = segmentTree[n] - arr[b] + c
            if (start == end) return
            update(n*2, b, c, start, (start + end)/2)
            update(n*2 + 1, b, c, (start + end)/2 + 1, end)
        } else return
    }
    init(1, 0, arr.lastIndex)

    val bw = BufferedWriter(OutputStreamWriter(System.out))
    repeat(M + K) {
        val tokens = readLine().split(" ")
        val a = tokens[0]
        val b = tokens[1].toInt()
        val c = tokens[2].toLong()
        if (a == "1") {
            update(1, b-1, c, 0, arr.lastIndex)
            arr[b-1] = c
        } else {
            //  b ~ c 구간 합 출력
            bw.write("${query(1, b-1, c.toInt()-1, 0, arr.lastIndex)}\n")
        }
    }
    bw.flush()
    close()
    bw.close()
}