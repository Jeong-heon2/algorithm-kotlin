package playground

fun main() {
    inlineHello("김영희") {
        println(it)
    }
}

private inline fun <T> inlineHello(name: T, crossinline func: (String) -> Unit) {
    /**
     * 다른 고차함수에서 func를 호출시엔 crossinline 을 표시해주어야 함.
     */
    invokeFunc() {
        func("$name 반가워~^^")
    }
}

private fun invokeFunc(func: () -> Unit) {
    func.invoke()
}