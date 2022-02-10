package kotlin_test

import netscape.javascript.JSObject
import java.util.*

fun main() {
    var cv = Covariance<A>()
    val a = "심은석"
    val b = "심은석"
    if (a===b) println("yes")

}
open class A : G(){
    fun d() {
        a
    }
}

class F : A() {
    fun c() {
        a
    }
}

open class G {
    protected val a  = 1
}

class Covariance<T : A> (){

}