package playground

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.lang.invoke.StringConcatFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Test {
    constructor() {
        println("d")
    }
    init {
        println("df")
    }
}
fun main(args: Array<String>)  {

    val primes = sequence {
        var numbers: Sequence<Int> = generateSequence(2) { it + 1 }

        while(true) {
            val prime = numbers.first()
            yield(prime)
            println("prime : $prime =========")
            numbers = numbers.drop(1).filter {
                println(it)
                it % prime != 0
            }
        }
    }
    primes.take(5).toList()
}

data class Person(val name: String, val age: Int)

fun nameStartWithC(person: Person): Boolean = person.name.startsWith("C")
fun lengthOfNameGraterThan3(person: Person) = person.name.length > 3
fun ageGraterThan10(person: Person) = person.age > 10

val persons = listOf(
    Person("Choe Jaeho", 13),
    Person("Micheal Jackson", 3),
    Person("Nice One Sonny", 29),
    Person("Jaws bar", 9)
)

fun isOdd(x:Int):Boolean{
    return x % 2 != 0
}

fun solve() {
    persons.filter(::nameStartWithC and  ::lengthOfNameGraterThan3 and ::ageGraterThan10)
}
inline infix fun <P> ((P) -> Boolean).and(crossinline predicate: (P) -> Boolean): (P) -> Boolean {
    return{p: P->this(p) && predicate(p)}
}
