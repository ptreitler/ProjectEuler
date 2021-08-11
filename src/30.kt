/**
 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
 *
 *   1634 = 1^4 + 6^4 + 3^4 + 4^4
 *   8208 = 8^4 + 2^4 + 0^4 + 8^4
 *   9474 = 9^4 + 4^4 + 7^4 + 4^4
 *   As 1 = 1^4 is not a sum it is not included.
 *
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 *
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 */
fun main() {
    val limit = 1_000_000L
    val n = 5

    var sum = 0L
    for (i in 10L..limit) {
        if (i.isSumOfNthPower(n)) {
            println(i)
            sum += i
        }
    }
    println("----")
    println(sum)
}

fun Long.isSumOfNthPower(n: Int): Boolean {
    var remainder = this
    var sum = 0L
    while (remainder > 0) {
        val digit = remainder % 10
        remainder /= 10
        var power = 1L
        repeat(n) { power *= digit }
        sum += power
    }
    return this == sum
}