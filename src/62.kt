/**
 * The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3).
 * In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.
 *
 * Find the smallest cube for which exactly five permutations of its digits are cube.
 */

fun main() {
    var base = 1L
    val cubes = mutableMapOf<String, Set<Long>>()
    while(true) {
        val cube = base.cubed()
        val cubeSorted = cube.toSortedDigits()
        val cubePermutations = cubes.getOrDefault(cubeSorted, emptySet()) + cube
        if (cubePermutations.size == 5) {
            println(cubePermutations.sorted()[0])
            return
        }
        cubes[cubeSorted] = cubePermutations
        base++
    }
}

private fun Long.cubed() = this * this * this

private fun Long.toSortedDigits(): String = toString().toCharArray().sorted().joinToString("")
