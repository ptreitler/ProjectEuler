/**
 * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three
 * solutions for p = 120.
 *
 * {20,48,52}, {24,45,51}, {30,40,50}
 *
 * For which value of p ≤ 1000, is the number of solutions maximised?
 */
fun main() {
    var maxP = 0
    var maxSolutions = 0
    val limit = 1000
    for (p in 5..limit) {
        var solutions = 0
        for (a in 1 until p / 2) {
            for (b in a..(p / 2)) {
                val c = p - a - b
                if (c > 0 && a * a + b * b == c * c) {
                    solutions++
                }
            }
        }
        if (solutions > maxSolutions) {
            maxP = p
            maxSolutions = solutions
        }
    }

    println("Max solutions for p=$maxP: $maxSolutions")
}