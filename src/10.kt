/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */
fun main() {
    val limit = 2_000_000
    println(
        (2L until limit).filter { it.isPrime() }.sum()
    )
}