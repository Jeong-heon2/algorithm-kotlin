package sliding_window

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val L = st.nextToken().toInt()

    st = StringTokenizer(readLine())
    val deq = ArrayDeque<IntArray>()
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    repeat(N) {
        val cur = st.nextToken().toInt()
        // window 크기 체크
        if (deq.isNotEmpty() && it - deq.peekFirst()[0] >= L) {
            deq.pollFirst()
        }
        while (deq.isNotEmpty() && deq.peekLast()[1] >= cur) {
            // 새로 입력된 게 더 작으면 너네는 최소 값이 될 수 없어
            deq.pollLast()
        }
        deq.offer(intArrayOf(it, cur))
        // 덱의 first 는 항상 구간의 최소 값으로 유지할 거야
        bw.write("${deq.peekFirst()[1]} ")
    }
    bw.flush()
    bw.close()
    close()
}