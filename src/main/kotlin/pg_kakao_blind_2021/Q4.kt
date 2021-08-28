package pg_kakao_blind_2021

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/72413

fun main(){

}

/**
 * 효율성 통과 못함
 * https://programmers.co.kr/questions/15987
 * 이 문제에서 최악의 다익스트라는 O( N * ElogN ), 플로이드는 O( N^3 )의 시간이 소요됩니다
 * 다익스트라는 구현에 따라 최대 O( N * ElogN )이 걸릴 수도 있고, 최소 O( 3 * ElogN )의 시간이 걸릴 수도 있음. 시간 초과 발생 가능성이 있는 것은 전자의 경우이므로 전자를 고려
 * 아래의 solution은 전자의 경우. 모든 노드 N에서 다익스트라를 다 돌림.
 * 그런데 과연 모든 노드 N에 대해서 다익스트라를 돌릴 필요가 있을까??
 * 문제의 조건에 따라, 노드 i -> 노드 j 의 최소거리는  노드 j -> 노드 i 의 최소거리와 같다 !!
 * 따라서 우리는 i -> a , i -> b 를 구하기위해 모든 노드에 대해서 다익스트라를 할 필요 없이,  a, b만 돌리면 된다.
 * 따라서, s -> i 를 구하기위해 다익스트라 한 번,  i -> a,  i -> b 를 구하기 위해 두 번, 총 3번의 다익스트라만 돌리면 된다.
 * 따라서 최적 다익스트라 시간 복잡도는 O( 3 * ElogN ) 이 된다.
 *
 *
 * 겉보기에는 log를 가진 다익스트라가 최악의 경우에도 N3 인 플로이드보다 효율적으로 동작할 것처럼 보입니다
 * 하지만 간선 E(fairs) 는 문제에서 최대 n x (n-1) / 2 의 크기를 가집니다
 *
 * N은 최대 200의 크기를 가지므로 최악의 경우 다음과 같은 시간이 소요됩니다
 * 플로이드: O( N^3 ) = 8 * 10^6
 * 최악의 다익스트라: O( N * ElogN ) = 3 * 10^7
 * 최적의 다익스트라: O( 3 * ElogN ) = 4.5 * 10^5
 * 사용하는 언어에 따라서 최악의 다익스트라를 사용해도 통과하는 경우가 있겠지만
 * 다익스트라로 풀 때 효율성에서 시간 초과가 발생하는 경우 최선의 경우를 떠올려봐야 한다.
 *
 */
class Q4_Dijkstra {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val graph = Array<MutableList<Edge>>(n+1){ mutableListOf()}
        for(arr in fares){
            graph[arr[0]].add(Edge(arr[1], arr[2]))
            graph[arr[1]].add(Edge(arr[0], arr[2]))
        }
        //distArr[i] = i를 시작위치로 하는  dijkstra 결과 배열
        //distArr[i][j] = i ~ j 최소 거리
        val distArr = Array(n+1){IntArray(n+1)}
        dijkstra(distArr[s], n, s, graph)
        dijkstra(distArr[a], n, a, graph)
        dijkstra(distArr[b], n, b, graph)
        var min = Int.MAX_VALUE
        for(i in 1..n){
            min = min.coerceAtMost(distArr[s][i] + distArr[a][i] + distArr[b][i])
        }
        return min
    }
    fun dijkstra(dist: IntArray, n : Int, s : Int, graph: Array<MutableList<Edge>>){
        for(i in 1..n){
            dist[i] = Int.MAX_VALUE
        }
        dist[s] = 0
        val visited = BooleanArray(n+1)
        val pq = PriorityQueue<Edge>()
        pq.offer(Edge(s, 0))
        while (pq.isNotEmpty()){
            val cur = pq.poll()
            if(visited[cur.end]) continue
            visited[cur.end] = true
            for(next in graph[cur.end]){
                dist[next.end] = dist[next.end].coerceAtMost(next.w + dist[cur.end])
                pq.offer(Edge(next.end, dist[next.end]))
            }
        }

    }
    class Edge(val end: Int, val w: Int) : Comparable<Edge>{
        override fun compareTo(e: Edge): Int{
            return this.w - e.w
        }
    }
}
//플로이드 와샬 알고리즘  n^3 + n  시간 복잡도 -> 효율성 통과 못함
class Q4_Floyd_Warshall {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val graph = Array(n+1){Array(n+1){Int.MAX_VALUE}}
        fares.forEach{
            graph[it[0]][it[1]] = graph[it[0]][it[1]].coerceAtMost(it[2])
            graph[it[1]][it[0]] = graph[it[1]][it[0]].coerceAtMost(it[2])
        }
        for(i in 1..n) graph[i][i] = 0
        floydWarShall(n, graph)
        var min = Int.MAX_VALUE
        for(i in 1..n){
            //i 까지 합승하고 i에서 각 목적지는 혼자 택시
            // i까지 동승했을때 택시 요금 + i~a 요금 + i~b 요금
            min = min.coerceAtMost(graph[s][i] + graph[i][a] + graph[i][b])
        }
        return min
    }
    private fun floydWarShall(n: Int, arr: Array<Array<Int>>){
        for(k in 1..n){
            for(i in 1..n){
                if(arr[i][k] == Int.MAX_VALUE) continue
                for(j in 1..n){
                    if(arr[k][j] == Int.MAX_VALUE) continue
                    arr[i][j] = arr[i][j].coerceAtMost(arr[i][k] + arr[k][j])
                }
            }
        }
    }
}