/**
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
 *  >>vv
 *  >v>v
 *  >vv>
 *  v>>v
 *  v>v>
 *  vv>>
 *
 * How many such routes are there through a 20×20 grid?
 */
fun main() {
    val gridSize = 20

    val gridValues = mutableListOf<List<Long>>()
    var row = mutableListOf<Long>()
    repeat(gridSize + 1) { row.add(1L) }
    gridValues.add(row)
    for (i in 1..gridSize) {
        row = mutableListOf(1L)
        for (j in 1..gridSize) {
            row.add(row[j - 1] + gridValues[i - 1][j])
        }
        gridValues.add(row)
    }
    println(gridValues.last().last())
}



