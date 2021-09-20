package kk_2022_blind


class Q5 {
    class Solution {
        var sheeps = 1
        var wolfs = 0
        lateinit var parents: IntArray
        fun solution(info: IntArray, edges: Array<IntArray>): Int {
            val nodes = Array(info.size){Node(it)}
            parents = IntArray(info.size)
            for (i in info.indices) {
                parents[i] = i
            }
            for (i in info.indices) {
                if(info[i] == 0) {
                    nodes[i].isWolf = false
                }
            }

            edges.forEach {
                if (nodes[it[0]].left == null) {
                    nodes[it[0]].left = nodes[it[1]]
                } else {
                    nodes[it[0]].right = nodes[it[1]]
                }
                nodes[it[1]].parent = nodes[it[0]]
            }
            var answer: Int = 0
            return answer
        }
        fun search(cur: Node): Boolean {
            cur.parent?.let { parent ->
                if(find(cur.i) == find(parent.i)) {
                    var flag = true
                    var turn = false // left
                    while (flag) {
                        flag = false
                        if(!turn){
                            cur.left?.let { left ->
                                flag = search(left)
                                if (flag) turn = true
                            }
                        }else {
                            cur.right?.let { right ->
                                flag = search(right)
                                if (flag) turn = false
                            }
                        }
                    }
                } else {
                    union(cur.i, parent.i)
                    if (cur.isWolf) {
                        wolfs++
                        if (wolfs >= sheeps) return false
                    } else {
                        sheeps++
                    }
                    var flag = true
                    var turn = false // left
                    while (flag) {
                        flag = false
                        if(!turn){
                            cur.left?.let { left ->
                                flag = search(left)
                                if (flag) turn = true
                            }
                        }else {
                            cur.right?.let { right ->
                                flag = search(right)
                                if (flag) turn = false
                            }
                        }
                    }
                }
            }



        }
        class Node(
            var i: Int,
            var isWolf: Boolean = true,
            var parent: Node? = null,
            var left: Node? = null,
            var right: Node? = null
        )

        fun find(x: Int): Int {
            if (parents[x] == x) {
                return x
            } else {
                parents[x] = find(parents[x])
                return parents[x]
            }
        }

        fun union(x: Int, y: Int) {
            var i = x
            var j = y
            i = find(i)
            j = find(j)
            if (i != j) {
                if (j > i) {
                    parents[j] = i
                } else {
                    parents[i] = j
                }
            }
        }
    }
}