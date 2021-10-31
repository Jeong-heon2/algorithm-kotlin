package implementation

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

var N = 0
var M = 0
var fuel = 0
val MAX_M = 400
val dx = arrayOf(1,-1,0,0)
val dy = arrayOf(0,0,1,-1)
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    var st = StringTokenizer(readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    fuel = st.nextToken().toInt()
    val map = Array(N){
        Array(N){0}
    }
    for (i in 0 until N) {
        st = StringTokenizer(readLine())
        for (j in 0 until N) {
            map[i][j] = st.nextToken().toInt()
        }
    }
    st = StringTokenizer(readLine())
    var taxiPos = Pos(st.nextToken().toInt() - 1, st.nextToken().toInt() - 1)
    var idx = MAX_M + 1
    val dstInfoMap = mutableMapOf<Int, Pos>()
    for (i in 0 until M) {
        val tokens = readLine().split(" ")
        val startY = tokens[0].toInt() - 1
        val startX = tokens[1].toInt() - 1
        val dstY = tokens[2].toInt() - 1
        val dstX = tokens[3].toInt() - 1
        map[startY][startX] = idx
        dstInfoMap[idx++] = Pos(dstY, dstX)
    }
    while (M-- > 0) {
        val passengerRes = bfs(taxiPos, map) { pos ->
            map[pos.y][pos.x] > MAX_M
        }
        if (fuel < passengerRes.distance || passengerRes.distance == -1) {
            //연료 바닥
            println(-1)
            return@main
        }
        fuel -= passengerRes.distance
        val dst = dstInfoMap[map[passengerRes.y][passengerRes.x]]
        map[passengerRes.y][passengerRes.x] = 0
        taxiPos = Pos(passengerRes.y, passengerRes.x)

        val destRes = bfs(taxiPos, map) { pos ->
            pos.y == dst?.y && pos.x == dst.x
        }
        if (fuel < destRes.distance || destRes.distance == -1) {
            //연료 바닥
            println(-1)
            return@main
        }
        fuel += destRes.distance
        taxiPos = Pos(destRes.y, destRes.x)
    }
    println(fuel)
}
data class Pos(
    val y: Int,
    val x: Int,
) : Comparable<Pos> {
    override fun compareTo(other: Pos): Int {
        val compY = y - other.y
        return if (compY != 0) {
            compY
        } else {
            x - other.x
        }
    }
}
data class Res(
    val y: Int,
    val x: Int,
    val distance: Int,
)

fun bfs(start: Pos, map: Array<Array<Int>>, checkTarget: (Pos) -> Boolean): Res {
    val q = LinkedList<Pos>()
    val visited = Array(N){BooleanArray(N)}
    q.offer(start)
    visited[start.y][start.x] = true
    var cnt = 0
    val resList = mutableListOf<Pos>()
    while (q.isNotEmpty()) {
        var qSize = q.size
        while (qSize-- > 0) {
            val cur = q.poll()
            if (checkTarget(cur)) {
                resList.add(cur)
                continue
            }

            for (i in 0..3) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue
                if (!visited[ny][nx] && map[ny][nx] != 1) {
                    q.offer(Pos(ny, nx))
                    visited[ny][nx] = true
                }
            }
        }
        if (resList.size > 0) {
            resList.sort()
            return Res(resList[0].y, resList[0].x, cnt)
        }
        cnt++
    }
    return Res(-1, -1, -1)
}
