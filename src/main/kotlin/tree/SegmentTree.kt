package tree

fun main() {
    //입력 데이터
    val a = intArrayOf(75, 30, 100, 38, 50, 20)
    //세그먼트 트리
    val n = a.size // 데이터 개수
    /*
    필요한 노드 개수 = 정확히는 2n - 1
    이진 트리에서 리프 노드가 n 개일때  총 2n - 1 개의 노드가 필요함

    트리의 높이 = log2 필요한 노드개수 + 1
             = log2 2*n + 1

    필요한 배열 크기 = 2^트리의 높이
                 = 2^(log2 2*n + 1)
                 = 2^(log2 2 + log2 n + 1 )
                 = 2^(2 + log2 n)
                 = 2^2 * 2^log2 n
                 = 4 * 2^log2 n
                 = 4 * n  ( x^logx y = y 이므로 )
     */
    val tree = IntArray(4 * n)

    // n : tree Index
    fun init(n: Int, start: Int, end: Int) {
        //구간의 크기가 1 인경우
        if (start == end) tree[n] = a[start]
        else {
            //왼쪽 자식 : n*2
            init(n*2, start, (start + end)/2)
            //오른쪽 자식 : n*2 + 1
            init(n*2 + 1, (start + end)/2 + 1, end)
            //왼쪽과 오른쪽의 최소 값
            tree[n] = tree[n * 2].coerceAtMost(tree[n * 2 + 1])
        }
    }

    // i ~ j 구간의 최소 값을 찾고 싶다.
    fun query(n: Int, i: Int, j: Int, start: Int, end: Int): Int {
        //구간이 겹치지 않는 경우
        if (i > end || j < start) return -1
        //구간을 포함하는 경우
        if (i <= start && j >= end) return tree[n]
        val resLeft = query(n*2, i, j, start, (start + end)/2)
        val resRight = query(n*2 + 1, i, j, (start + end)/2 + 1, end)
        return if (resLeft == -1) resRight
        else if (resRight == -1) resLeft
        else resLeft.coerceAtMost(resRight)
    }

    init(1, 0, a.lastIndex)
    println(query(1, 1, 3, 0, a.lastIndex))
    println(query(1, 2, 4, 0, a.lastIndex))
    println(query(1, 1, 5, 0, a.lastIndex))
    println(query(1, 0, 5, 0, a.lastIndex))
    println(query(1, 0, 0, 0, a.lastIndex))
    println(query(1, 5, 5, 0, a.lastIndex))
}
