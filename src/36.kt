/**
 * The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.
 *
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 *
 * (Please note that the palindromic number, in either base, may not include leading zeros.)
 */
fun main() {
    val limit = 1_000_000
    var sum = 0
    for (i in 1..limit) {
        if (i.isPalindrome() && i.toString(2).isPalindrome()) {
            println("$i = ${i.toString(2)}")
            sum += i
        }
    }
    println("---")
    println(sum)
}