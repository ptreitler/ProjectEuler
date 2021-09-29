import java.lang.Integer.max

/**
 * The prime 41, can be written as the sum of six consecutive primes:
 *
 * 41 = 2 + 3 + 5 + 7 + 11 + 13
 * This is the longest sum of consecutive primes that adds to a prime below one-hundred.
 *
 * The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to
 * 953.
 *
 * Which prime, below one-million, can be written as the sum of the most consecutive primes?
 */
fun main() {
    val limit = 1_000_000
    var maxCount = 0
    var maxPrime = 0
    for (start in 2 until limit) {
        if (start.isPrime()) {
            var sum = start
            var primeCount = 1
            var currentPrime = start + 1
            while (sum < limit && currentPrime < limit) {
                while (!currentPrime.isPrime()) currentPrime++
                sum += currentPrime
                primeCount++
                if (sum.isPrime() && sum < limit) {
                    if (primeCount > maxCount) {
                        maxCount = primeCount
                        maxPrime = sum
                    }
                }

                currentPrime++
            }
        }
    }

    println(maxPrime)
    println(maxCount)
}