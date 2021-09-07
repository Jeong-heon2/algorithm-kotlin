package pg_kakao_intern_2021

fun main() {

}

class Q4 {
    class Solution {
        var ans: Int = Int.MAX_VALUE
        lateinit var visited: Array<Array<Boolean>>
        lateinit var nodes: Array<Node>
        var target: Int = 0
        fun solution(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {
            nodes = Array(n+1){Node(it)}
            visited = Array(n+1){ Array(n+1){false} }
            target = end

            traps.forEach {
                nodes[it].isTrap = true
            }

            roads.forEach {
                val edge = Edge(it[0], it[1], it[2])
                nodes[edge.from].edges.add(edge)
                nodes[edge.to].edges.add(edge)
            }

            dfs(nodes[start], 0)
            return ans
        }

        fun dfs(node: Node, cnt: Int) {
            if(node.n == target) {
                ans = ans.coerceAtMost(cnt)
                return
            }

            if (node.isTrap) changeEdges(node)

            node.edges.forEach {
                if (it.from == node.n) {
                    if (!visited[it.from][it.to]) {
                        visited[it.from][it.to] = true
                        dfs(nodes[it.to], cnt + it.w)
                        visited[it.from][it.to] = false
                    }
                }
            }

            if (node.isTrap) changeEdges(node)
        }

        fun changeEdges(node: Node) {
            node.edges.forEach {
                val from = it.from
                it.from = it.to
                it.to = from
            }
        }

        class Node(val n: Int) {
            val edges = mutableListOf<Edge>()
            var isTrap = false

        }

        data class Edge(var from: Int, var to: Int, val w: Int)
    }
}