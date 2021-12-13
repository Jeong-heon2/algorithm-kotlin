package tree

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var inOrder: List<Int> = listOf()
var postOrder: List<Int> = listOf()
var index: IntArray = intArrayOf() // index[i] : inOrder에서 i는 몇번 인덱스에 있니?
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    index = IntArray(n + 1)
    inOrder = readLine().split(" ").map { it.toInt() }
    postOrder = readLine().split(" ").map { it.toInt() }
    close()
    inOrder.forEachIndexed { idx, i ->
        index[inOrder[idx]] = idx
    }
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    preOrder(bw, 0, n, 0, n)
    bw.flush()
    bw.close()
}

fun preOrder(bw: BufferedWriter, il: Int, ir: Int, pl: Int, pr: Int) {
    if (pr == pl) return
    val root = postOrder[pr - 1]
    bw.write("$root ")

    val rootInOrderIdx = index[root]
    preOrder(bw, il, rootInOrderIdx, pl, rootInOrderIdx - il + pl)
    preOrder(bw, rootInOrderIdx + 1, ir, pl - (il - rootInOrderIdx), pr - 1)
}