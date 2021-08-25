/**
 * An irrational decimal fraction is created by concatenating the positive integers:
 *
 *   0.123456789101112131415161718192021...
 *
 * It can be seen that the 12th digit of the fractional part is 1.
 *
 * If dn represents the nth digit of the fractional part, find the value of the following expression.
 *
 *   d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
 */
fun main() {
    var digits = ""
    var number = 0
    while (digits.length <= 1_000_000) {
        digits += number
        number++
    }
    val d1 = digits[1].digitToInt()
    val d10 = digits[10].digitToInt()
    val d100 = digits[100].digitToInt()
    val d1000 = digits[1000].digitToInt()
    val d10000 = digits[10000].digitToInt()
    val d100000 = digits[100000].digitToInt()
    val d1000000 = digits[1000000].digitToInt()

    println(d1 * d10 * d100 * d1000 * d10000 * d100000 * d1000000)
}