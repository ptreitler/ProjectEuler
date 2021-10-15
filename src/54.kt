@file:Suppress("GrazieInspection")

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException
import java.net.URL

/**
 * In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:
 *
 * High Card: Highest value card.
 * One Pair: Two cards of the same value.
 * Two Pairs: Two different pairs.
 * Three of a Kind: Three cards of the same value.
 * Straight: All cards are consecutive values.
 * Flush: All cards of the same suit.
 * Full House: Three of a kind and a pair.
 * Four of a Kind: Four cards of the same value.
 * Straight Flush: All cards are consecutive values of same suit.
 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 * The cards are valued in the order:
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 *
 * If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of
 * eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of
 * queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next
 * highest cards are compared, and so on.
 *
 * Consider the following five hands dealt to two players:
 *
 * Hand	 	Player 1	 	     Player 2	 	      Winner
 * 1	 	5H 5C 6S 7S KD       2C 3S 8S 8D TD     Player 2
 *          Pair of Fives        Pair of Eights
 *
 * 2	 	5D 8C 9S JS AC      2C 5C 7D 8S QH      Player 1
 *          Highest card Ace    Highest card Queen
 *
 * 3	 	2D 9C AS AH AC      3D 6D 7D TD QD      Player 2
 *          Three Aces          Flush with Diamonds
 *
 * 4	 	4D 6S 9H QH QC      3D 6D 7H QD QS
 *          Pair of Queens      Pair of Queens      Player 1
 *          Highest card Nine   Highest card Seven
 *
 * 5	 	2H 2D 4C 4D 4S      3C 3D 3S 9S 9D
 *          Full House          Full House          Player 1
 *          With Three Fours    with Three Threes
 *
 * The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten
 * cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You
 * can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific
 * order, and in each hand there is a clear winner.
 *
 * How many hands does Player 1 win?
 */
fun main() {
    val url = URL("https://projecteuler.net/project/resources/p054_poker.txt")
    val hands = BufferedReader(InputStreamReader(url.openStream())).use {
        it.readText().split("\n").filter { line -> line.isNotBlank() }
    }

    println(
        hands.count {
            val (h1, h2) = parseHands(it)
            h1 > h2
        }
    )
}

private enum class Suit {
    HEARTS, SPADES, DIAMONDS, CLUBS;
}

private data class Card(
    val value: Int,
    val suit: Suit,
)

private data class Hand(
    val cards: List<Card>
) : Comparable<Hand> {
    val values = cards.map { it.value }
    val suits = cards.map { it.suit }

    val score: Int
        get() {
            var total = 0

            when {
                isStraight && isFlush -> total += 90_000 + cards.last().value
                isFourOfAKind -> {
                    total += 80_000
                    total += if (values[0] == values[1]) {
                        values[0] * 100 + values[4]
                    } else {
                        values[4] * 100 + values[0]
                    }
                }
                isFullHouse -> {
                    total += 70_000
                    total += if (values[0] == values[1] && values[1] == values[2]) {
                        values[0] * 100 + values[3]
                    } else {
                        values[3] * 100 + values[0]
                    }
                }
                isFlush -> total += 60_000 + values[4]
                isStraight -> total += 50_000 + values[4]
                isThreeOfAKind -> {
                    total += 40_000
                    total += values[2] * 100 // always part of the triplet
                    total += if (values[2] == values[4]) values[1] else values[4] // high card
                }
                isTwoPairs -> {
                    total += 30_000
                    total += values[3] * 100 // always part of the high pair
                    total += values[1] // always part of the low pair
                }
                isPair -> {
                    total += 20_000
                    total += when {
                        values[0] == values[1] -> values[0] * 100 + values[4]
                        values[1] == values[2] -> values[1] * 100 + values[4]
                        values[2] == values[3] -> values[2] * 100 + values[4]
                        else -> values[4] * 100 + values[2]
                    }
                }
                else -> total += values[4]
            }

            return total
        }

    val isFlush get() = suits.distinct().size == 1
    val isStraight: Boolean
        get() {
            val values = cards.map { it.value }
            return (values[0] + 1 == values[1] || (values[0] == 14 && values[1] == 2)) &&
                    values[1] + 1 == values[2] &&
                    values[2] + 1 == values[3] &&
                    values[3] + 1 == values[4]
        }
    val isFourOfAKind get() = values[0] == values[3] || values[1] == values[4]
    val isFullHouse
        get() = (values[0] == values[1] && values[1] == values[2] && values[3] == values[4]) ||
                (values[0] == values[1] && values[2] == values[3] && values[3] == values[4])
    val isThreeOfAKind
        get() = values[0] == values[1] && values[1] == values[2] ||
                values[1] == values[2] && values[2] == values[3] ||
                values[2] == values[3] && values[3] == values[4]

    val isTwoPairs
        get() = (values[0] == values[1] && (values[2] == values[3] || values[3] == values[4])) ||
                (values[1] == values[2] && values[3] == values[4])

    val isPair
        get() = values[0] == values[1] || values[1] == values[2] || values[2] == values[3] || values[3] == values[4]

    override fun compareTo(other: Hand): Int = this.score.compareTo(other.score)
}

private fun parseHands(string: String): Pair<Hand, Hand> {
    val cards = string.split(" ")
    val p1 = cards.subList(0, 5)
    val p2 = cards.subList(5, 10)

    return Pair(Hand(p1.toCards()), Hand(p2.toCards()))
}

@Suppress("SpellCheckingInspection")
private const val VALUES_STRING = "23456789TJQKA"

private fun List<String>.toCards(): List<Card> = this.map { parseCard(it) }.sortedBy { it.value }

private fun parseCard(string: String): Card {
    assert(string.length == 2)

    val index = VALUES_STRING.indexOf(string[0])
    if (index == -1) throw IllegalArgumentException("Invalid value: ${string[0]}")
    val value = index + 2

    val suit = when (string[1]) {
        'H' -> Suit.HEARTS
        'S' -> Suit.SPADES
        'D' -> Suit.DIAMONDS
        'C' -> Suit.CLUBS
        else -> throw IllegalArgumentException("Invalid suit: ${string[1]}")
    }
    return Card(value, suit)
}