/**
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves
 * prime.
 *
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 *
 * How many circular primes are there below one million?
 */
fun main() {
    val limit = 1_000_000
    var count = 0
    for (i in 2 until limit) {
        if (i.isPrime() && i.getRotations().all { it.isPrime() }) {
            println(i)
            count++
        }
    }

    println("---")
    println(count)
}

fun Int.toDigits(): List<Int> {
    val digits = mutableListOf<Int>()
    var remainder = this
    while (remainder > 0) {
        val digit = remainder % 10
        digits.add(digit)
        remainder /= 10
    }
    return digits
}

fun Int.getRotations(): List<Int> {
    if (this < 10) return listOf(this)
    var currentRotation = this
    var rotations = mutableListOf(currentRotation)
    repeat(this.toString().length - 1) {
        val lastDigit = currentRotation % 10
        val remainder = currentRotation / 10
        currentRotation = (lastDigit.toString() + remainder.toString()).toInt()
        rotations.add(currentRotation)
    }
    return rotations
}

fun List<Int>.joinToInt(): Int = this.reduce { acc, i -> acc * 10 + i }