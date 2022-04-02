package kotlin_test
fun main() {
    val a = ActualElement()
    a.a = 2
    val b : Element = a
    println(a.a())
}
open class Element {
    open val a: Int = 1
}
class ActualElement : Element() {
    override var a = 2
    fun a() {
        println(super.a)
    }
}

