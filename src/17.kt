/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 */
fun main() {
    var letterCount = 0
    for (i in (1..1000)) {
        val numberString = getNumberString(i)
        println(numberString)
        letterCount += numberString.length
    }
    println(letterCount)
}

private val numbersUpToTwenty = listOf(
    "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty"
)

private val tens = listOf("", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

fun getNumberString(i: Int): String = when {
    i <= 20 -> numbersUpToTwenty[i]
    i < 100 -> {
        val tensIndex = i / 10
        tens[tensIndex] + numbersUpToTwenty[i % 10]
    }
    i == 1000 -> "onethousand"
    else -> {
        val hundreds = i / 100
        val tensIndex = i / 10 % 10
        val ones = i % 10
        numbersUpToTwenty[hundreds] + "hundred" + if (tensIndex == 0 && ones == 0) "" else "and" + getNumberString(i % 100)
    }
}
