package playground

fun main() {
    val items: List<Any> = listOf("one", 2, "three")
    items.rf<Any, String> {
        it.toString()
    }
    val tes = Tes()

}
inline fun <reified T, R> Collection<T>.rf(body: (Collection<T>) -> R): R {
    return body.invoke(this)
}

open class Tes {
    val c: Int = 1
    fun somF() {}
    companion object {
        const val d = 1

        fun ho() {}
    }
}

inline fun <reified T> rf3() {

}
inline fun <reified T : Tes,  R> rf2(a : T, b: R) {
    val items: List<Any> = listOf("one", 2, "three")
    //     rf3<R>()
}
