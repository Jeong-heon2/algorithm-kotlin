package graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

val dx = arrayOf(1,-1,0,0)
val dy = arrayOf(0,0,1,-1)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val arr = Array(N+1){Array(M+1){false}}
    for(i in 1 .. N){
        val input = readLine()
        for( j in 1 .. M){
            arr[i][j] = input[j-1] == '1'
        }
    }
    println(bfs(arr, N, M))
}
fun bfs(arr: Array<Array<Boolean>>, N: Int, M: Int): Int{
    //visited[y][x][0] = 벽을 부수지 않은 상태로 y, x 방문 한 적이 있는가?
    //visited[y][x][1] = 벽을 부순 상태로 y, x 방문 한 적이 있는가?
    val visited = Array(N+1){Array(M+1){Array(2){false} } }
    val q = LinkedList<Pos>()
    q.offer(Pos(1,1,false))
    visited[1][1][0] = true
    var cnt = 1
    while(q.isNotEmpty()){
        var qSize = q.size
        while(qSize-- > 0){
            val cur = q.poll()
            if(cur.y == N && cur.x == M) return cnt
            for(i in 0..3){
                val ny = cur.y + dy[i]
                val nx = cur.x + dx[i]
                if(ny < 1 || ny > N || nx < 1 || nx > M) continue
                //다음 방문할 곳이 벽인가?
                if(arr[ny][nx]){
                    if(!cur.isBreak && !visited[ny][nx][1]){
                        visited[ny][nx][1] = true
                        q.offer(Pos(ny,nx,true))
                    }
                }else{
                    if(cur.isBreak){
                        if(!visited[ny][nx][1]){
                            visited[ny][nx][1] = true
                            q.offer(Pos(ny,nx,true))
                        }
                    }else{
                        if(!visited[ny][nx][0]){
                            visited[ny][nx][0] = true
                            q.offer(Pos(ny,nx,false))
                        }
                    }
                }
            }
        }
        cnt++
    }
    return -1
}
data class Pos(val y: Int, val x: Int, val isBreak: Boolean)
