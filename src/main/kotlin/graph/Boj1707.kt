package graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/*
인접한 정점끼리 서로 다른 색으로 칠해서 모든 정점을 두 가지 색으로만 칠할 수 있는 그래프.
즉, 그래프의 모든 정점이 두 그룹으로 나눠지고
서로 다른 그룹의 정점이 간선으로 연결되어져 있는
(<=> 같은 그룹에 속한 정점끼리는 서로 인접하지 않도록 하는)
그래프를 이분 그래프라고 한다.
 */
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var T = readLine().toInt()
    loop@while(T-- > 0){
       var st = StringTokenizer(readLine())
       val V = st.nextToken().toInt()
       val E = st.nextToken().toInt()
       val arr = Array(V+1){Node(null, mutableListOf())}
       for(i in 0 until E){
           st = StringTokenizer(readLine())
           val a = st.nextToken().toInt()
           val b = st.nextToken().toInt()
           arr[a].friends.add(b)
           arr[b].friends.add(a)
       }
        for(i in 1..arr.lastIndex){
            if(arr[i].color == null){
                if(!bfs(arr,i)) {
                    println("NO")
                    continue@loop
                }
            }
        }
        println("YES")

    }

}
fun bfs(arr:Array<Node>, start: Int):Boolean{
    val q = LinkedList<Int>()
    q.offer(start)
    arr[start].color = true
    while (q.isNotEmpty()){
        val cur = q.poll()
        val curColor = arr[cur].color!!
        val nextColor = !curColor
        for(next in arr[cur].friends){
            if(arr[next].color == curColor) return false
            if(arr[next].color == null){
                arr[next].color = nextColor
                q.offer(next)
            }
        }
    }
    return true
}
data class Node(var color: Boolean?, val friends: MutableList<Int>)

/*
***********************테스트 케이스마다 초기화를 잘 하세요.*********************** 간선 정보, 정답 여부, 큐 등등 매 케이스마다 새롭게 시작해야 하는 건 반드시 그 루프의 시작에서 초기화합시다. 테스트 해볼 때도 1개씩만 하지 말고, 다수의 케이스를 연속으로 넣어서 항상 잘 나오는지 확인하세요. 그리고 매 케이스마다 개행 문자를 반드시 출력하세요. 그리고 Yes, yes 가 아니라 YES입니다.
1번 정점에서만 탐색을 해서는 안 됩니다. 1번 정점과 연결되지 않은 다른 정점들 사이에서 이분 그래프를 못 만들 수 있습니다. 1번 뿐만 아니라, 어느 정점이라도 마찬가지입니다. 주어지는 그래프는 연결 그래프가 아닐 수 있습니다.
간선 정보 순서대로, 또는 임의의 순서로 정렬해놓고 하나씩 보면서 집합을 임의로 정해주는 것도 안 됩니다. 지금 당장 집합을 정한 것 때문에 나중에 이분 그래프를 못 만들게 될 수도 있습니다. 그래프 정보가 완전히 들어오기 전까지는 섣부른 판단은 금물입니다.
한 정점에서 탐색을 시작했더니 답이 NO로 판명났으면, 그 답을 다시 YES로 바꿀 일은 절대로 없습니다. 예를 들어 1번 정점에서 탐색했더니 이분 그래프를 못 만들었다면, 4번 정점에서 탐색했더니 만들 수 있었다고 해도 답이 YES가 되지는 않습니다
 */