import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2
 * to 10 are given:
 *
 * 1/2	= 	0.5
 * 1/3	= 	0.(3)
 * 1/4	= 	0.25
 * 1/5	= 	0.2
 * 1/6	= 	0.1(6)
 * 1/7	= 	0.(142857)
 * 1/8	= 	0.125
 * 1/9	= 	0.(1)
 * 1/10	= 	0.1
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring
 * cycle.
 *
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
fun main() {
    var maxLength = 0
    var maxLengthFraction = "1/1"

    val limit = 1000L
    for (i in 1L until limit) {
        val fraction = BigDecimal.ONE.divide(BigDecimal.valueOf(i), MathContext(4096, RoundingMode.HALF_UP))
        val decimalPart = fraction.toPlainString().substringAfter('.')

        val longestRecurringCycle = decimalPart.getLongestRecurringCycleLength()
        println("1/$i: $longestRecurringCycle ($fraction)")
        if (decimalPart.length > maxLength && longestRecurringCycle > maxLength) {
            maxLength = longestRecurringCycle
            maxLengthFraction = "1/$i"
        }
    }

    println("Longest recurring cycle: $maxLengthFraction with a length of $maxLength")
}

private fun String.getLongestRecurringCycleLength(): Int {
    var longestCycleLength = 0
    for (start in 0 until (this.length - 1)) {
        var length = 1
        var sequence = this.substring(start, start + length + 1)
        while (!this.substring(start + length + 1).startsWith(sequence) && start + length + length < this.length) {
            length++
            sequence = this.substring(start, start + length + 1)
        }
        if (this.substring(start + length + 1).startsWith(sequence) && sequence.length > longestCycleLength) {
            longestCycleLength = sequence.length
        }
    }
    return longestCycleLength
}
