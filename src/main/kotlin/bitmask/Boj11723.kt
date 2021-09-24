package bitmask

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

@OptIn(ExperimentalStdlibApi::class)
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val M = readLine().toInt()
    var bits = 0
    repeat(M) {
        val tokens = readLine().split(" ")
        val opc = tokens[0]
        val opr = if (opc == "all" || opc == "empty" ) 0 else tokens[1].toInt()
        val one = 1
        when(opc) {
            "add" -> {
                bits = bits.or(one.rotateLeft(opr))
            }
            "check" -> {
                if (bits.and(one.rotateLeft(opr)) != 0) bw.write("1\n") else bw.write("0\n")
            }
            "remove" -> {
                bits = bits.and(one.rotateLeft(opr).inv())
            }
            "toggle" -> {
                bits = bits.xor(one.rotateLeft(opr))
            }
            "empty" -> {
                bits = 0
            }
            "all" -> {
                bits = one.rotateLeft(21) - 1
            }
        }
    }
    close()
    bw.close()
}