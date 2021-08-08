import Direction.*

/**
 * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:
 *
 *  21 22 23 24 25
 *  20  7  8  9 10
 *  19  6  1  2 11
 *  18  5  4  3 12
 *  17 16 15 14 13
 *
 * It can be verified that the sum of the numbers on the diagonals is 101.
 *
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 */
fun main() {
    val size = 1001
    assert(size > 0 && size % 2 == 1)

    val square = mutableListOf<MutableList<Int>>()
    repeat(size) { square.add(MutableList<Int>(size) { 0 }) }

    var count = 1
    var row = size / 2
    var column = size / 2
    var stepSize = 1
    var direction = RIGHT
    while (count <= size * size) {

        repeat(stepSize) {
            if (count > size * size) return@repeat
            square[row][column] = count
            when (direction) {
                RIGHT -> column++
                DOWN -> row++
                LEFT -> column--
                UP -> row--
            }
            count++
        }

        if (direction == DOWN || direction == UP) stepSize++
        direction = Direction.values()[(direction.ordinal + 1) % 4]
    }

    var sum = 0
    for (i in 0 until size) {
        sum += square[i][i]
        sum += square[size - i - 1][i]
    }
    sum -= square[size / 2][size / 2]
    println(sum)
}

enum class Direction {
    RIGHT, DOWN, LEFT, UP
}