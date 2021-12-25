package implementation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var t = readLine().toInt()
    val dx = intArrayOf(1, -1, 0 ,0)
    val dy = intArrayOf(0, 0, 1, -1)
    while (t-- > 0) {
        val (w, h) = readLine().split(" ").map { it.toInt() }
        val arr = Array(h) { CharArray(w) }
        val posQ = LinkedList<IntArray>()
        val fireQ = LinkedList<IntArray>()
        repeat(h) { y ->
            val input = readLine()
            repeat(w) { x ->
                when (input[x]) {
                    '@' -> {
                        posQ.offer(intArrayOf(y, x))
                    }
                    '*' -> {
                        fireQ.offer(intArrayOf(y, x))
                    }
                }
                arr[y][x] = input[x]
            }
        }
        fun fire() {
            repeat(fireQ.size) {
                val cur = fireQ.poll()
                for (i in 0..3) {
                    val ny = cur[0] + dy[i]
                    val nx = cur[1] + dx[i]
                    if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue
                    if (arr[ny][nx] == '.') {
                        arr[ny][nx] = '*'
                        fireQ.offer(intArrayOf(ny, nx))
                    }
                }
            }
        }
        fun move(): Int {
            val visited = Array(h){BooleanArray(w)}
            visited[posQ.peek()[0]][posQ.peek()[1]] = true
            var cnt = 0
            while (posQ.isNotEmpty()) {
                //불 옮기기
                fire()
                //움직이기
                var qSize = posQ.size
                while (qSize-- > 0) {
                    val cur = posQ.poll()
                    for (i in 0..3) {
                        val ny = cur[0] + dy[i]
                        val nx = cur[1] + dx[i]
                        if (nx < 0 || nx >= w || ny < 0 || ny >= h) return cnt + 1
                        if (!visited[ny][nx] && arr[ny][nx] == '.') {
                            visited[ny][nx] = true
                            posQ.offer(intArrayOf(ny, nx))
                        }
                    }
                }
                cnt++
            }
            return -1
        }
        val res = move()
        if (res == -1) bw.write("IMPOSSIBLE\n")
        else bw.write("$res\n")
    }
    close()
    bw.flush()
    bw.close()
}