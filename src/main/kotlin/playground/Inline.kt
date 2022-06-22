package playground

interface Foo {
    fun qux(fn: () -> Unit)
}

open class Bar : Foo {
    final override inline fun qux(fn: () -> Unit){
        println("Bar ")
        fn.invoke()
    }
}

class Baz : Foo {
    override fun qux(fn: () -> Unit) {
        println("Baz ")
    }
}

fun main() {
    val a= 1
    var foo: Foo = Bar()
    foo.qux {
        println(a)
    }          // calls Bar.qux
    foo = Baz()
    foo.qux { }          // calls Foo.qux
}

