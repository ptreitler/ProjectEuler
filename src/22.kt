import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first
 * names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply
 * this value by its alphabetical position in the list to obtain a name score.
 *
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53,
 * is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
 *
 * What is the total of all the name scores in the file?
 */
@OptIn(ExperimentalStdlibApi::class)
fun main() {


    val url = URL("https://projecteuler.net/project/resources/p022_names.txt")
    val names = BufferedReader(InputStreamReader(url.openStream())).use {
        it.readText()
    }.replace("\"", "").split(",")
    val sortedNames = names.sorted()

    var sum = 0L
    sortedNames.forEachIndexed { index, name ->
        var nameValue = 0
        name.forEach {
            nameValue += (it.code - 64)
        }
        val valueTimesIndex = nameValue * (index + 1)
        println("\"$name\" is worth $nameValue * ${index + 1} = $valueTimesIndex")
        sum += valueTimesIndex
    }

    println(sum)
}