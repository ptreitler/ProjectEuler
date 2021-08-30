import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
 *
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 *
 * By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we
 * form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle
 * number then we shall call the word a triangle word.
 *
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common
 * English words, how many are triangle words?
 */
fun main() {
    val url = URL("https://projecteuler.net/project/resources/p042_words.txt")
    val words = BufferedReader(InputStreamReader(url.openStream())).use {
        it.readText()
    }.replace("\"", "").split(",")

    val limit = 1000
    val triangleNumbers = calculateTriangleNumbers(limit)

    println(words.count { word ->
        val letterValue = word.getLetterValue()
        triangleNumbers.contains(letterValue).also {
            if(it) println("$word: $letterValue")
        }
    })
}

fun String.getLetterValue(): Int = this.toCharArray().sumOf { it - 'A' + 1 }

fun calculateTriangleNumbers(limit: Int): List<Int> {
    var triangleNumbers = mutableListOf<Int>()
    var i = 1
    var highestTriangleNumber = 0
    while (highestTriangleNumber <= limit) {
        highestTriangleNumber = i.getTriangleNumber()
        triangleNumbers.add(highestTriangleNumber)
        i++
    }
    return triangleNumbers
}

private fun Int.getTriangleNumber(): Int = (0.5 * this.toDouble() * (this + 1)).toInt()
