/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10 001st prime number?
 */
fun main() {
    var number = 2L
    var primeCount = 0
    val target = 10001
    while(primeCount < target) {
        if(number.isPrime()) {
            primeCount++
        }
        number++
    }

    println(number - 1)
}