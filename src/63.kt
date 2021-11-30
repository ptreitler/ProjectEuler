import java.math.BigInteger

/**
 * The 5-digit number, 16807=75, is also a fifth power. Similarly, the 9-digit number, 134217728=89, is a ninth power.
 * How many n-digit positive integers exist which are also an nth power?
 */
fun main() {
    var count = 0
    for (base in 1..10) {
        for (exponent in 1..100) {
            val result = BigInteger.valueOf(base.toLong()).pow(exponent)
            if (result.toString().length == exponent) {
                count++
                println("$base^$exponent = $result")
            }
        }
    }

    println("===== $count =====")
}