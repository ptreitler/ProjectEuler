/**
 * Triangle, square, pentagonal, hexagonal, heptagonal, and octagonal numbers are all figurate (polygonal) numbers and
 * are generated by the following formulae:
 *
 * Triangle	 	    P3,n=n(n+1)/2	 	1, 3, 6, 10, 15, ...
 * Square	 	    P4,n=n2	        	1, 4, 9, 16, 25, ...
 * Pentagonal	 	P5,n=n(3n−1)/2	 	1, 5, 12, 22, 35, ...
 * Hexagonal	 	P6,n=n(2n−1)	 	1, 6, 15, 28, 45, ...
 * Heptagonal	 	P7,n=n(5n−3)/2	 	1, 7, 18, 34, 55, ...
 * Octagonal	 	P8,n=n(3n−2)	 	1, 8, 21, 40, 65, ...
 * The ordered set of three 4-digit numbers: 8128, 2882, 8281, has three interesting properties.
 *
 * The set is cyclic, in that the last two digits of each number is the first two digits of the next number (including
 * the last number with the first).
 * Each polygonal type: triangle (P3,127=8128), square (P4,91=8281), and pentagonal (P5,44=2882), is represented by a
 * different number in the set.
 * This is the only set of 4-digit numbers with this property.
 * Find the sum of the only ordered set of six cyclic 4-digit numbers for which each polygonal type: triangle, square,
 * pentagonal, hexagonal, heptagonal, and octagonal, is represented by a different number in the set.
 */

const val MAX = 9_999

fun main() {
    val triangleNumbers = generateSequence(1) { it + 1 }.map { n -> n * (n + 1) / 2 }.getFourDigitNumbers()
    val squareNumbers = generateSequence(1) { it + 1 }.map { n -> n * n }.getFourDigitNumbers()
    val pentagonalNumbers = generateSequence(1) { it + 1 }.map { n -> n * (3 * n - 1) / 2 }.getFourDigitNumbers()
    val hexagonalNumbers = generateSequence(1) { it + 1 }.map { n -> n * (2 * n - 1) }.getFourDigitNumbers()
    val heptagonalNumbers = generateSequence(1) { it + 1 }.map { n -> n * (5 * n - 3) / 2 }.getFourDigitNumbers()
    val octagonalNumbers = generateSequence(1) { it + 1 }.map { n -> n * (3 * n - 2) }.getFourDigitNumbers()

    val allNumbers = triangleNumbers.union(squareNumbers).union(pentagonalNumbers).union(hexagonalNumbers)
        .union(heptagonalNumbers).union(octagonalNumbers).sorted()

    val numberTypeMap = mutableMapOf<Int, Set<Int>>()
    for (number in allNumbers) {
        val types = mutableSetOf<Int>()

        if (number in triangleNumbers) types += 3
        if (number in squareNumbers) types += 4
        if (number in pentagonalNumbers) types += 5
        if (number in hexagonalNumbers) types += 6
        if (number in heptagonalNumbers) types += 7
        if (number in octagonalNumbers) types += 8

        numberTypeMap[number] = types
    }

    val chains = getChainsOfSix(allNumbers.toSet()).filterNotNull()
    chains.forEach { println(it) }

    println("=====")
    val result = chains.first { it.hasAllNumberTypes(setOf(3, 4, 5, 6, 7, 8), numberTypeMap) }
    println(result)
    println(result.sum())
}

fun Sequence<Int>.getFourDigitNumbers(): List<Int> =
    takeWhile { it <= MAX }.filter { it.toString().length == 4 && it / 10 % 10 != 0 }.toList()

fun getChainsOfSix(allNumbers: Set<Int>, chain: List<Int> = emptyList()): List<List<Int>?> {
    if (chain.isEmpty()) return allNumbers.flatMap { getChainsOfSix(allNumbers, listOf(it)) }

    val last = chain.last()
    if (chain.size == 6) {
        return if (last.overlaps(chain.first())) listOf(chain) else listOf(null)
    }

    val remainingNumbers = allNumbers - chain.toSet()
    val overlappingNumbers = remainingNumbers.filter { last.overlaps(it) }
    return overlappingNumbers.flatMap { getChainsOfSix(allNumbers, chain + it) }
}

private fun Int.overlaps(other: Int): Boolean = this % 100 == other / 100


private fun List<Int>.hasAllNumberTypes(availableNumbers: Set<Int>, numberTypeMap: Map<Int, Set<Int>>): Boolean {
    val first = this.first()

    if (this.size == 1 && numberTypeMap[first]!!.any { it in availableNumbers }) return true

    return numberTypeMap[first]!!.any {
        it in availableNumbers && (this - first).hasAllNumberTypes(availableNumbers - it, numberTypeMap)
    }
}