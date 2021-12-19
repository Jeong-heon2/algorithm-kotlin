package brute_froce

import java.util.*
import kotlin.math.abs

var n = 0
lateinit var arr: IntArray // arr[i] = a :  i 열 a 행에 퀸이 있다.
var ans = 0
fun main() {
    val sc = Scanner(System.`in`)
    n = sc.nextInt()
    arr = IntArray(n)
    sc.close()

    dfs(0)
    println(ans)
}

fun dfs(y: Int){
    if (y == n) {
        ans++
        return
    }

    repeat(n) { x ->
        if (check(y, x)) {
            arr[y] = x
            dfs(y + 1)
        }
    }
}

fun check(y: Int, x: Int): Boolean {
    repeat(y) {
        if (arr[it] == x) return false
        if (abs(it - y) == abs(arr[it] - x)) return false
    }
    return true
}
//fun check(y: Int, x: Int): Boolean {
//    list.forEach {
//        if (it[0] == y || it[1] == x
//            || (y - it[0] == x - it[1])
//            || (y - it[0] == -x + it[1]))
//                return false
//    }
//    return true
//}