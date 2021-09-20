package dijkstra

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // e: 간선의 목적지, w: 간선의 가중치
    class Edge(val e: Int, val w: Int) : Comparable<Edge>{
        override fun compareTo(other : Edge): Int{
            return this.w - other.w
        }
    }

    var st = StringTokenizer(readLine())
    val V = st.nextToken().toInt() // 정점의 개수
    val E = st.nextToken().toInt() // 간선의 개수
    val K = readLine().toInt() // 시작 정점

    val dist = IntArray(V+1) // dist[i] = 시작정점 부터 i까지 최소거리
    val visited = BooleanArray(V+1)

    val graph = Array<MutableList<Edge>>(V+1){ mutableListOf()} // graph[i] = 노드 i의 간선들을 저장하는 list

    //모든 거리는 max로 초기화
    for( i in 1 .. V){
        dist[i] = Int.MAX_VALUE
    }

    dist[K] = 0 // 시작 정점과 시작 정점과의 거리는 0

    for( i in 0 until E){
        st = StringTokenizer(readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        graph[u].add(Edge(v, w))
    }

    close()

    val pq = PriorityQueue<Edge>()
    pq.offer(Edge(K, 0))//시작 정점으로 향하는 가중치 0짜리 간선을 우선순위 큐에 넣음.

    while (pq.isNotEmpty()){
        val cur = pq.poll()
        if(visited[cur.e]) continue
        visited[cur.e] = true
        for(next in graph[cur.e]){
            if(!visited[next.e]){
                dist[next.e] = min(dist[next.e], dist[cur.e] + next.w)
                pq.offer(Edge(next.e, dist[next.e]))
            }
        }
    }
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    for(i in 1 .. V){
        if(dist[i] == Int.MAX_VALUE){
            bw.write("INF\n")
        }else{
            bw.write("${dist[i]}\n")
        }
    }
    bw.flush()
    bw.close()

}
