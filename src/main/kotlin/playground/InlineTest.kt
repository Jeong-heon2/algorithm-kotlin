package playground

fun test() {
    var cnt = 0
    inlineTestFun {
        cnt++
    }
}

inline fun inlineTestFun(body: () -> Unit) {
    body.invoke()
}