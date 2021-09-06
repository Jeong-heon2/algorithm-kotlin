package pg_kakao_intern_2021
import java.util.*
fun main() {
    val s = Q3.Solution()
    val n = 8
    val k = 2
    //val cmd = arrayOf("D 2","C","U 3","C","D 4","C","U 2","Z","Z")
    val cmd = arrayOf("C", "C", "C", "C", "C", "C", "C")
    val node = Q3.Solution.Node(1)
    val node2 = Q3.Solution.Node(2)
    var root = node
    var cur = root
    root = node2
    println(root.num)
    println(cur.num)
    println(s.solution(n, k, cmd))
}
class Q3 {
    class Solution {
        fun solution(n: Int, k: Int, cmd: Array<String>): String {
            val linkedList = DoublyLinkedList(n, k)
            cmd.forEach {
                val tokens = it.split(" ")
                if (tokens[0] == "U") {
                    linkedList.moveUp(tokens[1].toInt())
                } else if (tokens[0] == "D") {
                    linkedList.moveDown(tokens[1].toInt())
                } else if (tokens[0] == "C") {
                    linkedList.remove()
                } else {
                    linkedList.restore()
                }
            }
            return linkedList.getResult()
        }

        class DoublyLinkedList(n: Int, k: Int) {
            var cur: Node
            var nodes = mutableListOf<Node>()
            private val removedStack = Stack<Node>()

            init {
                nodes.add(Node(0))
                for (i in 1 until n) {
                    nodes.add(Node(i))
                    nodes[i-1].back = nodes[i]
                    nodes[i].front = nodes[i-1]
                }
                cur = nodes[k]
            }

            fun remove() {
                cur.removed = true
                removedStack.push(cur)
                val front = cur.front
                val back = cur.back
                if (front == null) {
                    back!!.front = null
                    cur = back
                } else if (back == null){
                    front.back = null
                    cur = front
                } else {
                    front.back = back
                    back.front = front
                    cur = back
                }
            }

            fun restore() {
                val restoredNode = removedStack.pop()
                restoredNode.removed = false
                if (restoredNode.front == null) {
                    restoredNode.back!!.front = restoredNode
                } else if (restoredNode.back == null){
                    restoredNode.front!!.back = restoredNode
                } else {
                    restoredNode.front!!.back = restoredNode
                    restoredNode.back!!.front = restoredNode
                }
            }

            fun moveUp(x: Int) {
                repeat(x) {
                    cur = cur.front!!
                }
            }

            fun moveDown(x: Int) {
                repeat(x) {
                    cur = cur.back!!
                }
            }

            fun getResult(): String {
                val sb = StringBuilder()
                nodes.forEach {
                    if(it.removed) sb.append("X")
                    else sb.append("O")
                }
                return sb.toString()
            }
        }
        class Node(var num: Int) {
            var front: Node? = null
            var back: Node? = null
            var removed = false
        }
    }

}