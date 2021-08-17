fun Int.isPalindrome(): Boolean = this == this.toString().reversed().toInt()

fun String.isPalindrome(): Boolean = this == this.reversed()