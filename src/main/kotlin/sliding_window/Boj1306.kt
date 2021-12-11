package sliding_window

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val L = 2 * M - 1
    val deq = ArrayDeque<IntArray>()
    st = StringTokenizer(readLine())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    repeat(N) {
        val cur = st.nextToken().toInt()
        if (deq.isNotEmpty() && it - deq.peekFirst()[0] >= L) {
            deq.pollFirst()
        }
        while (deq.isNotEmpty() && deq.peekLast()[1] <= cur) {
            deq.pollLast()
        }
        deq.offer(intArrayOf(it, cur))
        if (it >= 2 * M - 2) {
            bw.write("${deq.peekFirst()[1]} ")
        }
    }
    bw.flush()
    bw.close()
    close()
}