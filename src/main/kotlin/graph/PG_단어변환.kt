package graph

import java.util.*

class PG_단어변환 {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        return bfs(begin, target, words)
    }

    private fun bfs(begin: String, target: String, words: Array<String>): Int {
        val visited = BooleanArray(words.size)
        val q = LinkedList<Int>()
        q.offer(-1)
        var cnt = 0
        while (q.isNotEmpty()) {
            var qSize = q.size
            while (qSize-- > 0) {
                val i = q.poll()
                val cur = if (i == -1) begin else words[i]
                words.forEachIndexed { j, word ->
                    if (!visited[j] && check(cur, word, cur.length)) {
                        if (word == target) return cnt + 1
                        visited[j] = true
                        q.offer(j)
                    }
                }
            }
            cnt++
        }
        return 0
    }

    private fun check(s1: String, s2: String, n: Int): Boolean {
        var cnt = 0
        repeat(n) {
            if (s1[it] != s2[it]) {
                cnt++
                if (cnt > 1) return false
            }
        }
        return true
    }
}