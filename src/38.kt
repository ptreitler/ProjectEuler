import java.lang.Integer.max

/**
 * Take the number 192 and multiply it by each of 1, 2, and 3:
 *
 * 192 × 1 = 192
 * 192 × 2 = 384
 * 192 × 3 = 576
 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated
 * product of 192 and (1,2,3)
 *
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645,
 * which is the concatenated product of 9 and (1,2,3,4,5).
 *
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer
 * with (1,2, ... , n) where n > 1?
 */
fun main() {
    var base = 1
    val limit = 500_000
    var maxPandigitalNumber = 0
    while (base < limit) {
        var numberString = base.toString()
        var multiplier = 2
        while (numberString.length < 9) {
            val nextProduct = base * multiplier
            numberString += nextProduct
            multiplier++
        }
        if (numberString.is1To9Pandigital()) {
            println("$base with (1..${multiplier - 1}) = $numberString")
            maxPandigitalNumber = max(maxPandigitalNumber, numberString.toInt())
        }
        base++
    }
    println("=====")
    println("max: $maxPandigitalNumber")
}

fun String.is1To9Pandigital(): Boolean = length == 9 &&
        contains("1") &&
        contains("2") &&
        contains("3") &&
        contains("4") &&
        contains("5") &&
        contains("6") &&
        contains("7") &&
        contains("8") &&
        contains("9")