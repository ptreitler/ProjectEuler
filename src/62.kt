import java.math.BigInteger

/**
 * The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3).
 * In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.
 *
 * Find the smallest cube for which exactly five permutations of its digits are cube.
 */

private const val LIMIT = 10_000
private val cubes = (1L..LIMIT).map { it.cubed() }.toSet()
private val max = cubes.maxOrNull()!!

fun main() {
    var base = 345L
    var cubePermutationCount = 0
    while (cubePermutationCount != 5) {
        val cube = base.cubed()
        val cubePermutations = cubes.filter { it.isPermutationOf(cube) }
//        println("base $base has ${cubePermutations.size} cube permutations: ${cubePermutations.joinToString(", ")}")
        cubePermutationCount = cubePermutations.size
        if (cubePermutationCount == 5) {
            println(cubePermutations.first())
            return
        }
        base += 1
    }

}

private fun Long.cubed() = this * this * this

private fun Long.isPermutationOf(other: Long): Boolean {
    val digits = toString().toCharArray().sorted()
    val otherDigits = other.toString().toCharArray().sorted()
    return digits == otherDigits
}
