/**
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways:
 *  (i) each of the three terms are prime, and,
 *  (ii) each of the 4-digit numbers are permutations of one another.
 *
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property,
 * but there is one other 4-digit increasing sequence.
 *
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 */
fun main() {
    for (n in 1000..9999) {
        if (n.isPrime()) {
            val permutations = mutableListOf<String>()
            generatePermutations(n.toDigits().toMutableList(), permutations)
            val primePermutations =
                permutations.distinct().map { it.toInt() }.filter { it.isPrime() && it.toString().length == 4 }
            if (primePermutations.size >= 3 && !primePermutations.contains(1487)) {
                val sortedPrimePermutations = primePermutations.sorted()
                for (i in sortedPrimePermutations.indices) {
                    val a = sortedPrimePermutations[i]
                    for (j in (i + 1)..(sortedPrimePermutations.size - 2)) {
                        val b = sortedPrimePermutations[j]
                        val delta = b - a
                        val c = b + delta
                        if (sortedPrimePermutations.contains(c)) {
                            println("$a$b$c")
                            return
                        }
                    }
                }
            }
        }
    }
}