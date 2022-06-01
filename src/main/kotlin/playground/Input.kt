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

    data class Person(val name: String, val age: Int)

    fun nameStartWithC(person: Person) = person.name.startsWith("C")
    fun lengthOfNameGraterThan3(person: Person) = person.name.length > 3
    fun ageGraterThan10(person: Person) = person.age > 10

    val persons = listOf(
        Person("Choe Jaeho", 13),
        Person("Micheal Jackson", 3),
        Person("Nice One Sonny", 29),
        Person("Jaws bar", 9)
    )

    val list = mutableListOf<Person>()
    for (person in persons) {
        if (nameStartWithC(person) && lengthOfNameGraterThan3(person) && ageGraterThan10(person)) {
            list.add(person)
        }
    }
}
