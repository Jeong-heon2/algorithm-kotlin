package graph

class PG_네트워크 {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0
        val visited = BooleanArray(n)
        repeat(n) {
            if (!visited[it]) {
                answer++
                dfs(it, computers, visited)
            }
        }
        return answer
    }

    private fun dfs(com: Int, computers: Array<IntArray>, visited: BooleanArray) {
        visited[com] = true
        computers[com].forEachIndexed { otherCom, connected ->
            if (!visited[otherCom] && connected == 1) {
                dfs(otherCom, computers, visited)
            }
        }
    }
}