import kotlin.math.sqrt

fun Long.isPrime(): Boolean = this > 1L && (this == 2L || this == 3L ||
        (2L..sqrt(this.toDouble()).toLong()).none { this % it == 0L })

fun Int.isPrime(): Boolean = this > 1 && (this == 2 || this == 3 ||
        (2..sqrt(this.toDouble()).toInt()).none { this % it == 0 })
