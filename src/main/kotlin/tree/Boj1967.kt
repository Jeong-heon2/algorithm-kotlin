package tree

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var ans = 0

    data class Edge (
        val end: Int,
        val weight: Int,
    )
    fun findMaxLength(arr: Array<MutableList<Edge>>, curNode: Int, visited: BooleanArray): Int {
        if (arr[curNode].isEmpty()) return 0
        visited[curNode] = true
        var max1 = 0
        var max2 = 0
        arr[curNode].forEach { edge ->
            if (!visited[edge.end]) {
                val res = findMaxLength(arr, edge.end, visited) + edge.weight
                if (max1 < res) {
                    max2 = max1
                    max1 = res
                } else if (max2 < res) {
                    max2 = res
                }
            }
        }

        ans = ans.coerceAtLeast(max1 + max2)
        return max1
    }

    val n = readLine().toInt()
    val arr = Array(n + 1) { mutableListOf<Edge>() }

    repeat(n) {
        val st = StringTokenizer(readLine())
        val node = st.nextToken().toInt()
        var endNode = st.nextToken().toInt()
        while (endNode != -1) {
            val weight = st.nextToken().toInt()
            arr[node].add(Edge(endNode, weight))
            endNode = st.nextToken().toInt()
        }
    }

    val visited = BooleanArray(n + 1)
    findMaxLength(arr, 1, visited)
    println(ans)
}