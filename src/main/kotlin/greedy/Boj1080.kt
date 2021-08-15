package greedy

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

var N = 0
var M = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var st = StringTokenizer(readLine())
    N  = st.nextToken().toInt()
    M  = st.nextToken().toInt()
    var A = Array(N){Array(M){ false } }
    for( i in 0 until N){
        val input = readLine()
        for(j in 0 until M){
            A[i][j] = input[j] == '1'
        }
    }
    var B = Array(N){Array(M){ false } }
    for( i in 0 until N){
        val input = readLine()
        for(j in 0 until M){
            B[i][j] = input[j] == '1'
        }
    }

    var cnt = 0
    for( i in 0 until N){
        for(j in 0 until M){
            if(A[i][j] != B[i][j] && isRanged(i+2, j+2)){
                change(A, i, j)
                cnt++
            }
        }
    }
    if(isEqual(A,B)) println(cnt)
    else println(-1)

}
fun isRanged(y: Int, x: Int): Boolean{
    if(y < 0|| y >= N || x < 0 || x >= M) return false
    return true
}
fun change(A: Array<Array<Boolean>>, y: Int, x: Int){
    for(i in y until y+3){
        for(j in x until  x+3){
            A[i][j] = !A[i][j]
        }
    }
}
fun isEqual(A: Array<Array<Boolean>>, B: Array<Array<Boolean>>) : Boolean{
    for( i in 0 until N){
        for(j in 0 until M){
            if(A[i][j] != B[i][j]) return false
        }
    }
    return true
}