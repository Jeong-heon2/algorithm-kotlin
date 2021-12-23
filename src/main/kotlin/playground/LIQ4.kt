package playground

import java.util.*

val operandStack = Stack<String>()
val operatorStack = Stack<Char>()

fun main(args: Array<String>) {
    val input = readLine()!!
    // * , / 연산 먼저

    var operand = ""
    input.forEach {
        if (isDigit(it) || it == '.') {
            operand += it
        }
        else {
            operandStack.push(operand)
            operand = ""
            operationMulOrDiv()
            operatorStack.push(it)
        }
    }
    operandStack.push(operand)
    operationMulOrDiv()

    // + , - 연산
    while (operatorStack.isNotEmpty()) {
        operationPlusOrMinus()
    }

    printResult(operandStack.pop())
}

fun printResult(value: String) {
    var zeroPoint = value.length
    for (i in value.lastIndex downTo 0) {
        if (value[i] == '0') zeroPoint = i
        else break
    }
    var res = ""
    for (i in 0 until zeroPoint-1) {
        res += value[i]
    }
    if (value[zeroPoint - 1] != '.') res += value[zeroPoint - 1]
    println(res)
}

fun operationMulOrDiv() {
    if (operatorStack.isEmpty()) return
    when(operatorStack.peek()) {
        '*' -> {
            operatorStack.pop()
            val operand2 = operandStack.pop()
            val operand1 = operandStack.pop()
            operandStack.push((operand1.toDouble() * operand2.toDouble()).toString())
        }
        '/' -> {
            operatorStack.pop()
            val operand2 = operandStack.pop()
            val operand1 = operandStack.pop()
            operandStack.push((operand1.toDouble() / operand2.toDouble()).toString())
        }
        else -> {}
    }
}

fun operationPlusOrMinus() {
    if (operatorStack.isEmpty()) return
    when(operatorStack.peek()) {
        '+' -> {
            operatorStack.pop()
            val operand2 = operandStack.pop()
            val operand1 = operandStack.pop()
            operandStack.push((operand1.toDouble() + operand2.toDouble()).toString())
        }
        '-' -> {
            operatorStack.pop()
            val operand2 = operandStack.pop()
            val operand1 = operandStack.pop()
            operandStack.push((operand1.toDouble() - operand2.toDouble()).toString())
        }
        else -> {}
    }
}

fun isDigit(ch : Char): Boolean {
    if (ch in '0'..'9') return true
    return false
}