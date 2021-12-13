package tree

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val arr = Array(26){CharArray(2){'.'} }
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    repeat(N) {
        val st = StringTokenizer(readLine())
        val parent = st.nextToken()[0]
        val left = st.nextToken()[0]
        val right = st.nextToken()[0]
        arr[parent - 'A'][0] = left
        arr[parent - 'A'][1] = right
    }
    close()

    val bw = BufferedWriter(OutputStreamWriter(System.out))
    preOrder('A', bw)
    bw.write("\n")
    inOrder('A', bw)
    bw.write("\n")
    postOrder('A', bw)
    bw.write("\n")

    bw.flush()
    bw.close()
}

private fun preOrder(cur: Char, bw: BufferedWriter) {
    bw.write(cur.toString())
    val idx = cur - 'A'
    if (arr[idx][0] != '.') preOrder(arr[idx][0], bw)
    if (arr[idx][1] != '.') preOrder(arr[idx][1], bw)
}

private fun inOrder(cur: Char, bw: BufferedWriter) {
    val idx = cur - 'A'
    if (arr[idx][0] != '.') inOrder(arr[idx][0], bw)
    bw.write(cur.toString())
    if (arr[idx][1] != '.') inOrder(arr[idx][1], bw)
}

private fun postOrder(cur: Char, bw: BufferedWriter) {
    val idx = cur - 'A'
    if (arr[idx][0] != '.') postOrder(arr[idx][0], bw)
    if (arr[idx][1] != '.') postOrder(arr[idx][1], bw)
    bw.write(cur.toString())
}