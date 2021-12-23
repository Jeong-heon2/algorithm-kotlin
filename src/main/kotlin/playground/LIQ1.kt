package playground

import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    var maxLen = 0
    val arr = Array(n){""}
    repeat(n) {
        val str = readLine()!!
        maxLen = maxLen.coerceAtLeast(str.length)
        arr[it] = str
    }
    drawFrame(maxLen, arr)
}

// O(maxLen * N)
fun drawFrame(maxLen: Int, strArr: Array<String>) {
    val frameWidth = maxLen + 4
    val frameHeight = strArr.size + 4
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    for (y in 0 until frameHeight) {
        for (x in 0 until frameWidth) {
            if (y == 0 || y == frameHeight - 1 || x == 0 || x == frameWidth - 1) {
                bw.write("#")
                continue
            }
            if (y in 2..(frameHeight-2) && x in 2 until 2 + strArr[y-2].length) {
                bw.write(strArr[y-2][x-2].toString())
            } else {
                bw.write(" ")
            }
        }
        bw.write("\n")
        bw.flush()
    }
    bw.close()
}