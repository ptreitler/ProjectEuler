/**
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits
 * 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
 * The lexicographic permutations of 0, 1 and 2 are:
 *
 *   012   021   102   120   201   210
 *
 *   0123   0132    0213    0231    0312    0321    1023    1203    1302    1320
 *
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
fun main() {
    val result = mutableListOf<String>()
    generatePermutations(mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), result)
    println(result.sorted()[999_999])
}


fun <T> generatePermutations(input: MutableList<T>, result: MutableList<String>, k: Int = input.size) {
    if (k == 1) {
        result.add(input.joinToString(""))
    } else {
        generatePermutations(input, result, k - 1)

        for (i in 0 until (k - 1)) {
            if (k % 2 == 0) {
                input.swap(i, k - 1)
            } else {
                input.swap(0, k - 1)
            }
            generatePermutations(input, result, k - 1)
        }
    }
}

fun <T> MutableList<T>.swap(a: Int, b: Int) {
    assert(a >= 0 && a < this.size && b >= 0 && b < this.size && a != b)
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}