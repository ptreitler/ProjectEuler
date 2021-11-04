import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code
 * for Information Interchange). For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
 *
 *  A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given
 *  value, taken from a secret key. The advantage with the XOR function is that using the same encryption key on the
 *  cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.
 *
 * For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random
 * bytes. The user would keep the encrypted message and the encryption key in different locations, and without both
 * "halves", it is impossible to decrypt the message.
 *
 * Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key.
 * If the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message.
 * The balance for this method is using a sufficiently long password key for security, but short enough to be memorable.
 *
 * Your task has been made easy, as the encryption key consists of three lower case characters. Using p059_cipher.txt
 * (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes, and the knowledge that the
 * plain text must contain common English words, decrypt the message and find the sum of the ASCII values in the
 * original text.
 */
fun main() {
    val url = URL("https://projecteuler.net/project/resources/p059_cipher.txt")
    val numbers = BufferedReader(InputStreamReader(url.openStream())).use {
        it.readText()
    }.split(",").map { it.toInt() }

    for (a in 'a'..'z') {
        for (b in 'a'..'z') {
            for (c in 'a'..'z') {
                val password = listOf(a, b, c)
                val decryptedText = numbers.decrypt(password)
                if (decryptedText.split(" ").contains("the")) {
                    println("$a$b$c: $decryptedText")
                    println(decryptedText.toCharArray().sumOf { it.code })
                }
            }
        }
    }
}

private fun List<Int>.decrypt(password: List<Char>): String {
    val passwordLength = password.size
    return this.mapIndexed { i, c ->
        val p = password[i % passwordLength].code
        val decryptedCharacter = c xor p
        decryptedCharacter.toChar()
    }.joinToString("")
}
