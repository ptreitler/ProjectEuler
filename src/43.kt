/**
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some
 * order, but it also has a rather interesting sub-string divisibility property.
 *
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
 *
 *  - d2d3d4=406 is divisible by 2
 *  - d3d4d5=063 is divisible by 3
 *  - d4d5d6=635 is divisible by 5
 *  - d5d6d7=357 is divisible by 7
 *  - d6d7d8=572 is divisible by 11
 *  - d7d8d9=728 is divisible by 13
 *  - d8d9d10=289 is divisible by 17
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 */
fun main() {
    val input = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val pandigitalNumbers = mutableListOf<String>()
    var sum = 0L
    generatePermutations(input, pandigitalNumbers)
    pandigitalNumbers.filter { !it.startsWith("0") }.forEach {
        if (it.isSubstringDivisible()) {
            println(it)
            sum += it.toLong()
        }
    }
    println("====")
    println("sum = $sum")
}

private fun String.isSubstringDivisible(): Boolean =
    this.substring(1, 4).toInt() % 2 == 0 &&
            this.substring(2, 5).toInt() % 3 == 0 &&
            this.substring(3, 6).toInt() % 5 == 0 &&
            this.substring(4, 7).toInt() % 7 == 0 &&
            this.substring(5, 8).toInt() % 11 == 0 &&
            this.substring(6, 9).toInt() % 13 == 0 &&
            this.substring(7).toInt() % 17 == 0
