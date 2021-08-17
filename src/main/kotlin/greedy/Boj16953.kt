package greedy

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
/*
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val A = st.nextToken().toLong()
    val B = st.nextToken().toLong()
    println(bfs(A,B))
}
fun bfs(A: Long, B: Long): Int{
    val q = LinkedList<Long>()
    q.offer(A)
    var cnt = 0
    var flag = false
    loop@while (q.isNotEmpty()){
        var qSize = q.size
        while (qSize-- > 0){
            val cur = q.poll()
            if(cur == B) {
                flag = true
                break@loop
            }
            val multiple = cur*2
            val addOne = (cur.toString() + '1').toLong()
            if(multiple <= B) q.offer(multiple)
            if(addOne <= B) q.offer(addOne)
        }
        cnt++
    }
    return if(flag) cnt + 1
    else -1
}*/
/*
백준에서 가장 성능 좋은 풀이
 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val a = st.nextToken().toInt()
    var b = st.nextToken().toInt()

    var ans = 0
    while (b > a) {
        b /= when {
            b % 10 == 1 -> 10
            b % 2 == 1 -> {
                print(-1); return
            }
            else -> 2
        }
        ans++
    }
    if (b == a) print(ans + 1) else print(-1)
}