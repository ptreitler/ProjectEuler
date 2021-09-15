import kotlin.math.sqrt

/**
 * Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:
 *
 * Triangle	     	Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
 * Pentagonal	 	Pn=n(3n−1)/2 	1, 5, 12, 22, 35, ...
 * Hexagonal	 	Hn=n(2n−1)	 	1, 6, 15, 28, 45, ...
 * It can be verified that T285 = P165 = H143 = 40755.
 *
 * Find the next triangle number that is also pentagonal and hexagonal.
 */
fun main() {
    var n = 286L
    while (true) {
        val triangleNumber = n * (n + 1) / 2
        val pn = triangleNumber.getPentagonalNumberIndex()
        if (pn >= 0) {
            val hn = triangleNumber.getHexagonalNumberIndex()
            if (hn >= 0) {
                println("T$n = P$pn = H$hn = $triangleNumber")
                return
            }
        }
        n++
    }
}

fun getHexagonalNumber(n: Long): Long = n * (2 * n - 1)

fun Long.getHexagonalNumberIndex(): Int {
    val n = ((sqrt((8 * this + 1).toDouble()) + 1) / 4).toLong()
    return if (getHexagonalNumber(n) == this) n.toInt() else -1

    // n = (sqrt(8 * h + 1) + 1) / 4
    // 4n = sqrt(8 * h + 1) + 1
    // 4n - 1 = sqrt(8h + 1)
    // (4n - 1)^2 = 8h + 1
    // 16n^2 - 8n + 1 = 8h + 1
    // 16n^2 - 8n = 8h
    // 2n^2 - n = h
}