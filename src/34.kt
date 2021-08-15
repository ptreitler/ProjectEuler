/**
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 *
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 *
 * Note: As 1! = 1 and 2! = 2 are not sums they are not included.
 */
fun main() {
    val limit = 1_000_000
    var sum = 0
    for (i in 3..limit) {
        if (i.isEqualToSumOfDigitFactorials()) {
            println(i)
            sum += i
        }
    }
    println("----")
    println(sum)
}

private fun Int.isEqualToSumOfDigitFactorials(): Boolean {
    var remainder = this
    var sumOfDigitFactorials = 0
    while (remainder > 0) {
        val digit = remainder % 10
        sumOfDigitFactorials += digit.factorial()
        if (sumOfDigitFactorials > this) return false
        remainder /= 10
    }
    return this == sumOfDigitFactorials
}
