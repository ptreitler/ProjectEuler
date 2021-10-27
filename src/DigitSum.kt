import java.math.BigInteger

fun BigInteger.digitSum(): BigInteger {
    var digitSum = BigInteger.ZERO
    var value = this
    while (value > BigInteger.ZERO) {
        digitSum += value.mod(BigInteger.TEN)
        value /= BigInteger.TEN
    }

    return digitSum
}