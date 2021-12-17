package heap


fun main() {
    val pq = PriorityQ<Int>()
    pq.offer(1)
    pq.offer(2)
    pq.offer(0)
}
class PriorityQ<T : Comparable<T>> {
    private val items = mutableListOf<T>()
    val size: Int
        get() = items.size

    fun offer(item: T) {
        items.add(item)
        var childIdx = items.lastIndex
        var parentIdx = getParentIdx(childIdx)
        while (parentIdx >= 0) {
            if (items[parentIdx].compareTo(items[childIdx]) == 1) {
                swap(parentIdx, childIdx)
                childIdx = parentIdx
                parentIdx = getParentIdx(childIdx)
            } else {
                break
            }
        }
    }

    /*
    todo : poll 구현
     */

    private fun getParentIdx(childIdx: Int): Int {
        return if (childIdx % 2 == 0) (childIdx - 2) / 2
        else childIdx / 2
    }

    private fun swap(i: Int, j: Int) {
        val tmp = items[i]
        items[i] = items[j]
        items[j] = tmp
    }
}