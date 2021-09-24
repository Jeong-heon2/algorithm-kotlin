package dijkstra

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val M = st.nextToken().toInt()
    val N = st.nextToken().toInt()
    val arr = Array(N){BooleanArray(M)}
    repeat(N) {
        val input = readLine()
        var j = 0
        input.forEach { c ->
            arr[it][j++] = c != '0'
        }
    }
    println(dijkstra(arr, N, M))
}
fun dijkstra(arr : Array<BooleanArray>, N: Int, M: Int) : Int{
    val dx = arrayOf(1, -1, 0, 0)
    val dy = arrayOf(0, 0, 1, -1)
    val dist = Array(N){IntArray(M){Int.MAX_VALUE}}
    val visited = Array(N){BooleanArray(M)}
    dist[0][0] = 0
    val pq = PriorityQueue<Edge>()
    pq.offer(Edge(intArrayOf(0,0), 0))

    while (!pq.isEmpty()) {
        val cur = pq.poll()
        if (visited[cur.next[0]][cur.next[1]]) continue
        visited[cur.next[0]][cur.next[1]] = true

        for (i in 0..3) {
            val ny = dy[i] + cur.next[0]
            val nx = dx[i] + cur.next[1]
            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue
            if (!visited[ny][nx]) {
                val cost = if (arr[ny][nx]) dist[cur.next[0]][cur.next[1]] + 1 else dist[cur.next[0]][cur.next[1]]
                if (dist[ny][nx] > cost) {
                    dist[ny][nx] = cost
                    pq.offer(Edge(intArrayOf(ny, nx), dist[ny][nx]))
                }
            }
        }
    }
    return dist[N-1][M-1]
}
class Edge(val next: IntArray, private val w: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return this.w - other.w
    }
}