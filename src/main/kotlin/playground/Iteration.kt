package playground

fun main() {
    val s = "101010111"
    val res = s.groupBy { if(it == '0') 0 else 1 }.mapValues { it.value.size }
    res[0]
    var a = 0
    var b = 0
    val (c, d) = func()
}

fun func(): List<Int> {
    return listOf(1, 2)
}