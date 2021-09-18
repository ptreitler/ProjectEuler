import kotlin.math.pow

/**
 * The first two consecutive numbers to have two distinct prime factors are:
 *
 * 14 = 2 × 7
 * 15 = 3 × 5
 *
 * The first three consecutive numbers to have three distinct prime factors are:
 *
 * 644 = 2² × 7 × 23
 * 645 = 3 × 5 × 43
 * 646 = 2 × 17 × 19.
 *
 * Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?
 */
fun main() {
    val numberOfPrimeFactors = 4
    val targetCount = 4
    val result = mutableListOf<Int>()
    var n = 2

    while (result.size < targetCount) {
        val primeFactors = n.getPrimeFactors()
        if (primeFactors.size == numberOfPrimeFactors) {
            result.add(n)
        } else {
            result.clear()
        }

        n++
    }

    result.forEach { println(it.getPrimeFactorsString()) }
}

fun Int.getPrimeFactors(): List<Pair<Int, Int>> {
    val primeFactors = mutableListOf<Pair<Int, Int>>()
    var remainder = this
    var prime = 2
    while (remainder > 1 && prime * prime <= this) {
        if (remainder.isPrime()) {
            primeFactors.add(Pair(remainder, 1))
            return primeFactors
        }

        if (remainder % prime == 0) {
            var exponent = 1
            while (remainder % prime.toDouble().pow(exponent + 1).toInt() == 0) exponent++
            primeFactors.add(Pair(prime, exponent))
            remainder /= prime.toDouble().pow(exponent).toInt()
        }

        do {
            prime++
        } while (!prime.isPrime())
    }

    if (remainder > 1) primeFactors.add(Pair(remainder, 1))

    return primeFactors
}

fun Int.getPrimeFactorsString(): String = "$this = ${
    this.getPrimeFactors().joinToString(" × ") {
        val exponent = if (it.second > 1) "^${it.second}" else ""
        "${it.first}${exponent}"
    }
}"