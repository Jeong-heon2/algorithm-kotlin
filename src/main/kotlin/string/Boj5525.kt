package string

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val s = readLine()
    val p = makeIOIOI(n)
    val pi = failfunc(p)

    var i = 0 //s의 idx
    var j = 0 //p의 idx
    var cnt = 0
    while(i < s.length){
        if(s[i] != p[j]){
            if(j != 0){
                j = pi[j-1]
            }else{
                i++
                j = 0
            }
        }else{
            if(j == p.lastIndex){
                cnt++
                i++
                j--
            }else{
                i++
                j++
            }
        }
    }
    println(cnt)
}
fun failfunc(p : String):Array<Int>{
    val len = p.length
    val pi = Array(len){0}
    pi[0] = 0
    var j = 0
    for(i in 1 until len){
        while (j > 0 && p[i] != p[j]){
            j = pi[j-1]
        }
        if(p[i] == p[j]){
            pi[i] = j++ + 1
        }else{
            pi[i] = 0
        }
    }
    return pi
}
fun makeIOIOI(n: Int): String{
    val sb = StringBuilder()
    sb.append("I")
    repeat(n){
        sb.append("OI")
    }
    return sb.toString()
}
/* 백준에서 가장 빠른 해답
val br = System.`in`.bufferedReader()

fun main(): Unit = with(br) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val s = readLine()

    var ans = 0
    var i = 0
    var j = 0

    while (i < m) {
        if (s[i] == if (j and 1 == 1) 'O' else 'I') {
            if (j == n * 2) {
                ans += 1
                j -= 2
            }

            i += 1
            j += 1
        } else {
            if (j != 0) {
                j = 0
            } else {
                i += 1
            }
        }
    }


    println(ans)

    return
}
*/