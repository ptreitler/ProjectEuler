import kotlin.math.sqrt

/**
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
 *
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 *
 * Evaluate the sum of all the amicable numbers under 10000.
 */
fun main() {
    var sum = 0
    for (i in 1 until 10_000) {
        val divisorSum = i.divisorSum()
        if (i != divisorSum && divisorSum.divisorSum() == i) {
            println("$i <-> $divisorSum")
            sum += i
        }
    }

    println(sum)
}

fun Int.divisorSum(): Int {
    var sum = 1
    var i = 2
    while (i * i < this) {
        if (this % i == 0) {
            sum += i
            sum += (this / i)
        }
        i++
    }
    if (i * i == this && this % i == 0) sum += i

    return sum
}