import java.math.BigInteger

/**
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 *
 * What is the sum of the digits of the number 2^1000
 */
fun main() {
    val twoToThePowerOfThousand = BigInteger.valueOf(2).pow(1000)
    var sum = BigInteger.valueOf(0L)
    var number = twoToThePowerOfThousand
    val ten = BigInteger.valueOf(10L)
    while (number > BigInteger.valueOf(0L)) {
        sum += number.mod(ten)
        number /= ten
    }

    println(sum)
}