import java.math.BigInteger

fun Int.factorial(): Int = if (this == 0) 1 else (1..this).reduce { acc, i -> acc * i }

fun Long.factorial(): Long = if (this == 0L) 1L else (1L..this).reduce { acc, i -> acc * i }

fun BigInteger.factorial(): BigInteger =
    if (this == BigInteger.ZERO) BigInteger.ONE else {
        var factorial = BigInteger.valueOf(1L)
        for (n in 1L..this.toLong()) factorial *= BigInteger.valueOf(n)
        factorial
    }