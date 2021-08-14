/**
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may
 * incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 *
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 *
 * There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two
 * digits in the numerator and denominator.
 *
 * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 */
fun main() {
    val numerators = mutableListOf<Int>()
    val denominators = mutableListOf<Int>()
    for (a in 10..98) {
        for (b in (a + 1)..99) {
            val fraction = a / b.toDouble()
            val aString = a.toString()
            val bString = b.toString()
            ('1'..'9').forEach {
                if (aString.contains(it) && bString.contains(it)) {
                    try {
                        val aSimplified = aString.replace(it.toString(), "").toInt()
                        val bSimplified = bString.replace(it.toString(), "").toInt()
                        val fractionSimplified = aSimplified / bSimplified.toDouble()
                        if (fraction == fractionSimplified) {
                            println("$a/$b = $aSimplified/$bSimplified = $fraction")
                            numerators.add(a)
                            denominators.add(b)
                        }
                    } catch (ignored: NumberFormatException) {
                    }
                }
            }
        }
    }

    println()
    val numeratorsMultiplied = numerators.reduce { product, i -> product * i }
    val denominatorsMultiplied = denominators.reduce { product, i -> product * i }
    println("multiplied: $numeratorsMultiplied/$denominatorsMultiplied")

    println()
    val gcd = gcd(numeratorsMultiplied, denominatorsMultiplied)
    val numeratorSimplified = numeratorsMultiplied / gcd
    val denominatorSimplified = denominatorsMultiplied / gcd
    println("simplified: $numeratorSimplified/$denominatorSimplified")

    println()
    println("denominator: $denominatorSimplified")
}

fun gcd(a: Int, b: Int): Int = if (a == 0) b else gcd(b % a, a)

fun lcm(a: Int, b: Int): Int = a / gcd(a, b) * b
