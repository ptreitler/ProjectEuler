/**
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example,
 * the 5-digit number, 15234, is 1 through 5 pandigital.
 *
 * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is
 * 1 through 9 pandigital.
 *
 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9
 * pandigital.
 *
 * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
fun main() {
    val limit = 10_000
    val products = mutableSetOf<Int>()
    for (a in 1..limit) {
        for (b in 1..limit) {
            if (isPandigitalProduct(a, b)) products.add(a * b)
        }
    }

    println(products.sum())
}

fun isPandigitalProduct(a: Int, b: Int): Boolean {
    val product = a * b
    val numbersAsString = "$a$b$product"
    return numbersAsString.length == 9 && ('1'..'9').all {
        numbersAsString.contains(it)
    }
}
