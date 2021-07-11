import kotlin.math.floor
import kotlin.math.max
import kotlin.math.sqrt

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */

fun main() {
    val number = 600_851_475_143L
    var i = sqrt(number.toDouble()).toLong()
    while (i >= 1) {
        if (number % i == 0L && i.isPrime()) {
            var j = number
            while (j % i == 0L) j /= i

            println(if (j.isPrime() && j > i) j else i)

            return
        }

        i--
    }

    println(number)
}

fun Long.isPrime(): Boolean = this > 1L && (this == 2L || this == 3L ||
        (2L..sqrt(this.toDouble()).toLong()).none { this % it == 0L })

fun Int.isPrime(): Boolean = this > 1 && (this == 2 || this == 3 ||
        (2..sqrt(this.toDouble()).toInt()).none { this % it == 0 })
