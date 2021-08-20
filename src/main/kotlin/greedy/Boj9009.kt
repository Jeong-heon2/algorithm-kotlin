package greedy

import java.io.BufferedReader
import java.io.InputStreamReader

/*
 1 ≤ n ≤ 1,000,000,000.  이므로
 1,000,000,000 까지 피보나치를 구한다.

 */

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val fiboMemo = mutableListOf<Int>()
    setFibo(fiboMemo)
    var T = readLine().toInt()
    while (T-- > 0){
        val N = readLine().toInt()
        var sum = 0
        var list = mutableListOf<Int>()
        for(i in fiboMemo.lastIndex downTo 0){
            if(sum + fiboMemo[i] <= N) {
                sum += fiboMemo[i]
                list.add(fiboMemo[i])
            }
            if(sum == N) break
        }
        println(list.reversed().map { it.toString() }.reduce { acc, s -> "$acc $s" })
    }
}
fun setFibo(fiboMemo: MutableList<Int>){
    fiboMemo.add(0)
    fiboMemo.add(1)
    var i = 2
    while(true){
        fiboMemo.add(fiboMemo[i-1] + fiboMemo[i-2])
        if(fiboMemo[i] > 1000000000) break
        i++
    }
}
