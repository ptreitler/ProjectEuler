import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * All square roots are periodic when written as continued fractions and can be written in the form:
 * √(N) = a0 + 1 / (a1 + 1 / (a2 + 1 / (a3 + ...)))
 *
 * For example, let us consider √(23):
 * √(23) = 4 + √(23) - 4 = 4 + 1 / (1 + √(23)) = 4 + 1 / ((1 + √(23) - 3) / 7)
 *
 * If we continue we would get the following expansion:
 * √(23) = 4 + 1 / (1 + 1 / (3 + 1 / (1 + 1 / (8 + ...))))
 *
 * The process can be summarised as follows:
 * a0 = 4, 1 / (√(23) - 4) = (√(23) + 4) / 7 = 1 + (√(23) - 3) / 7
 * ...
 *
 * It can be seen that the sequence is repeating. For conciseness, we use the notation √(23) = [4; (1, 3, 1, 8)], to
 * indicate that the block (1,3,1,8) repeats indefinitely.
 * The first ten continued fraction representations of (irrational) square roots are:
 *
 * √(2) = [1; (2)], period=1
 * √(3) = [1; (1, 2)], period=2
 * √(5) = [2; (4)], period=1
 * √(6) = [2; (2, 4)], period=2
 * √(7) = [2; (1, 1, 1, 4)], period=4
 * √(8) = [2; (1, 4)], period=2
 * √(10) = [3; (6)], period=1
 * √(11) = [3; (3, 6)], period=2
 * √(12) = [3; (2, 6)], period=2
 * √(13) = [3; (1, 1, 1, 1, 6)], period=5
 *
 * Exactly four continued fractions, for N <= 13, have an odd period.
 *
 * How many continued fractions for N <= 10,000 have an odd period?
 */

private const val LIMIT = 10_000
private const val EPSILON = 0.000001
private const val PRECISION = 1000

fun main() {
    println(
        (1..LIMIT).count {
            val sqrt = sqrt(it.toDouble())
            if (floor(sqrt) == sqrt) {
                false
            } else {
                val period = it.getPeriod()
                println("$it: $period")
                period % 2 == 1
            }
        }
    )
}

fun Int.getPeriod(): Int {
    val bigDecimal = BigDecimal(this.toDouble())
    val sqrt = bigDecimal.sqrt(MathContext(PRECISION))
    val a0 = floor(sqrt.toDouble()).toInt()
    var remainder = sqrt
    var an = a0
    val remainders = mutableSetOf(remainder)
    var n = 0
    while (true) {
        remainder = BigDecimal.ONE.divide(remainder - BigDecimal.valueOf(an.toDouble()), MathContext(PRECISION))
        if (remainders.any {
                val delta = it.minus(remainder)
                return@any (it - remainder).abs() < BigDecimal.valueOf(EPSILON.toDouble())
            }) return n
        remainders += remainder
        an = floor(remainder.toDouble()).toInt()
        n++
    }
}
