package implementation

import java.io.BufferedReader
import java.io.InputStreamReader

const val TOP_EMPTY_SPACE = 4
const val FIELD_WIDTH = 3
const val FIELD_HEIGHT = 300 + TOP_EMPTY_SPACE
lateinit var field: Array<BooleanArray>
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    field = Array(FIELD_HEIGHT){ BooleanArray(FIELD_WIDTH){false} }
    val inputFigureNumbers = IntArray(n)
    repeat(n) {
        inputFigureNumbers[it] = br.readLine().toInt()
    }
    br.close()
    println(dfs(inputFigureNumbers, 0))
}

fun dfs(inputFigureNumbers: IntArray, curIdx: Int): Int {
    if (curIdx > inputFigureNumbers.lastIndex) {
        return getHeight()
    }

    val curFigure = Figure.createFigure(inputFigureNumbers[curIdx])
    var res = 100
    repeat(FIELD_WIDTH) { x ->
        curFigure.x = x
        repeat(4) {
            curFigure.y = field.lastIndex
            curFigure.rotateRight()
            if (curFigure.x + curFigure.matrix.first().size <= FIELD_WIDTH) {
                while (!check(curFigure)) {
                    curFigure.y--
                }
                drawFigure(curFigure)
                res = dfs(inputFigureNumbers, curIdx + 1).coerceAtMost(res)
                removeFigure(curFigure)
            }
        }
    }
    return res
}

fun removeFigure(figure: Figure) {
    figure.matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, isMarked ->
            if (isMarked) field[figure.y + i][figure.x + j] = false
        }
    }
}

fun drawFigure(figure: Figure) {
    figure.matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, isMarked ->
            if (isMarked) field[figure.y + i][figure.x + j] = true
        }
    }
}

fun check(figure: Figure): Boolean {
    if (figure.y + figure.matrix.size > FIELD_HEIGHT) return false

    figure.matrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, isMarked ->
            if (isMarked && field[figure.y + i][figure.x + j]) return false
        }
    }
    return true
}

fun getHeight(): Int {
    var height = 0
    for (i in field.lastIndex downTo TOP_EMPTY_SPACE) {
        var flag = true
        for (j in 0 until FIELD_WIDTH) {
            if (field[i][j]) {
                flag = false
                break
            }
        }
        if (flag) {
            return height
        }
        height++
    }
    return 0
}

class Figure(var x: Int = 0, var y: Int = 0, var matrix: Array<BooleanArray>) {

    //90도 회전
    fun rotateRight() {
        val newMatrix = Array(matrix.first().size){BooleanArray(matrix.size)}
        matrix.forEachIndexed { index, row ->
            writeRowToColumn(row, newMatrix.first().lastIndex - index, newMatrix)
        }
        matrix = newMatrix
    }

    private fun writeRowToColumn(row: BooleanArray, i: Int, targetArr: Array<BooleanArray>) {
        for (j in targetArr.indices) {
            targetArr[j][i] = row[j]
        }
    }
    companion object {
        fun createFigure(type: Int): Figure =
            when (type) {
                1 -> {
                    Figure(
                        matrix = arrayOf(
                            booleanArrayOf(true),
                            booleanArrayOf(true),
                            booleanArrayOf(true),
                            booleanArrayOf(true),
                        )
                    )
                }
                2 -> {
                    Figure(
                        matrix = arrayOf(
                            booleanArrayOf(true, true),
                            booleanArrayOf(true, false),
                            booleanArrayOf(true, false),
                        )
                    )
                }
                3 -> {
                    Figure(
                        matrix = arrayOf(
                            booleanArrayOf(true, true),
                            booleanArrayOf(false, true),
                            booleanArrayOf(false, true),
                        )
                    )
                }
                4 -> {
                    Figure(
                        matrix = arrayOf(
                            booleanArrayOf(false, true, false),
                            booleanArrayOf(true, true, true),
                        )
                    )
                }
                5 -> {
                    Figure(
                        matrix = arrayOf(
                            booleanArrayOf(false, true, true),
                            booleanArrayOf(true, true, false),
                        )
                    )
                }
                6 -> {
                    Figure(
                        matrix = arrayOf(
                            booleanArrayOf(true, true, false),
                            booleanArrayOf(false, true, true),
                        )
                    )
                }
                7 -> {
                    Figure(
                        matrix = arrayOf(
                            booleanArrayOf(true, true),
                            booleanArrayOf(true, true),
                        )
                    )
                }
                else -> {
                    Figure(
                        matrix = emptyArray()
                    )
                }
            }

    }
}