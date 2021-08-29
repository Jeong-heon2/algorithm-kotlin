package pg_kakao_blind_2021
import java.util.*;

class Q6 {
    class Solution {
        private lateinit var orders : MutableList<IntArray>
        private val SIZE = 4
        private val dx = arrayOf(1, -1, 0, 0)
        private val dy = arrayOf(0, 0, 1, -1)
        private lateinit var map : MutableMap<Int, MutableList<Pos>>
        private var ans = 0
        fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
            orders = mutableListOf()
            //순열을 돌릴 카드 번호 리스트
            val arr = mutableListOf<Int>()
            map = mutableMapOf()
            ans = Int.MAX_VALUE
            /*
            map에  <카드번호,  위치 리스트> 형태로 저장
            */
            for (i in 0 until SIZE){
                for (j in 0 until SIZE){
                    if(board[i][j] != 0){
                        if(!map.containsKey(board[i][j])) {
                            map[board[i][j]] = mutableListOf(Pos(i, j))
                            arr.add(board[i][j])
                        }
                        else map[board[i][j]]!!.add(Pos(i, j))
                    }
                }
            }
            // 순열을 돌리는 이유는 순서대로 카드를 찾아서 없애기 위해
            permutation(arr, 0, arr.size)

            val cur = Pos(r, c)
            orders.forEach{
                search(board, it, 0, cur, 0)
            }

            return ans
        }
        /*
        order의 카드 순서대로  카드를 재귀적으로 찾는다 .  만약 order = {1, 2} 였다면
        1의 카드 쌍을 먼저 찾고 그 다음 2의 카드 쌍을 찾는다.
        여기서 중요한 점이 카드 번호당 카드가 2개이기 때문에  2개에서도 순서를 정해서 찾아 줘야 한다.
        ex. 1-a, 1-b  카드를 찾는 다면,  1-a 먼저 찾고 1-b 찾는 경우,  1-b 먼저 찾고 1-a 찾는 경우  이 두가지 모두 고려

        */
        private fun search(board: Array<IntArray>, order: IntArray, i: Int, cur: Pos, cnt: Int){
            if(i == order.size) {
                ans = ans.coerceAtMost(cnt)
                return
            }
            val list = map[order[i]]!!
            var target = list[0]
            var res = bfs(board, target, cur)
            board[target.i][target.j] = 0

            var target2 = list[1]
            res += bfs(board, target2, target)
            board[target2.i][target2.j] = 0
            search(board, order, i + 1, target2, cnt + res)

            board[target.i][target.j] = order[i]
            board[target2.i][target2.j] = order[i]

            //이번엔 순서를 바꿔서  list[1] 먼저 찾는다
            target = list[1]
            res = bfs(board, target, cur)
            board[target.i][target.j] = 0

            target2 = list[0]
            res += bfs(board, target2, target)
            board[target2.i][target2.j] = 0
            search(board, order, i + 1, target2, cnt + res)

            board[target.i][target.j] = order[i]
            board[target2.i][target2.j] = order[i]
        }
        /*
        start 위치에서 target 까지 가는 최소 키 조작 횟수를 리턴한다.
        bfs로 찾는다.
        */
        private fun bfs(board: Array<IntArray>, target: Pos, start: Pos): Int{
            if(target == start) return 1 // enter

            val visited = Array(SIZE){BooleanArray(SIZE)}
            val q = LinkedList<Pos>()
            q.offer(start)
            visited[start.i][start.j] = true
            var cnt = 0
            while(q.isNotEmpty()){
                var qSize = q.size
                while(qSize-- > 0){
                    val cur = q.poll()
                    for (i in 0..3){
                        //가장 가까운 카드 or 벽이 나올때 까지
                        var ny = cur.i + dy[i]
                        var nx = cur.j + dx[i]
                        //그냥 한 칸 이동
                        if(isRanged(ny,nx) && !visited[ny][nx]){
                            if(ny == target.i && nx == target.j) return cnt + 2 //enter 까지
                            visited[ny][nx] = true
                            q.offer(Pos(ny, nx))
                        }
                        //ctrl + 방향키
                        while(isRanged(ny, nx) && board[ny][nx] == 0){
                            ny += dy[i]
                            nx += dx[i]
                        }
                        //벽에 부딪힌 경우
                        if(!isRanged(ny, nx)) {
                            ny -= dy[i]
                            nx -= dx[i]
                            if(!visited[ny][nx]){
                                visited[ny][nx] = true
                                q.offer(Pos(ny, nx))
                            }
                        } else {
                            //다른 카드를 만남
                            if(!visited[ny][nx]){
                                if(ny == target.i && nx == target.j) return cnt + 2 //enter 까지
                                visited[ny][nx] = true
                                q.offer(Pos(ny, nx))
                            }
                        }
                    }
                }
                cnt++
            }
            return cnt
        }
        private fun isRanged(i: Int, j: Int): Boolean{
            if(i < 0 || i >= SIZE || j < 0 || j >= SIZE) return false
            return true
        }
        private fun permutation(arr: MutableList<Int>, depth: Int, n: Int){
            if(depth == n) {
                orders.add(arr.toIntArray())
            } else {
                for (i in depth until n){
                    swap(arr, i, depth)
                    permutation(arr, depth + 1, n)
                    swap(arr, depth, i)
                }
            }
        }
        private fun swap(arr: MutableList<Int>, i: Int, j: Int){
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
        data class Pos(val i: Int, val j: Int)
    }
}