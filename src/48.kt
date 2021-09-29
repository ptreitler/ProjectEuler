import java.math.BigInteger

/**
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 *
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */
fun main() {
    val limit = 1000
    var sum = BigInteger.ZERO
    for (n in 1..limit) {
        sum += BigInteger.valueOf(n.toLong()).pow(n)
    }

    println(sum.toString().takeLast(10))
}