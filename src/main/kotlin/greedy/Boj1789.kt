package greedy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine()
    val S = input.toLong()
    var sum = 0L
    /*
    N이 최대가 되려면 최대한 작은 자연수들로 S를 구성해야 한다. 1부터 더해나간다.
     */
    for(i in 1..S){
        sum += i
        //sum이 S 보다 커졌을때,  sum과 S의 차이는 1~i-1 중에 하나다.
        //따라서 그 차이를 빼면 S와 같아지므로.  N은 i-1이 된다.
        if(sum > S){
            println(i-1)
            break
        }
    }
}