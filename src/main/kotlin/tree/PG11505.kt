package tree

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (N, M, K) = readLine().split(" ").map { it.toInt() }
    val arr = IntArray(N)
    repeat(N) {
        arr[it] = readLine().toInt()
    }
    val segTree = LongArray(4*N)
    fun init(n: Int, start: Int, end: Int) {
        if (start == end) segTree[n] = arr[start].toLong()
        else {
            init(n*2, start, (start+ end)/2)
            init(n*2 + 1, (start + end) / 2 + 1, end)
            segTree[n] = (segTree[n*2] * segTree[n*2 + 1]) % 1_000_000_007
        }
    }
    init(1, 0, arr.lastIndex)

    fun update(n: Int, start: Int, end: Int, changeIdx: Int, changeValue: Int): Long {
        return if (changeIdx in start..end) {
            if (start == end) {
                arr[changeIdx] = changeValue
                segTree[n] = changeValue.toLong()
                changeValue.toLong()
            } else {
                update(n*2, start, (start + end) / 2, changeIdx, changeValue)
                update(n*2 + 1 , (start + end) / 2 + 1, end, changeIdx, changeValue)
                segTree[n] = (segTree[n*2] * segTree[n*2 + 1]) % 1_000_000_007
                segTree[n]
            }
        } else {
            segTree[n]
        }
    }

    fun query(n: Int, start: Int, end: Int, i: Int, j: Int): Long{
        if (j < start || i > end) return -1
        if (i <= start && end <= j) return segTree[n]
        val resultLeft = query(n*2, start, (start + end)/2, i, j)
        val resultRight = query(n*2 + 1, (start + end)/2 + 1, end, i, j)
        return when {
            resultLeft == -1L -> resultRight
            resultRight == -1L -> resultLeft
            else -> {
                (resultLeft * resultRight) % 1_000_000_007
            }
        }
    }
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(M+K) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        if (a == 1) {
            //update
            update(1, 0, arr.lastIndex, b - 1, c)
        } else {
            // b~ c 구간의 곱 출력
            bw.write(query(1, 0, arr.lastIndex, b - 1, c - 1).toString() + "\n")
        }
    }
    close()
    bw.flush()
    bw.close()
}