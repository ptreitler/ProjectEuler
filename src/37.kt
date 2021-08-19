/**
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from
 * left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left:
 * 3797, 379, 37, and 3.
 *
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 *
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
fun main() {
    var count = 0
    var sum = 0
    var i = 11

    while (count < 11) {
        if (i.isTruncatablePrime()) {
            println(i)
            count++
            sum += i
        }
        i++
    }
    println("---")
    println(sum)
}

private fun Int.isTruncatablePrime(): Boolean {
    if (!this.isPrime()) return false

    var remainder = this.removeLeadingDigit()
    while (remainder >= 10) {
        if (!remainder.isPrime()) return false
        remainder = remainder.removeLeadingDigit()
    }
    if (!remainder.isPrime()) return false

    remainder = this / 10
    while (remainder >= 10) {
        if (!remainder.isPrime()) return false
        remainder /= 10
    }
    return remainder.isPrime()
}

private fun Int.removeLeadingDigit() = this.toString().substring(1).toInt()
