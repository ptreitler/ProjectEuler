import kotlin.math.pow
import kotlin.math.sqrt

/**
 * It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and
 * twice a square.
 *
 * 9 = 7 + 2×1^2
 * 15 = 7 + 2×2^2
 * 21 = 3 + 2×3^2
 * 25 = 7 + 2×3^2
 * 27 = 19 + 2×2^2
 * 33 = 31 + 2×1^2
 *
 * It turns out that the conjecture was false.
 *
 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 */
fun main() {
    var n = 9L
    while (true) {
        if (!n.isPrime()) {
            var square = 1
            var matchFound = false
            while (!matchFound && square <= sqrt(n / 2.0).toLong()) {
                val squarePart = (2 * square.toDouble().pow(2.0)).toLong()
                val remainder = n - squarePart
                if (remainder.isPrime()) {
                    println("$n = $remainder + 2 * $square^2")
                    matchFound = true
                }

                square++
            }

            if(!matchFound) {
                println("=====")
                println("Composite number $n can't be written as the sum of a prime and twice a square")
                return
            }
        }

        n += 2
    }
}