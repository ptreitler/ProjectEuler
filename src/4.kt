/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
fun main() {
    var max = 0
    for (i in 100..999) {
        for (j in 100..999) {
            val product = i * j
            if (product > max && product.isPalindrome()) max = product
        }
    }

    println(max)
}

fun Int.isPalindrome(): Boolean = this == this.toString().reversed().toInt()
