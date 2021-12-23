package playground

import java.io.*

fun main(vararg args: String) {
    val (N, C) = readLine()!!.split(" ").map{ it.toInt() }
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val q = Q(C)
    repeat(N) {
        val tokens = readLine()!!.split(" ")
        when(tokens[0]) {
            "OFFER" -> {
                bw.write("${q.offer(tokens[1])}\n")
            }
            "TAKE" -> {
                val data = q.take()
                if (data != null) bw.write("$data\n")
            }
            "SIZE" -> {
                bw.write("${q.size()}\n")
            }
        }
    }
}
class Q(val maxSize: Int) {
    private var size = 0

    private var header: Node? = null
    private var tail: Node? = null

    fun offer(data: String): Boolean {
        if (size == maxSize) return false
        val node = Node(data)
        if (header == null) {
            header = node
            tail = node
        } else {
            node.front = tail
            tail?.back = node
            tail = node
        }
        size++
        return true
    }

    fun take(): String? {
        return if (size == 0) null
        else {
            val ret = header?.data
            header = header?.back
            header?.front?.back = null
            header?.front = null
            size--
            ret
        }
    }

    fun size() = this.size
}

class Node(
    val data: String,
    var front: Node? = null,
    var back: Node? = null
)