package dijkstra

import java.util.*

class PG_배달 {
    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        //dist 배열
        val dist = IntArray(N + 1) { Int.MAX_VALUE }
        //graph
        val graph = Array<MutableList<Edge>>(N + 1) { mutableListOf() }
        road.forEach {
            graph[it[0]].add(Edge(it[1], it[2]))
            graph[it[1]].add(Edge(it[0], it[2]))
        }
        //dijkstra
        dijkstra(graph, dist, N)
        return dist.count { it <= k }
    }

    private fun dijkstra(graph: Array<MutableList<Edge>>, dist: IntArray, n: Int) {
        val pq = PriorityQueue<Edge>()
        val visited = BooleanArray(n + 1)
        dist[1] = 0
        pq.offer(Edge(1, 0))
        while(pq.isNotEmpty()) {
            val curEdge = pq.poll()
            if (visited[curEdge.e]) continue
            visited[curEdge.e] = true

            for (next in graph[curEdge.e]) {
                if (!visited[next.e]) {
                    dist[next.e] = dist[next.e].coerceAtMost(dist[curEdge.e] + next.w)
                    pq.offer(Edge(next.e, dist[next.e]))
                }
            }
        }
    }

    class Edge(
        val e: Int,
        val w: Int
    ) : Comparable<Edge> {

        override fun compareTo(other: Edge): Int {
            return this.w - other.w
        }
    }
}