package implementation

fun main() {
    lateinit var arr: IntArray
    fun getSumFromOneTo(n: Int): Int {
        return n * (n + 1) / 2
    }
    // start: 시작 층
    fun triangle(startY: Int, depth: Int, startVal: Int, n: Int) {
        val endY = n - depth
        var value = startVal
        for (y in startY..endY) {
            val x = getSumFromOneTo(y-1) + depth
            arr[x] = value++
        }
        val endLayerStartIdx = getSumFromOneTo(endY - 1)
        for (x in (endLayerStartIdx + 1 + depth)..(endLayerStartIdx + n - 1 - depth)) {
            if (arr[x] == 0) arr[x] = value++
        }
        for (y in (endY-1) downTo (startY + 1)) {
            val x = getSumFromOneTo(y - 1) + y - 1 - depth
            if (arr[x] == 0) arr[x] = value++
        }
        if (value <= getSumFromOneTo(n)) {
            triangle(startY + 2, depth + 1, value, n)
        }
    }
    fun solution(n: Int): IntArray {
        arr = IntArray(getSumFromOneTo(n))
        // i 층의 첫 원소의 인덱스 : getSumFromOneTo(i-1)
        triangle(1, 0, 1, n)
        return arr
    }

    solution(4)
}