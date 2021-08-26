import kotlin.math.max

/**
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example,
 * 2143 is a 4-digit pandigital and is also prime.
 *
 * What is the largest n-digit pandigital prime that exists?
 */
fun main() {
    var max = 0
    for (number in 2143..987654321 step(2)) {
        if (number.isNPandigital() && number.isPrime()) {
            println(number)
            max = max(max, number)
        }
    }
    println("=====")
    println(max)
}

fun Int.isNPandigital() = (1..(this.toString().length)).none {
    !this.toString().contains(it.toString())
}