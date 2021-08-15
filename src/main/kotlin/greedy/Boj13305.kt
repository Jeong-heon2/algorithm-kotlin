package greedy

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.min
/*
현재 위치의 도시 i 에서 ,  i 도시의 기름가격보다 더 싼 j 까지 가는데 필요한 기름을 현재 도시에서 주유한다.

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    val distances = Array(N-1){0}
    var st = StringTokenizer(readLine())
    for(i in 0 until N-1){
        distances[i] = st.nextToken().toInt()
    }
    val oilPrices = Array(N){0}
    st = StringTokenizer(readLine())
    for(i in 0 until N){
        oilPrices[i] = st.nextToken().toInt()
    }


    var distanceSum = 0L
    var priceSum = 0L
    var curPrice = oilPrices[0] //현재 위치의 기름가격
    for(i in 1 until N){
        distanceSum += distances[i-1]
        if(oilPrices[i] < curPrice){
            priceSum += distanceSum*curPrice
            curPrice = oilPrices[i]
            distanceSum = 0
        }
    }
    if(distanceSum != 0L){
        priceSum += distanceSum*curPrice
    }
    println(priceSum)

}*/
//다른 사람의 풀이  (백준에서 가장 빠른풀이 )
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val L = IntArray(N)
    var st = StringTokenizer(readLine())
    var min = Int.MAX_VALUE
    var R = 0L
    for (i in 0 until N - 1)
        L[i] = st.nextToken().toInt()
    st = StringTokenizer(readLine())
    for (i in 0 until N - 1) {
        min = min(min, st.nextToken().toInt())
        R += min.toLong() * L[i]
    }
    println(R)
}