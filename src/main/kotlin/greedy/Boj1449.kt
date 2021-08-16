package greedy

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val L = st.nextToken().toInt()
    st = StringTokenizer(readLine())
    val arr = Array(1001){false}
    for(i in 0 until N){
        arr[st.nextToken().toInt()] = true
    }
    /*
    테이프 길이 1 -> 구멍 1개 커버
    2 - >  연속된 구멍 2개 커버
    3 - > 연속된 구명 3개 커버
     */
    var i = 0
    var cnt = 0
    while(i < 1001){
        if(arr[i]){
            i += L
            cnt++
        }else{
            i++
        }
    }
    println(cnt)
}