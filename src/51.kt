import kotlin.math.max

/**
 * By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values:
 * 13, 23, 43, 53, 73, and 83, are all prime.
 *
 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having
 * seven primes among the ten generated numbers, yielding the family:
 * 56003, 56113, 56333, 56443, 56663, 56773, and 56993.
 * Consequently 56003, being the first member of this family, is the smallest prime with this property.
 *
 * Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit,
 * is part of an eight prime value family.
 */
fun main() {
    val start = 56003L
    val primeValueFamilySize = 8

    var maxSize = 0
    var number = start
    while (maxSize < primeValueFamilySize) {
        val length = number.toString().length
        for (i in 0 until length - 3) {
            for (j in i + 1 until length - 2) {
                for (k in j + 1 until length - 1) {

                    val replacements = ('0'..'9').map {
                        val numberString = number.toString()
                        StringBuilder(numberString).apply {
                            setCharAt(i, it)
                            setCharAt(j, it)
                            setCharAt(k, it)
                        }.toString()
                    }
                    val primeReplacements = replacements.filter { it[0] != '0' && it.toInt().isPrime() }
                    val primeCount = primeReplacements.size
                    maxSize = max(maxSize, primeCount)
                    if (maxSize >= primeValueFamilySize) {
                        println(primeReplacements[0])
                        return
                    }
                }
            }
        }

        number += 2
    }
}