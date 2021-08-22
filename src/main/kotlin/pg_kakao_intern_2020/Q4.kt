package pg_kakao_intern_2020

import java.util.*

fun main() {
    class Solution {
        val dx = arrayOf(1,-1,0,0)
        val dy = arrayOf(0,0,1,-1)
        var N = 0
        lateinit var priceBoard : Array<Array<Int>>
        fun solution(board: Array<IntArray>): Int {
            N = board.size
            priceBoard = Array(N){Array(N){Int.MAX_VALUE} }
            bfs(board)
            return priceBoard[N-1][N-1]
        }
        fun bfs(board: Array<IntArray>){
            val q = LinkedList<Pos>()
            q.offer(Pos(-1, 0,0, 0))
            priceBoard[0][0] = 0
            while (q.isNotEmpty()){
                val cur = q.poll()
                if(cur.y == N-1 && cur.x == N-1) continue
                for( i in 0 until 4){
                    val nx = cur.x + dx[i]
                    val ny = cur.y + dy[i]
                    if(isRanged(ny, nx) && board[ny][nx] == 0){
                        //ny,ny 의 비용
                        val cost = when(i == cur.dir){
                            true -> cur.cost + 100
                            false -> {
                                if(cur.dir == -1) cur.cost + 100
                                else cur.cost + 600
                            }
                        }
                        if(priceBoard[ny][nx] >= cost){
                            priceBoard[ny][nx] = cost
                            q.offer(Pos(i, ny, nx, cost))
                        }
                    }
                }
            }
        }
        fun isRanged(y: Int, x: Int):Boolean{
            if(y < 0 || x < 0 || y >= N || x >= N) return false
            return true
        }
        inner class Pos(val dir: Int, val y: Int, val x: Int, val cost: Int)

    }
    val s = Solution()
    val board = arrayOf(
        intArrayOf(0,0,0),
        intArrayOf(0,0,0),
        intArrayOf(0,0,0)
    )
    s.solution(board)

}
