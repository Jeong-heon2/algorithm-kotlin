package binarysearch

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt() // 나무의 수
    val M = st.nextToken().toInt() // 집으로 가져가려고 하느 나무의 길이
    val arr = Array(N){0}
    st = StringTokenizer(readLine())
    for(i in 0 until N){
        arr[i] = st.nextToken().toInt()
    }
    println(binarySearch(arr, M))

}
fun binarySearch(arr: Array<Int>,target: Int):Int{
    var left = 0
    var right = arr.maxOf { it }
    var ans = 0
    while(left <= right){
        val mid = (left + right) / 2 // 톱날 높이
        val res = cutTree(arr, mid)
        if(res >= target){
            ans = mid
            left = mid + 1
        }else{
            right = mid - 1
        }
    }
    return ans
}
fun cutTree(arr: Array<Int>, mid: Int): Long{
    var res = 0L
    arr.forEach {
        if(it > mid) res += it - mid
    }
    return res
}

