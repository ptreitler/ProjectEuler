/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
fun main() {
    val sum = (1..999).filter { it.isMultipleOf3Or5() }.sum()
    println(sum)
}

fun Int.isMultipleOf3Or5() = this % 3 == 0 || this % 5 == 0