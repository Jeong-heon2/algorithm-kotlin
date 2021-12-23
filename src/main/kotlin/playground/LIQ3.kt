package playground

import java.io.*

//O(N)
fun main(vararg args: String) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val list = br.readLine().split(" ").map { it.toInt() }
    list.sorted()
    val target = br.readLine().toInt()

    var i = 0
    var j = list.lastIndex
    while(i < j) {
        val sum = list[i] + list[j]
        if (sum == target) {
            println("${list[i]} ${list[j]}")
            return
        }
        if (sum > target) j--
        else i++
    }
    println(-1)
}