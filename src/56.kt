import java.math.BigInteger

/**
 * A googol (10^100) is a massive number: one followed by one-hundred zeros; 100100 is almost unimaginably large:
 * one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.
 *
 * Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?
 */
fun main() {
    var a = BigInteger.ONE

    var maxDigitSum = BigInteger.ZERO
    var maxA = BigInteger.ZERO
    var maxB = 0
    var maxPower = BigInteger.ZERO

    while (a < BigInteger.valueOf(100L)) {
        for (b in 1 until 100) {
            val aToThePowerOfB = a.pow(b)
            val digitSum = aToThePowerOfB.digitSum()
            if (digitSum > maxDigitSum) {
                maxDigitSum = digitSum
                maxA = a
                maxB = b
                maxPower = aToThePowerOfB
            }
        }
        a++
    }

    println("$maxA^$maxB = $maxPower")
    println("digit sum = $maxDigitSum")
}