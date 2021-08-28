package floyd_warshall

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

//boj 11404
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt() //도시의 개수
    val m = readLine().toInt() //버스의 개수
    val graph = Array(n+1){Array(n+1){Int.MAX_VALUE} }

    for(i in 0 until  m){
        val st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        graph[a][b] = graph[a][b].coerceAtMost(c)
    }

    for(i in 1 .. n){
        graph[i][i] = 0
    }

    close()

    /**
     * k : 중간에 거쳐가야할 버스
     * i : 출발 버스
     * j : 목적지 버스
     */
    for(k in 1..n){
        for(i in 1..n){
            if(graph[i][k] == Int.MAX_VALUE) continue
            for(j in 1..n){
                if(graph[k][j] == Int.MAX_VALUE) continue
                graph[i][j] = graph[i][j].coerceAtMost(graph[i][k] + graph[k][j])
            }
        }
    }

    val bw = BufferedWriter(OutputStreamWriter(System.out))
    for(i in 1..n){
        for(j in 1..n){
            if(graph[i][j] == Int.MAX_VALUE) bw.write("0 ")
            else bw.write("${graph[i][j]} ")
        }
        bw.newLine()
    }
    bw.flush()
    bw.close()

}
