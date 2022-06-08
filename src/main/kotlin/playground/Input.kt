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

/*
N개의 원소로 이뤄진 수열 A가 있을때.
구간의 연속된 원소들의 합이 S 이상인 구간중 길이가 가장짧은 구간의 길이를 구하세요
10 15
5 1 3 5 10 7 4 9 2 8

 */
fun solve(arr: IntArray, s: Int): Int {
    if (arr.isEmpty()) return 0
    if (arr.first() >= s) return 1

    var i = 0
    var j = 1
    var sum = arr[i]
    var ans = Int.MAX_VALUE
    while (i <= j) {
        while (j < arr.size && sum < s) {
            sum += arr[j++]
        }
        if (sum >= s) {
            ans = ans.coerceAtMost(j - i + 1)
        }

        sum -= arr[i++];
    }
    return ans
}
// 1, 2 3, 4 5
//