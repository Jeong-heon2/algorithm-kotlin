package greedy

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    val arr = Array(N){false}
    val paper = Array(N){false}
    val st = StringTokenizer(readLine())
    for(i in 0 until N){
        paper[i] = st.nextToken() == "1"
    }
    var cnt = 0
    for(i in 0 until N){
        if(arr[i] != paper[i]){
            cnt++
            arr[i] = !arr[i]
            if(i+1 < N) arr[i+1] = !arr[i+1]
            if(i+2 < N) arr[i+2] = !arr[i+2]
        }
    }
    println(cnt)

}
