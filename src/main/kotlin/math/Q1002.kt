package math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val T = readLine().toInt()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(T) {
        val st = StringTokenizer(readLine())
        val x1 = st.nextToken().toInt()
        val y1 = st.nextToken().toInt()
        val r1 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()
        val r2 = st.nextToken().toInt()
        val distance = kotlin.math.hypot((x1 - x2).toDouble(), (y1 - y2).toDouble())

        if (x1 == x2 && y1 == y2) {
            if (r1 == r2) {
                bw.write("-1\n")
            } else {
                bw.write("0\n")
            }
        } else if (r1 + r2 > distance && kotlin.math.abs(r1 - r2) < distance) {
            bw.write("2\n")
        } else if (r1 + r2 < distance || kotlin.math.abs(r1 - r2) > distance ){
            bw.write("0\n")
        } else {
            bw.write("1\n")
        }
    }
    bw.flush()
    close()
    bw.close()
}
