import java.math.BigInteger

/**
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 *
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 *
 * Find the sum of the digits in the number 100!
 */
fun main() {
    var factorial = BigInteger.ONE
    val limit = 100
    for (i in 2..limit) {
        factorial *= BigInteger.valueOf(i.toLong())
    }
    println("Factorial of $limit = $factorial")

    var digitSum = BigInteger.ZERO
    while (factorial > BigInteger.ZERO) {
        digitSum += factorial.mod(BigInteger.TEN)
        factorial /= BigInteger.TEN
    }

    println("digit sum = $digitSum")
}