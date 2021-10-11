import java.math.BigInteger

/**
 * There are exactly ten ways of selecting three from five, 12345:
 *
 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 *
 * In combinatorics, we use the notation (5 over 3) = 10.
 *
 * In general, (n over r) = n! / (r! * (n - r)!), where r <= n, n! = n * (n - 1) * (n - 2) * ... * 2 * 1, and 0! = 1.
 *
 * It is not until n =23, that a value exceeds one-million: (23 over 10) = 1144066.
 * .
 *
 * How many, not necessarily distinct, values of (n over r) for 1 <= n <= 100, are greater than one-million?
 */
fun main() {
    var count = 0
    val million = BigInteger.valueOf(1_000_000L)

    for (n in 23L..100L) {
        for (r in 0L until n) {
            val nFactorial = BigInteger.valueOf(n).factorial()
            val rFactorial = BigInteger.valueOf(r).factorial()
            val nMinusRFactorial = (BigInteger.valueOf(n) - BigInteger.valueOf(r)).factorial()
            val nOverR = nFactorial / (rFactorial * nMinusRFactorial)

            if (nOverR > million) {
                println("($n over $r) = $nOverR")
                count++
            }
        }
    }

    println("=====")
    println(count)
}