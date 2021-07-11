import kotlin.math.sqrt

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 *      a2 + b2 = c2
 *
 * For example, 32 + 42 = 9 + 16 = 25 = 52.
 *
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
fun main() {
    val limit = 1_000
    for (a in 1..((limit -3) / 3)) {
        for (b in (a + 1) until limit - a) {
            val c = limit - a - b
            if (c * c == a * a + b * b) {
                println("a: $a, b: $b, c: $c, product: ${a * b * c}")
            }
        }
    }
}
