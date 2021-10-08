/**
 * It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different
 * order.
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 */
fun main() {
    var n = 2L
    val limit = 6

    while (true) {
        if ((2..limit).all {
                val multiple = n * it
                val nString = n.toString()
                val mString = multiple.toString()

                nString.length == mString.length && mString.toCharArray().sorted() == nString.toCharArray().sorted()
            }) {

            for (i in 1..limit) println(i * n)
            return
        }

        n++
    }
}