fun <T> List<T>.permutations(): List<List<T>> {
    val result: MutableList<List<T>> = mutableListOf()

    fun generate(k: Int, list: MutableList<T>) {
        if (k == 1) {
            result.add(list.toList())
        } else {
            for (i in 0 until k) {
                generate(k - 1, list)
                if (k % 2 == 0) {
                    list.swap(i, k - 1)
                } else {
                    list.swap(0, k - 1)
                }
            }
        }
    }

    generate(this.size, this.toMutableList())
    return result
}

fun <T> MutableList<T>.swap(a: Int, b: Int) {
    assert(a >= 0 && a < this.size && b >= 0 && b < this.size && a != b)
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}