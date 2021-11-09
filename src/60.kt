import kotlin.math.min

/**
 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in any order the
 * result will always be prime. For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four
 * primes, 792, represents the lowest sum for a set of four primes with this property.
 *
 * Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
 */
fun main() {
    val limit = 10_000
    val primes = (1..limit).filter { it.isPrime() }

    val primePairs = mutableMapOf<Int, List<Int>>()

    for (i in primes) {
        for (j in primes) {
            if ((i != j) && "$i$j".toInt().isPrime() && "$j$i".toInt().isPrime()) {
                primePairs[i] = primePairs.getOrDefault(i, listOf()) + j
                primePairs[j] = primePairs.getOrDefault(j, listOf()) + i
            }
        }
    }

    val primesWithFourOrMorePairs = primePairs.filter { it.value.size >= 4 }

    println("found ${primesWithFourOrMorePairs.keys.size} / ${primes.size} primes with >= 4 pairs")

    var minimumSum = Int.MAX_VALUE

    for (p1 in primesWithFourOrMorePairs.keys) {
        for (p2 in primesWithFourOrMorePairs[p1]!!) {
            val commonPairsP1P2 = primesWithFourOrMorePairs[p1]!!.intersect(primesWithFourOrMorePairs[p2]!!).toList()
            if (commonPairsP1P2.size >= 3) {
                for (p3 in commonPairsP1P2) {
                    val commonPairsP1P2P3 = commonPairsP1P2.intersect(primesWithFourOrMorePairs[p3]!!).toList()
                    if (commonPairsP1P2P3.size >= 2) {
                        for (p4 in commonPairsP1P2P3) {
                            val commonPairsP1P2P3P4 = commonPairsP1P2P3.intersect(primesWithFourOrMorePairs[p4]!!).toList()
                            if (commonPairsP1P2P3P4.isNotEmpty()) {
                                for (p5 in commonPairsP1P2P3P4) {
                                    val sum = p1 + p2 + p3 + p4 + p5
                                    println("$p1 + $p2 + $p3 + $p4 + $p5 = $sum")
                                    minimumSum = min(minimumSum, sum)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    println("=====")
    println("minimum sum: $minimumSum")
}