/**
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
 * For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect
 * number.
 *
 * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this
 * sum exceeds n.
 *
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of
 * two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be
 * written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even
 * though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than
 * this limit.
 *
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
fun main() {
    val minimum = 12
    val limit = 28123

    println(2.divisorSum())

    val abundantNumbers = (minimum..(limit - minimum)).filter { it.isAbundantNumber() }

    val sum = (1..limit).filter { i ->
        abundantNumbers.none { abundantNumber -> (abundantNumber < i && (i - abundantNumber).isAbundantNumber()) }
    }.sum()

    println(sum)
}

fun Int.isAbundantNumber(): Boolean = this.divisorSum() > this

fun Int.getDivisors(): List<Int> {
    val divisors = mutableListOf(1)
    var i = 2
    while (i * i < this) {
        if (this % i == 0) {
            divisors.add(i)
            divisors.add(this / i)
        }
        i++
    }
    if (i * i == this && this % i == 0) divisors.add(i)

    return divisors
}
